package com.samsung.driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.R;
import com.samsung.register.PhoneRegisterActivity;

public class DriverGoToRegisterFragment extends FragmentBase{
    
    private static View view;
    private Button driverRegisterButton = null;
    protected static final int SCANNING_REQUEST_CODE = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_driver_goto_register, container, false);
        //设置标题栏
        driverGoWorkRegisterActivity.childViewActionBarStyle(R.string.register_driver);
        driverRegisterButton = (Button) view.findViewById(R.id.register_to_driver);
        driverRegisterButton.setEnabled(true);
        driverRegisterButton.setOnClickListener(mSubmitRegisterListener);
        return view;
    }
    
    private OnClickListener mSubmitRegisterListener = new OnClickListener(){
        @Override
        public void onClick(View v){
            //enter the register activity
            Intent intent = new Intent();
            intent.setClass(driverGoWorkRegisterActivity, PhoneRegisterActivity.class);
            startActivityForResult(intent, SCANNING_REQUEST_CODE);
        }
    };
}
