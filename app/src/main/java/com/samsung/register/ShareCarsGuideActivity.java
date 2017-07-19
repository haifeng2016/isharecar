package com.samsung.register;

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

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.FragmentBaseInterface;
import com.samsung.isharecar.R;

public class ShareCarsGuideActivity extends FragmentActivity implements OnClickListener,FragmentBaseInterface {

    private int type = 0;
    protected String title = "";
    private ImageView mActionBarLayout = null;
    private TextView mActionView = null;
    private ImageView mActionIcon = null;

    //定义FragmentManager对象
    FragmentManager fManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_drive_guide_activity);

        /*在onCreate中一定要加入下面两行，不然无法压入栈*/
        fManager = getSupportFragmentManager();
        doPushFramgentAction(new ShareCarsGuideFragment());

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

    //更新标题栏title
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
        fragmentTransaction.add(R.id.driveguide, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        Fragment currentFragment = fManager.findFragmentById(R.id.driveguide);
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

    public void setSelectedFragment(FragmentBase selectedFragment) {
    }

    public int getType() {
        return type;
    }

    public void setType(int mType) {
        type = mType;
    }

}
