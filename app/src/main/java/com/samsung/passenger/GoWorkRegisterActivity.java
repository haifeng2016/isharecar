package com.samsung.passenger;

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

import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.FragmentBaseInterface;
import com.samsung.isharecar.R;

public class GoWorkRegisterActivity extends FragmentActivity implements OnClickListener, FragmentBaseInterface {

    private int type = 0;
    private Intent intent = null;
    protected String title = "";

    final int DefaultOper = 0;
    final static int GoWork = 1; //enter the go work register car page
    final static int OffWork = 2; //enter the off work register car page
    final static int ViewDriver = 3; //enter the view driver route page
    final static int OtherOrder = 4; //enter the other orders page
    private ImageView mActionBarLayout = null;

    private TextView mActionView = null;
    private ImageView mActionIcon = null;
    private TextView mChargeRule = null;
    private FragmentBase backHandledFragment;

    public static final int REQUEST_CODE_MAP_ADDRESS_OFFICE = 0x001;
    public static final int REQUEST_CODE_MAP_ADDRESS_HOME = 0x002;
    FragmentManager fManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        type = intent.getIntExtra("type", DefaultOper);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.passenger_gowork_register);
        fManager = getSupportFragmentManager();
        mChargeRule = (TextView) findViewById(R.id.chargerule);
        mActionView = (TextView) findViewById(R.id.actionbarhead);
        mActionBarLayout = (ImageView) findViewById(R.id.actionbaricon);
        mActionIcon = (ImageView) findViewById(R.id.actionbaricon);
        mActionBarLayout.setOnClickListener(this);
        mChargeRule.setOnClickListener(this);
        init();
    }

    public void init() {
        switch(type){
        case GoWork:
            title = "上班约车";
            doPushFramgentAction(new GoWorkRegisterFragment());
            break;
        case OffWork:
            title = "下班约车";
            doPushFramgentAction(new OffWorkRegisterFragment());
            break;
        case ViewDriver:
            title = "其他约车";
            doPushFramgentAction(new PassengerMyOrderFragment());
            break;
        case OtherOrder:
            title = "我的订单";
            doPushFramgentAction(new OtherOrderRegisterFragment());
            break;
        default:
            break;
        }
    }

    public void childViewActionBarStyle(int title) {//0 -> hide, >0 -> show icon
        if (mActionView != null) {
            mActionView.setText(title > 0 ? getString(title): getString(R.string.app_name));
        }
        if (mActionIcon != null) {
            mActionIcon.setVisibility(View.VISIBLE);
        }
    }
    
    
    public void updateIfFirstStackState(boolean level) {
        if (!level) {
            mChargeRule.setVisibility(View.GONE);
            mActionView.setText(R.string.charge_rule);
        } else {
            mChargeRule.setVisibility(View.VISIBLE);
            mActionView.setText(title);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        case R.id.actionbaricon:
            doPopFragmentAction();
            break;
        case R.id.chargerule:
            //Intent intent = new Intent(this, PassengerViewChargeRuleActivity.class);
            //startActivity(intent);
            updateIfFirstStackState(false);
            Fragment passengerviewchargerulefragment = new PassengerViewChargeRuleFragment();
            doPushFramgentAction(passengerviewchargerulefragment);
            break;
        default:
            break;
        }
    }

    public void doPushFramgentAction(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fManager.beginTransaction();
        String tag = fragment.getClass().getSimpleName() + "_" + fragment.hashCode();
        fragmentTransaction.add(R.id.passengergowork, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        Fragment currentFragment = fManager.findFragmentById(R.id.passengergowork);
        if (null != currentFragment) {
            fragmentTransaction.hide(currentFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
        fManager.executePendingTransactions();
    }

    public void doPopFragmentAction() {
        if (fManager.getBackStackEntryCount() > 2) {
            fManager.popBackStackImmediate();
        } else if (fManager.getBackStackEntryCount() == 2){
            updateIfFirstStackState(true);
            fManager.popBackStackImmediate();
        } else {
            finish();
        }
        
    }

    @Override
    public void onBackPressed() {
        //doPopFragmentAction();
        finish();
    }

    public void setSelectedFragment(FragmentBase selectedFragment) {
        this.backHandledFragment = selectedFragment;
    }

    public int getType(){
        return type;
    }

    public void setType(int mType){
        type = mType;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
            case REQUEST_CODE_MAP_ADDRESS_OFFICE:
            {
                backHandledFragment.onPushMessage(REQUEST_CODE_MAP_ADDRESS_OFFICE, 
                        intent.getStringExtra("name"), intent.getStringExtra("address"));
            }
            break;
            case REQUEST_CODE_MAP_ADDRESS_HOME:
            {
                backHandledFragment.onPushMessage(REQUEST_CODE_MAP_ADDRESS_HOME, 
                        intent.getStringExtra("name"), intent.getStringExtra("address"));
            }
            break;
            default:
                break;
            }

        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

}
