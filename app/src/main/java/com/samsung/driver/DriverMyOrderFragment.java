package com.samsung.driver;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.samsung.data.BaseData;
import com.samsung.data.Route;
import com.samsung.data.SQLManager;
import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.Log;
import com.samsung.isharecar.MainActivity;
import com.samsung.isharecar.R;
import com.samsung.listview.DriverViewRouteAdapter;
import com.samsung.listview.PassengerViewRouteAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DriverMyOrderFragment extends FragmentBase{
    
    private static View view;
    String TAG = "GoWorkRegisterFragment";
    private BaseData mBaseData = null;
    private ListView ListViewRoute = null;
    private ListView passengerListview =null;
    private TextView passengerListViewTitle = null;
    private TextView driverListViewTitle = null;
    private Route route = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.driver_my_orders_list, container, false);
        driverGoWorkRegisterActivity = (DriverGoWorkRegisterActivity)getActivity();

        //设置标题栏
        driverGoWorkRegisterActivity.childViewActionBarStyle(R.string.query_my_dindan);

        passengerListview = (ListView) view.findViewById(R.id.passengerViewRoute);
        ListViewRoute = (ListView) view.findViewById(R.id.listViewRoute);
        
        passengerListViewTitle =(TextView)view.findViewById(R.id.DriverRouteTitle);
        driverListViewTitle = (TextView)view.findViewById(R.id.PassengerRouteTitle);
        
        if(MainActivity.mDebug)
            //get the data from SQLite
            mBaseData = new SQLManager(getActivity());
        else{
            //get the date from network
            mBaseData = new SQLManager(getActivity());
        }
        configPassengerView();
        return view;
    }

    void configPassengerView(){
        String currentTime = null;
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentTime = df.format(day).toString();
        Log.d(TAG,"KMA ...currentTime:"+currentTime);
        
        List<Route> MyRoute = new ArrayList<Route>();
        List<Route> DriverRoute = new ArrayList<Route>();
        
        
        String[] queryRouteInfo = new String[BaseData.lengthOfRoute];
        for(int i = 0; i < BaseData.lengthOfRoute; i++){  //get all of the user route 
            queryRouteInfo[i] = null;
        }
        
        // get the user phone number
        queryRouteInfo[BaseData.Index_Route_UserPhone ] = MainActivity.getUserPhone();
        // get the local time
        queryRouteInfo[BaseData.Index_Route_Time] = currentTime;
        //only query my driver route
        queryRouteInfo[BaseData.Index_Route_Identify] = BaseData.Identify_Driver;
        
        MyRoute = mBaseData.searchByRouteColumn(getActivity(),queryRouteInfo);
        if((MainActivity.getUserPhone() != null) && (MyRoute.size() > 0)){
            passengerListViewTitle.setVisibility(View.VISIBLE);
            passengerListview.setAdapter(new PassengerViewRouteAdapter(getActivity(), MyRoute));
            DriverRoute = mBaseData.searchByRouteColumn(getActivity(),MyRoute);
            passengerListview.setOnItemClickListener(passengerItemClick);
        }else{
            // remove the user phone number
            queryRouteInfo[BaseData.Index_Route_UserPhone ] = null;
            // get the local time
            queryRouteInfo[BaseData.Index_Route_Time] = currentTime;
            //only query the passenger route
            queryRouteInfo[BaseData.Index_Route_Identify] = BaseData.Identify_Passenger;
            
//          passengerListViewTitle.setVisibility(View.GONE);
            DriverRoute = mBaseData.searchByRouteColumn(getActivity(),queryRouteInfo);
        }
        if(DriverRoute.size() > 0){
            DriverViewRouteAdapter adapter = new DriverViewRouteAdapter(getActivity(), DriverRoute);
            ListViewRoute.setAdapter(adapter);
            ListViewRoute.setOnItemClickListener(driverItemClick);
        }
    }
    
    private AdapterView.OnItemClickListener passengerItemClick = new AdapterView.OnItemClickListener(){
        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id){
            Log.d(TAG, "KMA ...onItemClick, position="+ position);
            Log.d(TAG, "KMA ...onItemClick, id="+ id);
            route = (Route)arg0.getItemAtPosition(position);
            
            new AlertDialog.Builder(driverGoWorkRegisterActivity)
            .setTitle(R.string.quit_hint)
            .setMessage(R.string.confirm_delete_route)
            .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialoginterface, int which) {
                    Log.d(TAG, "KMA ...route id:"+(route.getId()));
                    mBaseData.deleteRoute(getActivity(), route);
                    //继续回跳到订单界面
                    //跳转到订单界面
                    driverGoWorkRegisterActivity.setType(driverGoWorkRegisterActivity.ViewDriver);
                    driverGoWorkRegisterActivity.init();
                }

            })
            .setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener() {
                //添加返回按钮
                @Override
                public void onClick(DialogInterface dialoginterface, int which) {//响应事件  
                    Log.d(TAG,"KMA ...删除取消");
                }  
            }).show();
        }
    };
    
    private AdapterView.OnItemClickListener driverItemClick = new AdapterView.OnItemClickListener(){
        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id){
            Log.d(TAG, "KMA ...onItemClick, position="+ position);
            Log.d(TAG, "KMA ...onItemClick, id="+ id);
            route = (Route)arg0.getItemAtPosition(position);
            String phoneNum = route.getUserPhone();
            Log.d(TAG, "KMA ...phoneNum"+phoneNum);
            //启动拨号盘
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+phoneNum));
             if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                 Log.d(TAG, "KMA ...start dial");
                 startActivity(intent);
             }
        }
    };

    @Override
    public void onPushMessage(int type, String message1, String message2) {
        
    }

}
