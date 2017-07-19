package com.samsung.register;

import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.samsung.data.BaseData;
import com.samsung.data.Contact;
import com.samsung.data.SQLManager;
import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.FragmentBaseInterface;
import com.samsung.isharecar.MainActivity;
import com.samsung.isharecar.R;

import org.json.JSONObject;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class PhoneRegisterActivity extends FragmentActivity implements OnClickListener,FragmentBaseInterface {

    private String TAG = "PhoneRegisterActivity";
    private Button getCode;
    private Button Identity;
    private EditText PhoneEd;
    private EditText CodeEd;
    private TextView passwordlogin;
    private String AppKey ="119234e993497";
    private String APPSECRET ="06cfd43b7199d5bd62c76854f3b08304";
    public String phone;

    //定义FragmentManager对象
    FragmentManager fManager;
    private String CodeText;
    private ImageView mActionBarLayout = null;
    private TextView mActionView = null;
    private ImageView mActionIcon = null;
    private BaseData mBaseData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_phoneregister);
        fManager = getSupportFragmentManager();
        fManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });
        init();

        //SMSSDK初始化与回调
        SMSSDK.initSDK(this, AppKey, APPSECRET);
        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);
        
        if(MainActivity.mDebug)
            //get the data from SQLite
            mBaseData = new SQLManager(this);
        else{
            //get the date from network
            mBaseData = new SQLManager(this);
        }
    }

    private void init() {
        getCode = (Button) findViewById(R.id.getCode);
        Identity = (Button) findViewById(R.id.Indentity);
        PhoneEd = (EditText) findViewById(R.id.PhoneEd);
        CodeEd = (EditText) findViewById(R.id.Code);
        passwordlogin = (TextView) findViewById(R.id.passwordlogin);
        mActionView = (TextView) findViewById(R.id.actionbarhead);
        mActionBarLayout = (ImageView) findViewById(R.id.actionbaricon);
        mActionIcon = (ImageView) findViewById(R.id.actionbaricon);
        mActionBarLayout.setOnClickListener(this);
        getCode.setOnClickListener(this);
        Identity.setOnClickListener(this);
        passwordlogin.setOnClickListener(this);
    }

    private class SmsObserver extends ContentObserver {
        public SmsObserver(Handler handler) {
            super(handler);
        }
        @Override
        public void onChange(boolean selfChange) {
            StringBuilder sb = new StringBuilder();
            Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
            cursor.moveToNext();
            sb.append("body=" + cursor.getString(cursor.getColumnIndex("body")));
            System.out.println(sb.toString());
            Pattern pattern = Pattern.compile("[^0-9]");
            Matcher matcher = pattern.matcher(sb.toString());
            CodeText = matcher.replaceAll("");
            CodeEd.setText(CodeText);
            cursor.close();
            super.onChange(selfChange);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.getCode: // 获取验证码的过程.
            if (!TextUtils.isEmpty(PhoneEd.getText().toString())) {
                getContentResolver().registerContentObserver(Uri.parse("content://sms"), true,new SmsObserver(new Handler()));
                SMSSDK.getVerificationCode("86", PhoneEd.getText().toString());
                phone = PhoneEd.getText().toString();
            } else {
                Toast.makeText(PhoneRegisterActivity.this, "电话号码不能为空", Toast.LENGTH_LONG).show();
            }
            break;
            
        case R.id.Indentity:
            SMSSDK.submitVerificationCode("86", phone, CodeEd.getText().toString());
            break;

        case R.id.passwordlogin:
            Fragment setpasswordfragment = new PhoneLoginFragment();
            doPushFramgentAction(setpasswordfragment);
            break;
            
        case R.id.actionbaricon:
            doPopFragmentAction();
            break;
        }
    }
    
    private void switchToDetailsPage() {
        if(queryUserPhone(PhoneEd.getText().toString())){
            //进入完善个人资料界面
            Intent intent = new Intent();
            intent.setClass(this, PhoneRegisterDataEditActivity.class);
            startActivityForResult(intent, 5000);
        }else{
            Fragment setnicknamesexfragment = new SetNickNameSexFragment();
            phone = PhoneEd.getText().toString();
            doPushFramgentAction(setnicknamesexfragment);
        }
    }
    
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Toast.makeText(getApplicationContext(), "提交验证码成功",Toast.LENGTH_SHORT).show();
                    //当输入正确的验证码后，则此时button可以被点击，然后跳转到对应页面
                    switchToDetailsPage();
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    // 验证码已经发送
                    Toast.makeText(getApplicationContext(), "验证码已经发送",Toast.LENGTH_SHORT).show();
                }
            } else {
                int status = 0;
                try {
                    ((Throwable) data).printStackTrace();
                    Throwable throwable = (Throwable) data;
                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");
                    status = object.optInt("status");
                    if (!TextUtils.isEmpty(des)) {
                        Toast.makeText(PhoneRegisterActivity.this,des,Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    //SMSLog.getInstance().w(e);
                }
            }
        }

    };
    
    private boolean queryUserPhone(String phoneNumber){
        List<Contact> mContact = null;
        mContact = mBaseData.searchByContactColumn(this,BaseData.USER_COLUM_PHONE,phone);
        if((mContact != null) && (mContact.size() > 0)){
            MainActivity.setUserName(mContact.get(0).get_name());
            MainActivity.setUserPhone(mContact.get(0).get_phone_number());
            return true;
        }
        else
            return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String result = bundle.getString("operation");
                String login = bundle.getString("in");
                this.finish();
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    /*更新标题栏的*/
    public void childViewActionBarStyle(int title) {//0 -> hide, >0 -> show icon
        if (mActionView != null) {
            mActionView.setText(title > 0 ? getString(title): getString(R.string.easylogin));
        }
        if (mActionIcon != null) {
            mActionIcon.setVisibility(View.VISIBLE);
        }
    }

    //按键响应压栈
    public void doPushFramgentAction(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fManager.beginTransaction();
        String tag = fragment.getClass().getSimpleName() + "_" + fragment.hashCode();
        fragmentTransaction.add(R.id.phoneregister, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        Fragment currentFragment = fManager.findFragmentById(R.id.phoneregister);
        if (null != currentFragment) {
            fragmentTransaction.hide(currentFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
        fManager.executePendingTransactions();
    }

    public void doPopFragmentAction() {
        childViewActionBarStyle(0);
        if (fManager.getBackStackEntryCount() > 1) {
            fManager.popBackStackImmediate();
        } else if (fManager.getBackStackEntryCount() == 1){
            fManager.popBackStackImmediate();
        } else {
            finish();
        }
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
        //getContentResolver().unregisterContentObserver(new SmsObserver(handler));
    }

    @Override
    public void setSelectedFragment(FragmentBase selectedFragment) {
        // To do something
    }
}