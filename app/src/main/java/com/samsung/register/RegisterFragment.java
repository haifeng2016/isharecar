package com.samsung.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.Log;
import com.samsung.isharecar.MainActivity;
import com.samsung.isharecar.R;

public class RegisterFragment extends FragmentBase {
    
    private static final String TAG = RegisterFragment.class.getSimpleName();
    
    protected static final int SCANNING_REQUEST_CODE = 0;
    private MainActivity mainActivity;
    private TextView loginText = null;
    private ImageView loginImage = null;
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            if (!(activity instanceof MainActivity)) {
                throw new RuntimeException(activity.toString()
                        + " must implement OnFragmentInteractionListener");
            } else {
                mainActivity = (MainActivity)activity;
            }
        } catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container,false);

        loginText = (TextView)view.findViewById(R.id.registerTextView);
        loginImage = (ImageView) view.findViewById(R.id.registerImageView);
        
        (view.findViewById(R.id.login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(MainActivity.getUserName() == null){  //don't register 
                    intent.setClass(mainActivity, PhoneRegisterActivity.class);
                    startActivityForResult(intent, MainActivity.REQUEST_CODE_LOGIN_STATE);
                }else{
                    //enter the complete user info page
                    intent.setClass(mainActivity, PhoneRegisterDataEditActivity.class);
                    startActivityForResult(intent, 5000);
                }
            }
        });
        
        (view.findViewById(R.id.pinche)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mainActivity, ShareCarsGuideActivity.class);
                startActivityForResult(intent, SCANNING_REQUEST_CODE);
            }
        });
        
        (view.findViewById(R.id.settings)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mainActivity, RegisterSettingsActivity.class);
                startActivityForResult(intent, SCANNING_REQUEST_CODE);
            }
        });
        return view;
    }
    
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "KMA ...onResume()");
        if(MainActivity.getUserName() != null){
            loginText.setText(MainActivity.getUserName());
            loginImage.setImageResource(R.drawable.loginicon);
        }else{
            Log.d(TAG, "KMA ...don't login");
        }
    }

//    @Override
//    public void updateContent(String key, String content) {
//        Log.d(TAG, "updateContent: " + key + "\t" + content);
//        if (imageView == null) {
//            return;
//        }
//        if ("login".equalsIgnoreCase(key)) {
//            if ("in".equalsIgnoreCase(content)) {
//                imageView.setImageResource(R.drawable.login);
//            } else if ("out".equalsIgnoreCase(content)) {
//                imageView.setImageResource(R.drawable.register);
//            }
//        }
//    }
}
