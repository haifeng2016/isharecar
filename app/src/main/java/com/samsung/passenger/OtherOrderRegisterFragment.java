package com.samsung.passenger;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.samsung.data.BaseData;
import com.samsung.data.SQLManager;
import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.Log;
import com.samsung.isharecar.MainActivity;
import com.samsung.isharecar.R;
import com.samsung.register.PhoneRegisterActivity;
import com.samsung.register.RegisterSettingsSetAddrBaiduMapActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OtherOrderRegisterFragment extends FragmentBase{
    
    private static View view;
    protected static final int SCANNING_REQUEST_CODE = 0;
    
    private BaseData mBaseData = null;
    String TAG = "GoWorkRegisterFragment";
    
    private EditText goTime = null;
    private EditText goNumber = null;
    private EditText homeAddress = null;
    private EditText workAddress = null;
    private TextView viewCharge = null;
    private Button submitRegister = null;
    private String goTimeStr = null;
    private String goNumberStr = null;
    private String homeAddressStr = null;
    private String workAddressStr = null;
    private String viewChargeStr = null;
    public MainActivity mMainActivity = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.passenger_otherorder_register_fragment, container, false);
        //goWorkRegisterActivity.updateActionBar(GoWorkRegisterActivity.GoWork);
        //设置标题栏
        goWorkRegisterActivity.childViewActionBarStyle(R.string.passenger_otherorder);

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
    
    void initView(){
        goTime = (EditText) view.findViewById(R.id.passenger_go_time);
        goTime.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                createDateTimeDialog();
            }
        });
        goNumber = (EditText) view.findViewById(R.id.passenger_go_number);
        homeAddress = (EditText) view.findViewById(R.id.passenger_home_add);
        workAddress = (EditText) view.findViewById(R.id.passenger_work_add);
        //viewCharge = (TextView) view.findViewById(R.id.passenger_charge_display);
        submitRegister = (Button) view.findViewById(R.id.passenger_reservation);
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
                goWorkRegisterActivity.startActivityForResult(intent, goWorkRegisterActivity.REQUEST_CODE_MAP_ADDRESS_HOME);
            }
        });
        
        workAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), RegisterSettingsSetAddrBaiduMapActivity.class);
                goWorkRegisterActivity.startActivityForResult(intent, goWorkRegisterActivity.REQUEST_CODE_MAP_ADDRESS_OFFICE);
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
                Toast.makeText(goWorkRegisterActivity.getApplication(), "出发时间不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if(goNumber.getText().toString().length() != 0){
                goNumberStr = goNumber.getText().toString();
            }else{
                Toast.makeText(goWorkRegisterActivity.getApplication(), "出发人数不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if(homeAddress.getText().toString().length() != 0){
                homeAddressStr = homeAddress.getText().toString();
            }else{
                Toast.makeText(goWorkRegisterActivity.getApplication(), "小区地址不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if(workAddress.getText().toString().length() != 0){
                workAddressStr = workAddress.getText().toString();
            }else{
                Toast.makeText(goWorkRegisterActivity.getApplication(), "公司地址不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            
            submitRegister.setEnabled(false);
            if(MainActivity.getUserName() != null){//user had registered
                //调用insert插入数据库
                //调用insert插入数据库
                mBaseData.insertRoute(goWorkRegisterActivity,MainActivity.getUserPhone(), MainActivity.getUserName(), goTimeStr,goNumberStr, homeAddressStr, workAddressStr, null,
                        "0"/*identify 0: passenger; 1: driver*/, null/*number of position*/, null/*name of passenger*/);
                goWorkRegisterActivity.setType(GoWorkRegisterActivity.ViewDriver);
                goWorkRegisterActivity.init();
            }else{ //enter the register activity
                Intent intent = new Intent();
                intent.setClass(goWorkRegisterActivity, PhoneRegisterActivity.class);
                startActivityForResult(intent, SCANNING_REQUEST_CODE);
            }
        }
    };
 
    @Override
    public void onPushMessage(int type, String message1, String message2) {
        switch (type) {
        case MainActivity.REQUEST_CODE_MAP_ADDRESS_HOME:
            Log.e("onPushMessage", "REQUEST_CODE_MAP_ADDRESS_HOME");
            Log.e("onPushMessage", "message1 " + message1);
            Log.e("onPushMessage", "message2 " + message2);
            homeAddress.setText(message1);
            //homeAddrTextView.setText(message2);
            break;
        case MainActivity.REQUEST_CODE_MAP_ADDRESS_OFFICE:
            Log.e("onPushMessage", "REQUEST_CODE_MAP_ADDRESS_OFFICE");
            workAddress.setText(message1);
            //officeAddrTextView.setText(message2);
            break;

        default:
            break;
        }
    }

}
