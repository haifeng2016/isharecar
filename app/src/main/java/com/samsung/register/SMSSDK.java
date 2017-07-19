package com.samsung.register;

import com.samsung.isharecar.EventHandler;

public class SMSSDK {

    protected static final int EVENT_SUBMIT_VERIFICATION_CODE = 0;
    protected static final int EVENT_GET_SUPPORTED_COUNTRIES = 1;
    protected static final int EVENT_GET_VERIFICATION_CODE = 2;
    protected static final int RESULT_COMPLETE = 3;

    public static void unregisterAllEventHandler() {
    }

    public static void initSDK(RegisterFragment registerFragment,
            String string, String string2) {
    }

    public static void registerEventHandler(EventHandler eh) {
    }

    public static void getVerificationCode(String string, String iPhone) {
    }

    public static void submitVerificationCode(String string, String iPhone,
            String iCord) {
    }

    public static void submitUserInfo(String uid, String nickName,
            Object object, String country, String phone) {
    }

}
