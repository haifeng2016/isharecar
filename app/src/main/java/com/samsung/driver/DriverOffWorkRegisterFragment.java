package com.samsung.driver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.R;

public class DriverOffWorkRegisterFragment extends FragmentBase{
    
    private static View view;
    String TAG = "OffWorkRegisterFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.driver_offwork_register_fragment, container, false);
        driverGoWorkRegisterActivity = (DriverGoWorkRegisterActivity)getActivity();

        driverGoWorkRegisterActivity.childViewActionBarStyle(R.string.driver_off);
        return view;
    }

}
