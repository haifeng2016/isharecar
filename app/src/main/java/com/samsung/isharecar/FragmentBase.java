package com.samsung.isharecar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.samsung.driver.DriverGoWorkRegisterActivity;
import com.samsung.passenger.GoWorkRegisterActivity;
import com.samsung.register.IPushMessage;
import com.samsung.register.PhoneRegisterActivity;
import com.samsung.register.PhoneRegisterDataEditActivity;
import com.samsung.register.RegisterSettingsActivity;
import com.samsung.register.ShareCarsGuideActivity;

public abstract class FragmentBase extends Fragment implements IPushMessage, com.samsung.isharecar.IFragmentUpdate {

    protected com.samsung.isharecar.FragmentBaseInterface FragmentBaseInterface;
    protected com.samsung.isharecar.MainActivity mainActivity;
    protected FragmentActivity wrapperActivity;
    protected PhoneRegisterActivity loginActivity;
    protected PhoneRegisterDataEditActivity dataEditActivity;
    protected GoWorkRegisterActivity goWorkRegisterActivity;
    protected ShareCarsGuideActivity shareCarsGuideActivity;
    protected RegisterSettingsActivity registerSettingsActivity;
    protected DriverGoWorkRegisterActivity driverGoWorkRegisterActivity;
    
    protected final Handler handler = new Handler(Looper.getMainLooper());

    protected String title = "";
    protected int titleResId = 0;

    /**
     * 所有继承FragmentBase的子类都将在这个方法中实现物理Back键按下后的逻辑
     * FragmentActivity捕捉到物理返回键点击事件后会首先询问Fragment是否销毁该事件
     * 如果没有Fragment消息时FragmentActivity自己才会销毁该事件
     */
    
    @Override
    public void onBackPressed() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            if ((activity instanceof com.samsung.isharecar.MainActivity)) {
                mainActivity = (com.samsung.isharecar.MainActivity)activity;
                wrapperActivity = (com.samsung.isharecar.MainActivity)activity;
            } 
            else if (activity instanceof PhoneRegisterActivity){
                loginActivity = (PhoneRegisterActivity)activity;
                wrapperActivity = (PhoneRegisterActivity)activity;
            }
            else if (activity instanceof PhoneRegisterDataEditActivity) {
                dataEditActivity = (PhoneRegisterDataEditActivity)activity;
            }
            else if (activity instanceof GoWorkRegisterActivity) {
                goWorkRegisterActivity = (GoWorkRegisterActivity)activity;
            }
            else if (activity instanceof DriverGoWorkRegisterActivity) {
                driverGoWorkRegisterActivity = (DriverGoWorkRegisterActivity)activity;
            }
            else if (activity instanceof ShareCarsGuideActivity) {
                shareCarsGuideActivity = (ShareCarsGuideActivity) activity;
            }
            else if (activity instanceof RegisterSettingsActivity) {
                registerSettingsActivity = (RegisterSettingsActivity) activity;
            }
        } catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!(getActivity() instanceof com.samsung.isharecar.FragmentBaseInterface)){
            throw new ClassCastException("Hosting Activity must implement BackHandledInterface");
        }else{
            this.FragmentBaseInterface = (com.samsung.isharecar.FragmentBaseInterface)getActivity();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //告诉FragmentActivity，当前Fragment在栈顶
        FragmentBaseInterface.setSelectedFragment(this);
    }

    @Override
    public void onPushMessage(int type, String message1, String message2) {
        
    }
    
    @Override
    public void updateActionBarView() {
        if (registerSettingsActivity == null) {
            return;
        }
        registerSettingsActivity.childViewActionBarStyle(titleResId);
    }
    
    @Override
    public void updateContent(String key, String content) {
        // To do something
    }

}
