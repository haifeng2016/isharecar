package com.samsung.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.samsung.data.BaseData;
import com.samsung.data.Contact;
import com.samsung.data.SQLManager;
import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.MainActivity;
import com.samsung.isharecar.R;

import java.util.List;

public class PhoneLoginFragment extends FragmentBase implements View.OnClickListener {

    private Button savecord;
    private TextView phonelogin;
    private View view;
    private BaseData mBaseData = null;
    private EditText tvPhoneNum = null;
    private EditText tvPassword = null;

    private String phoneNumStr = null;
    private String PasswordStr = null;

    private List<Contact> contactList = null;
    private Contact contact = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phonelogin, container, false);

        //设置标题栏
        loginActivity.childViewActionBarStyle(R.string.password_login);

        if(MainActivity.mDebug)
            //get the data from SQLite
            mBaseData = new SQLManager(getActivity());
        else{
            //get the date from network
            mBaseData = new SQLManager(getActivity());
        }

        init();
        return view;
    }

    private void init() {
        savecord = (Button) view.findViewById(R.id.savecord);
        phonelogin = (TextView) view.findViewById(R.id.phonelogin);
        tvPhoneNum = (EditText)view.findViewById(R.id.phone);
        tvPassword = (EditText)view.findViewById(R.id.cord);

        savecord.setOnClickListener(this);
        phonelogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.savecord: // 点击“登录”按钮直接登录
            if(tvPhoneNum.getText().toString().length() != 0){
                phoneNumStr = tvPhoneNum.getText().toString();
            }else{
                Toast.makeText(loginActivity.getApplication(), "号码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            if(tvPassword.getText().toString().length() != 0){
                PasswordStr = tvPassword.getText().toString();
            }else{
                Toast.makeText(loginActivity.getApplication(), "密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            contactList = mBaseData.searchByContactColumn(getActivity(),mBaseData.USER_COLUM_PHONE, phoneNumStr);
            if(contactList.size() > 0)
                contact = contactList.get(0);
            if((contact != null) && (contact.get_passWord().equals(PasswordStr))){
                Toast.makeText(loginActivity.getApplication(), "登入成功", Toast.LENGTH_SHORT).show();
                MainActivity.setUserName(contact.get_name());
                MainActivity.setUserPhone(contact.get_phone_number());
                getActivity().finish();
            }else{
                Toast.makeText(loginActivity.getApplication(), "用户名不存在或者密码错误", Toast.LENGTH_SHORT).show();
                return;
            }
            break;
            case R.id.phonelogin: // 点击“手机号快捷登录”跳转到前一页
            loginActivity.doPopFragmentAction();
            break;
        }
    }

}