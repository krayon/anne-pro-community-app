package www.robinwatch.squid.utils;

import android.util.Log;

public class C0213L {
    private static final String TAG = "squid";
    public static boolean isDebug = true;

    public static void m19i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void m13d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void m16e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void m22v(String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }

    public static void m24w(String msg) {
        if (isDebug) {
            Log.w(TAG, msg);
        }
    }

    public static void m18i(Class<?> _class, String msg) {
        if (isDebug) {
            Log.i(_class.getName(), msg);
        }
    }

    public static void m12d(Class<?> _class, String msg) {
        if (isDebug) {
            Log.i(_class.getName(), msg);
        }
    }

    public static void m15e(Class<?> _class, String msg) {
        if (isDebug) {
            Log.i(_class.getName(), msg);
        }
    }

    public static void m21v(Class<?> _class, String msg) {
        if (isDebug) {
            Log.i(_class.getName(), msg);
        }
    }

    public static void m20i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void m14d(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void m17e(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void m23v(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void m25w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg);
        }
    }
}
