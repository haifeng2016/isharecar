package com.samsung.isharecar;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.samsung.info.InfoFragment;
import com.samsung.register.RegisterFragment;

public class MainActivity extends FragmentActivity implements OnClickListener,FragmentBaseInterface {
    
    final public static boolean mDebug = true;

    public static String APPID = "";
    protected boolean isPreview;
    public static final int LOLLIPOP_API_LEVEL = 21;
    public static final int MARSHMALLOW_API_LEVEL = 23;
    //private View _paddingView;
    public static final int SYSTEM_UI_FLAG_LIGHT_STATUS_BAR = 0x00002000;
    
    //all the max number of register route that unfinish
    public static final int allowRegisterNumber = 2;

    private InfoFragment infoFragment;
    private com.samsung.isharecar.HomeFragment homeFragment;
    private RegisterFragment registerFragment;
    private FragmentBase mBackHandedFragment;

    private RelativeLayout info_layout;
    private RelativeLayout home_layout;
    private RelativeLayout setting_layout;

    final public static int info = 0; //info page
    final public static int home = 1; //home page
    final public static int register = 2; //register page
    private int index = home;

    private ImageView info_image;
    private ImageView home_image;
    private ImageView setting_image;
    private TextView info_text;
    private TextView setting_text;
    private TextView home_text;

    private long exitTime = 0;

    private int black = 0xFF000000;
    private int blue = 0xFF45C01A;
    private String TAG = "MainActivity";

    private LinearLayout tabHomeView;

    private ImageView mActionBarLayout = null;
    private ImageButton mReturnBack = null;
    private TextView mABTextView = null;
    private TextView mActionView = null;
    private ImageView mActionIcon = null;
    
    public static final int REQUEST_CODE_MAP_ADDRESS_OFFICE = 0x001;
    public static final int REQUEST_CODE_MAP_ADDRESS_HOME = 0x002;
    public static final int REQUEST_CODE_LOGIN_STATE = 0x003;
    
    private static int userId = 0;
    private static String userName = null;
    private static String phoneNumber = null;

    FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_activity_main);
        fManager = getSupportFragmentManager();
        initViews();
        setChioceItem(home);
        
        /*****initial the bmob*********/
//        Bmob.initialize(this, APPID);
        /*****initial the bmob*********/

        fManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
//                Fragment fragment = fManager.findFragmentById(R.id.content);
//                if (fragment instanceof IFragmentUpdate) {
//                    IFragmentUpdate sf = (IFragmentUpdate) fragment;
//                    sf.updateContent("login", "in");
//                }
                setChioceItem(index);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });

    }

    public void initViews()
    {
        info_image = (ImageView) findViewById(R.id.info_image);
        home_image = (ImageView) findViewById(R.id.home_image);
        setting_image = (ImageView) findViewById(R.id.setting_image);
        info_text = (TextView) findViewById(R.id.info_text);
        home_text = (TextView) findViewById(R.id.home_text);
        setting_text = (TextView) findViewById(R.id.setting_text);
        info_layout = (RelativeLayout) findViewById(R.id.info_layout);
        home_layout = (RelativeLayout) findViewById(R.id.home_layout);
        setting_layout = (RelativeLayout) findViewById(R.id.setting_layout);
        tabHomeView = (LinearLayout) findViewById(R.id.tab_home);
        mActionView = (TextView) findViewById(R.id.actionbarhead);
        mActionBarLayout = (ImageView) findViewById(R.id.actionbaricon);
        mActionIcon = (ImageView) findViewById(R.id.actionbaricon);
        info_layout.setOnClickListener(this);
        home_layout.setOnClickListener(this); 
        setting_layout.setOnClickListener(this);
        mActionBarLayout.setOnClickListener(this);
    }

    public void childViewActionBarStyle(int title) {//0 -> hide, >0 -> show icon
        if (mActionView != null) {
            mActionView.setText(title > 0 ? getString(title): getString(R.string.app_name));
        }
        if (mActionIcon != null) {
            mActionIcon.setVisibility(title > 0 ? View.VISIBLE:View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        case R.id.info_layout:
            setChioceItem(0);
            break;
        case R.id.home_layout:
            setChioceItem(1);
            break;
        case R.id.setting_layout:
            setChioceItem(2);
            break;
        case R.id.actionbaricon:
            childViewActionBarStyle(0);
            if (fManager.getBackStackEntryCount() > 1) {
                fManager.popBackStackImmediate();
            } else if (fManager.getBackStackEntryCount() == 1){
                fManager.popBackStackImmediate();
                tabHomeView.setVisibility(View.VISIBLE);
            }
            setChioceItem(0);
            break;
        default:
            break;
        }
    }

    public void doPushFramgentAction(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fManager.beginTransaction();
        String tag = fragment.getClass().getSimpleName() + "_" + fragment.hashCode();
        fragmentTransaction.add(R.id.content, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        Fragment currentFragment = fManager.findFragmentById(R.id.content);
        if (null != currentFragment) {
            fragmentTransaction.hide(currentFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
        fManager.executePendingTransactions();
    }

    public void setTabHomeViewHide(boolean hide) {
        tabHomeView.setVisibility(hide?View.GONE:View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        childViewActionBarStyle(0);
        if (fManager.getBackStackEntryCount() > 1) {
            fManager.popBackStackImmediate();
        } else if (fManager.getBackStackEntryCount() == 1){
            fManager.popBackStackImmediate();
            tabHomeView.setVisibility(View.VISIBLE);
            setChioceItem(0);
        }
        else {
            exit();
        }
    }
    
    public void doPopFragmentAction() {
        childViewActionBarStyle(0);
        if (fManager.getBackStackEntryCount() > 1) {
            fManager.popBackStackImmediate();
        } else if (fManager.getBackStackEntryCount() == 1){
            fManager.popBackStackImmediate();
            tabHomeView.setVisibility(View.VISIBLE);
            setChioceItem(index);
        } else {
            exit();
        }
    }

    public int getIndex(){
        return index;
    }
    
    public void setIndex(int mIndex){
        index = mIndex;
    }

    public void setChioceItem(int index)
    {
        FragmentTransaction transaction = fManager.beginTransaction();
        clearChioce();
        hideFragments(transaction);
        Fragment currentFragment = null;
        switch (index) {
        case 0:
            info_image.setImageResource(R.drawable.home_info_green);
            info_text.setTextColor(blue);
            //test
            currentFragment = fManager.findFragmentById(R.id.content);

            if (infoFragment == null) {
                infoFragment = new InfoFragment();
                transaction.add(R.id.content, infoFragment);
            } else {
                transaction.show(infoFragment);
            }
            break;

        case 1:
            home_image.setImageResource(R.drawable.home_green);
            home_text.setTextColor(blue);
            if (homeFragment == null) {
                homeFragment = new com.samsung.isharecar.HomeFragment();
                transaction.add(R.id.content, homeFragment);
            } else {
                transaction.show(homeFragment);
            }
            break;

        case 2:
            currentFragment = fManager.findFragmentById(R.id.content);
            setting_image.setImageResource(R.drawable.home_me_green);
            setting_text.setTextColor(blue);
            if (registerFragment == null) {
                registerFragment = new RegisterFragment();
                transaction.add(R.id.content, registerFragment);
            } else {
                transaction.show(registerFragment);
            }
            break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (infoFragment != null) {
            transaction.hide(infoFragment);
        }
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (registerFragment != null) {
            transaction.hide(registerFragment);
        }
    }

    public void clearChioce()
    {
        info_image.setImageResource(R.drawable.home_info_black);
        info_text.setTextColor(black);
        home_image.setImageResource(R.drawable.home_black);
        home_text.setTextColor(black);
        setting_image.setImageResource(R.drawable.home_me_black);
        setting_text.setTextColor(black);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void setSelectedFragment(FragmentBase selectedFragment) {
        this.mBackHandedFragment = selectedFragment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
            case REQUEST_CODE_MAP_ADDRESS_OFFICE:
            {
                mBackHandedFragment.onPushMessage(REQUEST_CODE_MAP_ADDRESS_OFFICE, 
                        intent.getStringExtra("name"), intent.getStringExtra("address"));
            }
            break;
            case REQUEST_CODE_MAP_ADDRESS_HOME:
            {
                mBackHandedFragment.onPushMessage(REQUEST_CODE_MAP_ADDRESS_HOME, 
                        intent.getStringExtra("name"), intent.getStringExtra("address"));
            }
            break;
            case REQUEST_CODE_LOGIN_STATE:
            {
                //get return data
                mBackHandedFragment.updateContent("login", "in");
            }
            default:
                break;
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }
    
    /*************store the register user info in the application******************/
    
    public static int getUserId(){
        return userId;
    }
    public static void setUserId(int userid){
        userId = userid;
    }
    public static String getUserName(){
        return userName;
    }
    public static void setUserName(String username){
        userName = username;
    }
    public static String getUserPhone(){
        return phoneNumber;
    }
    public static void setUserPhone(String phonenumber){
        phoneNumber = phonenumber;
    }
    
    /*************store the register user info in the application******************/

}