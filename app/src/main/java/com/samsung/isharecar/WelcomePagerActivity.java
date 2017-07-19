package com.samsung.isharecar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class WelcomePagerActivity extends FragmentActivity {
    
    private static final int TIME = 2000;
    private static final int GO_HOME = 1000;

    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
            case GO_HOME:
                goHome();
                break;
            }
        };
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_welcome_pager_activity);
        init();
    }
    
    private void init(){
        mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
    }
    
    private void goHome(){
        Intent i = new Intent(WelcomePagerActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }

}
