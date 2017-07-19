package com.samsung.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsung.data.BaseData;
import com.samsung.data.Contact;
import com.samsung.data.SQLManager;
import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.FragmentBaseInterface;
import com.samsung.isharecar.MainActivity;
import com.samsung.isharecar.R;

import java.util.List;

public class PhoneRegisterDataEditActivity extends FragmentActivity implements OnClickListener, FragmentBaseInterface {

    public BaseData mBaseData = null;
    public List<Contact> contactList = null;
    public Contact contact = null; //get the contact object for the edit activity
    private ImageView mActionBarLayout = null;
    private TextView mActionView = null;
    private ImageView mActionIcon = null;
    private FragmentBase fragmentHandler = null;

    //定义FragmentManager对象
    FragmentManager fManager;

    @Override
    public void onAttachFragment(Fragment fragment) {
        try {
            fragmentHandler = (FragmentBase)fragment;
        } catch (ClassCastException e) {
            throw new ClassCastException(this.toString() + " must implement");
        }
        super.onAttachFragment(fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_phoneregister_dataedit_activity);
        
        mActionView = (TextView) findViewById(R.id.actionbarhead);
        mActionBarLayout = (ImageView) findViewById(R.id.actionbaricon);
        mActionIcon = (ImageView) findViewById(R.id.actionbaricon);
        mActionBarLayout.setOnClickListener(this);
        
        if(MainActivity.mDebug)
            //get the data from SQLite
            mBaseData = new SQLManager(this);
        else{
            //get the date from network
            mBaseData = new SQLManager(this);
        }
        if(MainActivity.getUserPhone() != null){
            contactList = mBaseData.searchByContactColumn(this, mBaseData.USER_COLUM_PHONE, MainActivity.getUserPhone());
            if(contactList.size() > 0)
                contact = contactList.get(0);
        }
        fManager = getSupportFragmentManager();
        doPushFramgentAction(new PhoneRegisterDataEditFragment());
    }

    //以下方法是更新标题栏title的
    public void childViewActionBarStyle(int title) {//0 -> hide, >0 -> show icon
        if (mActionView != null) {
            mActionView.setText(title > 0 ? getString(title): getString(R.string.data_edit));
        }
        if (mActionIcon != null) {
            mActionIcon.setVisibility(View.VISIBLE);
        }
    }

    //重写onClick事件
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
            case R.id.actionbaricon:
                onBackPressed();
                break;
            default:
                break;
            }
        }

    //按键响应压栈
    public void doPushFramgentAction(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fManager.beginTransaction();
        String tag = fragment.getClass().getSimpleName() + "_" + fragment.hashCode();
        fragmentTransaction.add(R.id.registersettings, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        Fragment currentFragment = fManager.findFragmentById(R.id.registersettings);
        if (null != currentFragment) {
            fragmentTransaction.hide(currentFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
        fManager.executePendingTransactions();
    }
    
    public void doPopFragmentAction() {
        if (fManager.getBackStackEntryCount() > 1) {
            fManager.popBackStackImmediate();
            childViewActionBarStyle(0);//退回上一个页面的时候，更新此页面的title
        } else {
            finish();
        }
    }

    @Override
    public void setSelectedFragment(FragmentBase selectedFragment) {
        //To do something
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        childViewActionBarStyle(0); //退回上一个页面的时候，更新此页面的title
        if (fManager.getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("operation", "destory");
            bundle.putString("login", "in");
            resultIntent.putExtras(bundle);
            backToMainActivity(resultIntent);
        }
    }
    
    public void backToMainActivity(Intent intent) {
        this.setResult(RESULT_OK, intent);
        this.finish();
    }
}