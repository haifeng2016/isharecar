package com.samsung.register;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.Log;
import com.samsung.isharecar.MainActivity;
import com.samsung.isharecar.R;

public class RegisterSettingsFragment extends FragmentBase {

    protected static final int SCANNING_REQUEST_CODE = 0;
    private Button btn;
    private String TAG="RegisterSettingsFragment";
    private View view;

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
        view = inflater.inflate(R.layout.fragment_register_settings, container,false);

        //设置标题栏
        //registerSettingsActivity.childViewActionBarStyle(R.string.setting);
        titleResId = R.string.setting;

        btn = (Button)view.findViewById(R.id.quitbutton);
        btn.setOnClickListener(mylistener);
        
        (view.findViewById(R.id.settings_aboutus)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment aboutusfragment = new RegisterSettingsAboutUsFragment();
                registerSettingsActivity.doPushFramgentAction(aboutusfragment);
            }
        });

        (view.findViewById(R.id.settings_addr)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment setaddrfragment = new RegisterSettingsSetAddrFragment();
                registerSettingsActivity.doPushFramgentAction(setaddrfragment);
            }
        });

        (view.findViewById(R.id.settings_password)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.getUserPhone() != null){
                    Fragment setpasswordfragment = new RegisterSettingsSetPasswordFragment();
                    registerSettingsActivity.doPushFramgentAction(setpasswordfragment);
                }else{
                    Toast.makeText(registerSettingsActivity.getApplication(), "请先登入", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        return view;
    }

    private OnClickListener mylistener= new OnClickListener(){
        @Override
        public void onClick(View v) {
            //按了button按钮以后，让他弹出一个对话框
            new AlertDialog.Builder(registerSettingsActivity)
                    .setTitle(R.string.quit_hint)
                    .setMessage(R.string.quit_detail)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialoginterface, int which) {
                            Log.v(TAG,"你点击了确定");
                            //activity finish
                            Intent intent = new Intent();
                            intent.setClass(registerSettingsActivity, PhoneRegisterActivity.class);
                            startActivityForResult(intent, MainActivity.REQUEST_CODE_MAP_ADDRESS_HOME);
                        }
                    })
                    .setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener() {
                        //添加返回按钮
                        @Override
                        public void onClick(DialogInterface dialoginterface, int which) {//响应事件
                            Log.i("alertdialog"," 请保存数据！");
                    dialoginterface.dismiss();
                }
            }).show();
        }
    };

}
