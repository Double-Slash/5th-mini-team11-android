package doubleslash05.mini.team11.util;

import doubleslash05.mini.team11.BuildConfig;

public class Log {
    private static boolean DEBUG = BuildConfig.DEBUG;

    public static void v(String tag, String msg) {
        if (DEBUG) android.util.Log.v(tag, msg);
    }

    public static void v(String tag, String msg, Throwable t) {
        if (DEBUG) android.util.Log.v(tag, msg, t);
    }

    public static void d(String tag, String msg) {
        if (DEBUG) android.util.Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable t) {
        if (DEBUG) android.util.Log.d(tag, msg, t);
    }

    public static void i(String tag, String msg) {
        if (DEBUG) android.util.Log.d(tag, msg);
    }

    public static void i(String tag, String msg, Throwable t) {
        if (DEBUG) android.util.Log.d(tag, msg, t);
    }

    public static void w(String tag, String msg) {
        if (DEBUG) android.util.Log.d(tag, msg);
    }

    public static void w(String tag, String msg, Throwable t) {
        if (DEBUG) android.util.Log.d(tag, msg, t);
    }

    public static void e(String tag, String msg) {
        if (DEBUG) android.util.Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable t) {
        if (DEBUG) android.util.Log.e(tag, msg, t);
    }
}
