package com.samsung.passenger;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
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

public class PassengerMyOrderFragment extends FragmentBase{
    
    private static View view;
    public GoWorkRegisterActivity mGoWorkRegisterActivity;
    String TAG = "GoWorkRegisterFragment";
    private BaseData mBaseData = null;
    private ListView ListViewRoute = null;
    private ListView passengerListView =null;
    private TextView passengerListViewTitle = null;
    private TextView driverListViewTitle = null;
    private ImageView passengerTitleIcon = null;
    
    private Route route = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.passenger_my_orders_list, container, false);
        mGoWorkRegisterActivity = (GoWorkRegisterActivity)getActivity();

        //设置标题栏
        goWorkRegisterActivity.childViewActionBarStyle(R.string.query_my_dindan);

        passengerListView = (ListView) view.findViewById(R.id.passengerViewRoute);
        ListViewRoute = (ListView) view.findViewById(R.id.listViewRoute);
        
        passengerListViewTitle =(TextView)view.findViewById(R.id.passengerRouteTitle);
        driverListViewTitle = (TextView)view.findViewById(R.id.driverRouteTitle);
        passengerTitleIcon = (ImageView)view.findViewById(R.id.passengerorder);
        
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
        
        List<Route> UserRoute = new ArrayList<Route>();
        List<Route> DriverRoute = new ArrayList<Route>();

        String[] queryRouteInfo = new String[BaseData.lengthOfRoute];
        for(int i = 0; i < BaseData.lengthOfRoute; i++){  //get all of the user route 
            queryRouteInfo[i] = null;
        }
        
        // get the user phone number
        queryRouteInfo[BaseData.Index_Route_UserPhone ] = MainActivity.getUserPhone();
        // get the local time
        queryRouteInfo[BaseData.Index_Route_Time] = currentTime;
        //only query the passenger route
        queryRouteInfo[BaseData.Index_Route_Identify] = BaseData.Identify_Passenger;
        
        UserRoute = mBaseData.searchByRouteColumn(getActivity(),queryRouteInfo);
        if((MainActivity.getUserPhone() != null) && (UserRoute.size() > 0)){
            passengerListViewTitle.setVisibility(View.VISIBLE);
            passengerTitleIcon.setVisibility(View.VISIBLE);
            passengerListView.setAdapter(new PassengerViewRouteAdapter(getActivity(), UserRoute));
            passengerListView.setOnItemClickListener(passengerItemClick);
            DriverRoute = mBaseData.searchByRouteColumn(getActivity(),UserRoute);
        }else{
            // remove the user phone number
            queryRouteInfo[BaseData.Index_Route_UserPhone ] = null;
            // get the local time
            queryRouteInfo[BaseData.Index_Route_Time] = currentTime;
            //only query the passenger route
            queryRouteInfo[BaseData.Index_Route_Identify] = BaseData.Identify_Driver;
            //query only have absent car
            queryRouteInfo[BaseData.Index_Route_NumOfPos] = BaseData.query_absent_driver_route;
            
//          passengerListViewTitle.setVisibility(View.GONE);
//          passengerTitleIcon.setVisibility(View.GONE);
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
            route = (Route)arg0.getItemAtPosition(position);
            
            new AlertDialog.Builder(mGoWorkRegisterActivity)
            .setTitle(R.string.quit_hint)
            .setMessage(R.string.confirm_delete_route)
            .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialoginterface, int which) {
                    Log.d(TAG, "KMA ...route id:"+(route.getId()));
                    mBaseData.deleteRoute(getActivity(), route);
                    //继续回跳到订单界面
                    //跳转到订单界面
                    goWorkRegisterActivity.setType(GoWorkRegisterActivity.ViewDriver);
                    goWorkRegisterActivity.init();
                }

            })
            .setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialoginterface, int which) {
                    Log.d(TAG,"KMA ...删除取消");
                }  
            }).show();
        }
    };
    
    private AdapterView.OnItemClickListener driverItemClick = new AdapterView.OnItemClickListener(){
        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id){
            route = (Route)arg0.getItemAtPosition(position);
            String phoneNum = route.getUserPhone();
            //启动拨号盘
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+phoneNum));
             if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                 startActivity(intent);
             }
        }
    };

    public void onClick(View arg0){
        //TODO
    }

    @Override
    public void onPushMessage(int type, String message1, String message2) {
        
    }

}
