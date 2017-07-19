package com.samsung.register;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.samsung.data.BaseData;
import com.samsung.data.Contact;
import com.samsung.data.SQLManager;
import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.MainActivity;
import com.samsung.isharecar.R;

import java.util.List;

public class RegisterSettingsSetPasswordFragment extends FragmentBase {
    
    private BaseData mBaseData = null;
    private List<Contact> contactList = null;
    private Contact contact = null;
    
    private LinearLayout oldPasswordLayout = null;
    private EditText tvOldPassword = null;
    private EditText tvNewPassword = null;
    private EditText tvNewPassword2 = null;
    private Button changePasswordButton  = null;
    
    private String oldPasswordStr = null;
    private String newPasswordStr = null;
    private String newPasswordStr2 = null;

    
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
        View view = inflater.inflate(R.layout.fragment_registersettings_setpassword1, container,false);
        //设置标题栏
        titleResId = R.string.password_settings;
        oldPasswordLayout = (LinearLayout)view.findViewById(R.id.setpassword);
        tvOldPassword = (EditText)view.findViewById(R.id.passwordinput);
        tvNewPassword = (EditText)view.findViewById(R.id.passwords);
        tvNewPassword2 = (EditText)view.findViewById(R.id.passwords2);
        changePasswordButton = (Button)view.findViewById(R.id.savepassword);
        changePasswordButton.setOnClickListener(changePWSOnClickListener);
        

        if(MainActivity.mDebug)
            //get the data from SQLite
            mBaseData = new SQLManager(getActivity());
        else{
            //get the date from network
            mBaseData = new SQLManager(getActivity());
        }
        
        if(MainActivity.getUserPhone() != null){
            contactList = mBaseData.searchByContactColumn(getActivity(), mBaseData.USER_COLUM_PHONE, MainActivity.getUserPhone());
            if(contactList.size() > 0)
                contact = contactList.get(0);
        }
        if((contact != null) && (contact.get_passWord() != null)){
            oldPasswordLayout.setVisibility(View.VISIBLE);
        }else{
            oldPasswordLayout.setVisibility(View.GONE);
        }
        return view;
    }
    
    private OnClickListener changePWSOnClickListener = new OnClickListener(){
        @Override
        public void onClick(View v){
            if((tvOldPassword != null) && (tvOldPassword.getText().toString().length() != 0)){
                oldPasswordStr = tvOldPassword.getText().toString();
                if(!oldPasswordStr.equals(contact.get_passWord())){
                    Toast.makeText(registerSettingsActivity.getApplication(), "输入旧密码错误", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            if(tvNewPassword.getText().toString().length() != 0){
                newPasswordStr = tvNewPassword.getText().toString();
            }else{
                Toast.makeText(registerSettingsActivity.getApplication(), "新密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            if(tvNewPassword2.getText().toString().length() != 0){
                newPasswordStr2 = tvNewPassword2.getText().toString();
            }else{
                Toast.makeText(registerSettingsActivity.getApplication(), "再次新密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            if(!newPasswordStr2.equals(newPasswordStr)){
                Toast.makeText(registerSettingsActivity.getApplication(), "两次新密码输入不同", Toast.LENGTH_SHORT).show();
                return;
            }else{
                contact.set_passWord(newPasswordStr);
                mBaseData.updateUser(getActivity(), contact);
                Toast.makeText(registerSettingsActivity.getApplication(), "密码修改成功", Toast.LENGTH_SHORT).show();
                //TODO 
                registerSettingsActivity.doPopFragmentAction();
            }
        }
    };

}
