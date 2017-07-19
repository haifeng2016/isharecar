package com.samsung.register;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.samsung.data.BaseData;
import com.samsung.data.SQLManager;
import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.Log;
import com.samsung.isharecar.MainActivity;
import com.samsung.isharecar.R;

public class SetNickNameSexFragment extends FragmentBase {

    protected static final int SCANNING_REQUEST_CODE = 0;
    private Button btn;
    private String TAG="SetNickNameSexFragment";
    private View view;
    private EditText nickName = null; 
    private RadioGroup radioGroup = null;
    private RadioButton radioButton = null;
    private String nickNameStr = null;
    private String userSex = null;
    private BaseData mBaseData = null;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nicknamesex, container,false);

        //设置标题栏
        loginActivity.childViewActionBarStyle(R.string.base_data);
        
        nickName = (EditText)view.findViewById(R.id.setnickname1);
        radioGroup = (RadioGroup)view.findViewById(R.id.radiogroup1);
        radioButton = (RadioButton)view.findViewById(R.id.radioButton1);//Get the default value
        userSex = radioButton.getText().toString();
        
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //在这个函数里面用来改变选择的radioButton的数值，以及与其值相关的 //任何操作，详见下文
                selectRadioBtn();
            }
        });
        
        btn = (Button)view.findViewById(R.id.savenicknamesex);
        btn.setOnClickListener(mylistener);
        
        if(MainActivity.mDebug)
            //get the data from SQLite
            mBaseData = new SQLManager(loginActivity);
        else{
            //get the date from network
            mBaseData = new SQLManager(loginActivity);
        }
        return view;
    }

    private OnClickListener mylistener= new OnClickListener(){

        @Override
        public void onClick(View v) {
            saveUser();//save the user info
            if(nickName.getText().toString().length() == 0){
                Toast.makeText(loginActivity.getApplication(), "昵称不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            new AlertDialog.Builder(wrapperActivity)
            .setTitle(R.string.quit_hint)
            .setMessage(R.string.setnamesexquithint)
            .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialoginterface, int which) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), PhoneRegisterDataEditActivity.class);
                    startActivityForResult(intent, 5000);
                }

            })
            .setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener() {
                //��ӷ��ذ�ť
                @Override
                public void onClick(DialogInterface dialoginterface, int which) {//响应事件  
                    Log.i("alertdialog"," 请保存数据！");
                    getActivity().finish();
                }  
            }).show();
        }
    };
    
    public void saveUser(){
        long result = 0;
        if(nickName.getText().toString().length() != 0){
            nickNameStr = nickName.getText().toString();
        }else{
            Toast.makeText(loginActivity.getApplication(), "昵称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(loginActivity.phone != null)
            result = mBaseData.insertUser(loginActivity, nickNameStr, loginActivity.phone, userSex, null, null, null,null,null,null,null,null,null);
        
        if(result >= 0){// insert successfully
            Log.d(TAG,"KMA ...insert success id:"+result);
            Log.d(TAG, "KMA ...nickNameStr:"+nickNameStr);
            Log.d(TAG, "KMA ...phone:"+(loginActivity.phone));
            MainActivity.setUserName(nickNameStr);
            MainActivity.setUserPhone(loginActivity.phone);
        }
    }
    
    private void selectRadioBtn(){
        radioButton = (RadioButton)view.findViewById(radioGroup.getCheckedRadioButtonId());
        userSex = radioButton.getText().toString();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
