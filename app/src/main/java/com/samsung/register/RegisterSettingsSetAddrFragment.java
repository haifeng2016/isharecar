package com.samsung.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.MainActivity;
import com.samsung.isharecar.R;

public class RegisterSettingsSetAddrFragment extends FragmentBase {
    
    private TextView homeNameTextView;
    private TextView homeAddrTextView;
    private TextView officeNameTextView;
    private TextView officeAddrTextView;
    
    @Override
    public void onAttach(Activity activity) {
        if (activity instanceof RegisterSettingsActivity) {
            registerSettingsActivity = (RegisterSettingsActivity) activity;
        }
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registersettings_setaddr, container,false);
        //设置标题栏
        titleResId = R.string.addr_settings;

        homeNameTextView = (TextView) view.findViewById(R.id.sethomenametextview);
        homeAddrTextView = (TextView) view.findViewById(R.id.sethomeaddrtextview);
        officeNameTextView = (TextView) view.findViewById(R.id.setofficenametextview);
        officeAddrTextView = (TextView) view.findViewById(R.id.setofficeaddrtextview);
        
        (view.findViewById(R.id.setaddr)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), RegisterSettingsSetAddrBaiduMapActivity.class);
                registerSettingsActivity.startActivityForResult(intent, registerSettingsActivity.REQUEST_CODE_MAP_ADDRESS_HOME);
            }
        });

        (view.findViewById(R.id.setaddr1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), RegisterSettingsSetAddrBaiduMapActivity.class);
                registerSettingsActivity.startActivityForResult(intent, registerSettingsActivity.REQUEST_CODE_MAP_ADDRESS_OFFICE);
            }
        });
        return view;
    }

    @Override
    public void onPushMessage(int type, String message1, String message2) {
        switch (type) {
        case MainActivity.REQUEST_CODE_MAP_ADDRESS_HOME:
            Log.e("onPushMessage", "REQUEST_CODE_MAP_ADDRESS_HOME");
            Log.e("onPushMessage", "message1 " + message1);
            Log.e("onPushMessage", "message2 " + message2);
            homeNameTextView.setText(message1);
            homeAddrTextView.setText(message2);
            break;
        case MainActivity.REQUEST_CODE_MAP_ADDRESS_OFFICE:
            Log.e("onPushMessage", "REQUEST_CODE_MAP_ADDRESS_OFFICE");
            officeNameTextView.setText(message1);
            officeAddrTextView.setText(message2);
            break;
        default:
            break;
        }
    }

}
