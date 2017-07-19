package com.samsung.passenger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.R;

public class OffWorkRegisterFragment extends FragmentBase{
    
    private static View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.passenger_offwork_register_fragment, container, false);
        goWorkRegisterActivity = (GoWorkRegisterActivity) getActivity();

        goWorkRegisterActivity.childViewActionBarStyle(R.string.passenger_off);
        return view;
    }

    @Override
    public void onPushMessage(int type, String message1, String message2) {
    }
 
}
