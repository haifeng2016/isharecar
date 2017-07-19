package com.samsung.driver;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.samsung.data.BaseData;
import com.samsung.data.Route;
import com.samsung.data.SQLManager;
import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.Log;
import com.samsung.isharecar.MainActivity;
import com.samsung.isharecar.R;
import com.samsung.passenger.DateTimePickerDialog;
import com.samsung.register.RegisterSettingsSetAddrBaiduMapActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DriverGoWorkRegisterFragment extends FragmentBase{
    
    private static View view;
    String TAG = "GoWorkRegisterFragment";
    private BaseData mBaseData = null;
    
    private EditText goTime = null;
    private EditText goNumber = null;
    private EditText homeAddress = null;
    private EditText workAddress = null;
    private EditText passWay = null;
    private Button submitRegister = null;
    
    private String goTimeStr = null;
    private String goNumberStr = null;
    private String homeAddressStr = null;
    private String workAddressStr = null;
    private String passWayStr = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.driver_gowork_register_fragment, container, false);
        driverGoWorkRegisterActivity = (DriverGoWorkRegisterActivity)getActivity();
        //设置标题栏
        driverGoWorkRegisterActivity.childViewActionBarStyle(R.string.driver_go);
        
        if(MainActivity.mDebug)
            //get the data from SQLite
            mBaseData = new SQLManager(getActivity());
        else{
            //get the date from network
            mBaseData = new SQLManager(getActivity());
        }
        initView();
        return view;
    }
    
    private void initView(){
        goTime = (EditText) view.findViewById(R.id.passenger_go_time);
        goTime.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                createDateTimeDialog();
            }
        });
        goNumber = (EditText) view.findViewById(R.id.passenger_go_number);
        homeAddress = (EditText) view.findViewById(R.id.passenger_home_add);
        workAddress = (EditText) view.findViewById(R.id.passenger_work_add);
        passWay = (EditText) view.findViewById(R.id.by_the_way);
        submitRegister = (Button) view.findViewById(R.id.driver_reservation);
        submitRegister.setEnabled(true);
        submitRegister.setOnClickListener(mSubmitRegisterListener);
        init();
    }
    
    private void init(){
        homeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), RegisterSettingsSetAddrBaiduMapActivity.class);
                driverGoWorkRegisterActivity.startActivityForResult(intent, driverGoWorkRegisterActivity.REQUEST_CODE_MAP_ADDRESS_HOME);
            }
        });
        
        workAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), RegisterSettingsSetAddrBaiduMapActivity.class);
                driverGoWorkRegisterActivity.startActivityForResult(intent, driverGoWorkRegisterActivity.REQUEST_CODE_MAP_ADDRESS_OFFICE);
            }
        });
    }
    
    private void createDateTimeDialog() {
        
        DateTimePickerDialog timePicker = new DateTimePickerDialog(getActivity(),
                new DateTimePickerDialog.ICustomDateTimeListener() {
                    @Override
                    public void onSet(Calendar calendarSelected,
                            Date dateSelected, int year, String monthFullName,
                            String monthShortName, int monthNumber, int date,
                            String weekDayFullName, String weekDayShortName,
                            int hour24, int hour12, int min, int sec,
                            String AM_PM) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        goTime.setText(format.format(dateSelected));
                    }
                    @Override
                    public void onCancel() {
                        Log.d("datetimepickerdialog", "canceled");
                    }
                });
        timePicker.set24HourFormat(true);
        timePicker.showDialog();
    }
    
    private OnClickListener mSubmitRegisterListener = new OnClickListener(){
        @Override
        public void onClick(View v){
            if(goTime.getText().toString().length() != 0){
                goTimeStr = goTime.getText().toString();
            }else{
                Toast.makeText(driverGoWorkRegisterActivity.getApplication(), "出发时间不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if(goNumber.getText().toString().length() != 0){
                goNumberStr = goNumber.getText().toString();
            }else{
                Toast.makeText(driverGoWorkRegisterActivity.getApplication(), "剩余座位不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if(passWay.getText().toString().length() != 0){
                passWayStr = passWay.getText().toString();
            }else{
                Toast.makeText(driverGoWorkRegisterActivity.getApplication(), "顺路地点不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if(homeAddress.getText().toString().length() != 0){
                homeAddressStr = homeAddress.getText().toString();
            }else{
                Toast.makeText(driverGoWorkRegisterActivity.getApplication(), "小区地址不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if(workAddress.getText().toString().length() != 0){
                workAddressStr = workAddress.getText().toString();
            }else{
                Toast.makeText(driverGoWorkRegisterActivity.getApplication(), "公司地址不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            
            submitRegister.setEnabled(false);

            if(isExceedActiveRoute()){
                Log.d(TAG,"KMA ...driver exceed route number");
                new AlertDialog.Builder(goWorkRegisterActivity)
                .setTitle(R.string.quit_hint)
                .setMessage(R.string.exceed_rout_num)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialoginterface, int which) {
                        //跳转到订单界面
                        driverGoWorkRegisterActivity.setType(driverGoWorkRegisterActivity.ViewDriver);
                        driverGoWorkRegisterActivity.init();
                    }

                }).show();
            }else{
                //司机界面调用insert插入数据库
                mBaseData.insertRoute(driverGoWorkRegisterActivity,MainActivity.getUserPhone(), MainActivity.getUserName(), goTimeStr,null/*number of passenger*/, homeAddressStr, workAddressStr, 
                        passWayStr/*passway*/, mBaseData.Identify_Driver, goNumberStr/*number of position*/, null/*name of passenger*/);
                driverGoWorkRegisterActivity.setType(driverGoWorkRegisterActivity.ViewDriver);
                driverGoWorkRegisterActivity.init();
            }
            // the driver had registered
        }
    };
    
    public boolean isExceedActiveRoute(){
        String currentTime = null;
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentTime = df.format(day).toString();
        
        List<Route> UserRoute = new ArrayList<Route>();
        String[] queryRouteInfo = new String[BaseData.lengthOfRoute];
        for(int i = 0; i < BaseData.lengthOfRoute; i++){  //get all of the user route 
            queryRouteInfo[i] = null;
        }
        
        // get the user phone number
        queryRouteInfo[BaseData.Index_Route_UserPhone ] = MainActivity.getUserPhone();
        // get the local time
        queryRouteInfo[BaseData.Index_Route_Time] = currentTime;
        //only query the passenger route
        queryRouteInfo[BaseData.Index_Route_Identify] = BaseData.Identify_Driver;
        
        UserRoute = mBaseData.searchByRouteColumn(getActivity(),queryRouteInfo);
        
        if(UserRoute.size() >= MainActivity.allowRegisterNumber)
            return true;  //超出限制数量范围
        else
            return false;
    }
    
    @Override
    public void onPushMessage(int type, String message1, String message2) {
        switch (type) {
        case MainActivity.REQUEST_CODE_MAP_ADDRESS_HOME:
            homeAddress.setText(message1);
            break;
        case MainActivity.REQUEST_CODE_MAP_ADDRESS_OFFICE:
            workAddress.setText(message1);
            break;
        default:
            break;
        }
    }
 
}
