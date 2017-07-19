package com.samsung.isharecar;

public final class Log {
    /* Set level of Log output */
    private static int sLevel = Log.VERBOSE;

    /*
     * If set 'true', add "call stack infomation" on log msg automatically.
     * It can make bad perfomance. It is recommended for use only local source.
     */

    private static boolean sPrintSecured = false;
    private static final boolean mPrintThreadId = false;
    public static final int VERBOSE = 0;
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int WARN = 3;
    public static final int ERROR = 4;

    public static final String FQCN = "com.example.sharecar";
    /*
     * Default value is 'true' on develop mode.
     * It does not work on D2...;;;(2012-03-16)
     */

    public static int getCurrentLevel() {
        return sLevel;
    }

    public static boolean isLoggable(String TAG, int LEVEL) {
        return android.util.Log.isLoggable(TAG, LEVEL);
    }

    public static String getStackTraceString(Throwable tr) {
        return android.util.Log.getStackTraceString(tr);
    }

    public static String getCurrentThreadInfo() {
        return "[" + Thread.currentThread().getName() + "]";
    }

    // VERBOSE
    public static void v(String tag, String msg) {
        if (sLevel <= VERBOSE) {
            if (mPrintThreadId) {
                msg = getCurrentThreadInfo() + msg;
            }
            android.util.Log.v(tag, msg);
        }
    }

    // Print security related contents with this method in order to turn off at once,like phone number, key code etc..
    public static void s(String tag, String msg) {
        if (sPrintSecured) {
            if (mPrintThreadId) {
                msg = getCurrentThreadInfo() + msg;
            }
            android.util.Log.v(tag, msg);
        }
    }

    // DEBUG
    public static void d(String tag, String msg) {
        if (sLevel <= DEBUG) {
            if (mPrintThreadId) {
                msg = getCurrentThreadInfo() + msg;
            }
            android.util.Log.d(tag, msg);
        }
    }

    // INFO
    public static void i(String tag, String msg) {
        if (sLevel <= INFO) {
            if (mPrintThreadId) {
                msg = getCurrentThreadInfo() + msg;
            }            
            android.util.Log.i(tag, msg);
        }
    }

    // WARN
    public static void w(String tag, String msg) {
        if (sLevel <= WARN) {
            if (mPrintThreadId) {
                msg = getCurrentThreadInfo() + msg;
            }
            android.util.Log.w(tag, msg);
        }
    }

    // ERROR
    public static void e(String tag, String msg) {
        if (sLevel <= ERROR) {
            if (mPrintThreadId) {
                msg = getCurrentThreadInfo() + msg;
            }
            android.util.Log.e(tag, msg);
        }
    }

}
