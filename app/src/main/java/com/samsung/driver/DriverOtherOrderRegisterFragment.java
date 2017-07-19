package com.samsung.driver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.R;

public class DriverOtherOrderRegisterFragment extends FragmentBase{
    
    private static View view;
    String TAG = "GoWorkRegisterFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.driver_otherorder_register_fragment, container, false);
        driverGoWorkRegisterActivity = (DriverGoWorkRegisterActivity)getActivity();

        driverGoWorkRegisterActivity.childViewActionBarStyle(R.string.driver_otherorder);
        return view;
    }

}
