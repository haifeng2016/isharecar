package com.samsung.register;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.R;

public class RegisterSettingsAboutUsFragment extends FragmentBase {

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
        View view = inflater.inflate(R.layout.fragment_registersettings_aboutus, container,false);

        //设置标题栏
        titleResId = R.string.aboutus;

        (view.findViewById(R.id.aboutus)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "已经是最新版本", Toast.LENGTH_LONG).show();
            }
        });
        
        (view.findViewById(R.id.aboutus2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment functionintroductionfragment = new RegisterSettingsFunctionIntroductionFragment();
                registerSettingsActivity.doPushFramgentAction(functionintroductionfragment);
            }
        });
        
        (view.findViewById(R.id.aboutus1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment privacyfragment = new RegisterSettingsPrivacyFragment();
                registerSettingsActivity.doPushFramgentAction(privacyfragment);
            }
        });

        return view;
    }
    
}
