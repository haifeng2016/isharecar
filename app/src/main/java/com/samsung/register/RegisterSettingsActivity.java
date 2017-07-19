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
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.FragmentBaseInterface;
import com.samsung.isharecar.IFragmentUpdate;
import com.samsung.isharecar.R;

public class RegisterSettingsActivity extends FragmentActivity implements OnClickListener,FragmentBaseInterface {

    private int type = 0;
    protected String title = "";

    //定义静态变量用于标题栏
    private ImageView mActionBarLayout = null;

    private TextView mActionView = null;
    private ImageView mActionIcon = null;
    protected FragmentManager fManager;
    private FragmentBase fragmentBase;
    
    public static final int REQUEST_CODE_MAP_ADDRESS_OFFICE = 0x001;
    public static final int REQUEST_CODE_MAP_ADDRESS_HOME = 0x002;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_register_settings_activity);
        fManager = getSupportFragmentManager();
        fManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment fragment = fManager.findFragmentById(R.id.registersettings);
                if (fragment instanceof IFragmentUpdate) {
                    IFragmentUpdate sf = (IFragmentUpdate) fragment;
                    sf.updateActionBarView();
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }
            }
        });

        /*在onCreate中一定要加入下面两行，不然无法压入栈*/
        doPushFramgentAction(new RegisterSettingsFragment());

        mActionView = (TextView) findViewById(R.id.actionbarhead);
        mActionBarLayout = (ImageView) findViewById(R.id.actionbaricon);
        mActionIcon = (ImageView) findViewById(R.id.actionbaricon);
        mActionBarLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
        case R.id.actionbaricon:
            doPopFragmentAction();
            break;
        default:
            break;
        }
    }

    //以下方法是更新标题栏title的
    public void childViewActionBarStyle(int title) {//0 -> hide, >0 -> show icon
        if (mActionView != null) {
            mActionView.setText(title > 0 ? getString(title): getString(R.string.app_name));
        }
        if (mActionIcon != null) {
            mActionIcon.setVisibility(View.VISIBLE);
        }
    }

    //按键响应压栈
    public void doPushFramgentAction(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fManager.beginTransaction();
        String tag = fragment.getClass().getSimpleName() + "_" + fragment.hashCode();
        fragmentTransaction.add(R.id.registersettings, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        Fragment currentFragment = fManager.findFragmentById(R.id.registersettings);
        if (currentFragment != null ) {
            fragmentTransaction.hide(currentFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
        fManager.executePendingTransactions();
    }

    public void doPopFragmentAction() {
        if (fManager.getBackStackEntryCount() > 2) {
            fManager.popBackStackImmediate();
        } else if (fManager.getBackStackEntryCount() == 2){
            fManager.popBackStackImmediate();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        doPopFragmentAction();
    }

    public int getType() {
        return type;
    }

    public void setType(int mType) {
        type = mType;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
            case REQUEST_CODE_MAP_ADDRESS_OFFICE:
            {
                fragmentBase.onPushMessage(REQUEST_CODE_MAP_ADDRESS_OFFICE,
                        intent.getStringExtra("name"), intent.getStringExtra("address"));
            }
            break;
            case REQUEST_CODE_MAP_ADDRESS_HOME:
            {
                fragmentBase.onPushMessage(REQUEST_CODE_MAP_ADDRESS_HOME,
                        intent.getStringExtra("name"), intent.getStringExtra("address"));
            }
            break;
            default:
                break;
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void setSelectedFragment(FragmentBase selectedFragment) {

    }
}
