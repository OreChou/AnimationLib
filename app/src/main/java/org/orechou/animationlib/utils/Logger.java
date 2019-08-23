package org.orechou.animationlib.utils;

import android.util.Log;

public class Logger {

    public static final int VERBOSE = 2;

    public static final int DEBUG = 3;

    public static final int INFO = 4;

    public static final int WARN = 5;

    public static final int ERROR = 6;

    public static final int ASSERT = 7;

    public static int LEVEL = VERBOSE;

    public static void v(String TAG, String message, Object... args) {
        if (LEVEL > VERBOSE) {
            return;
        }
        message = String.format(message, args);
        Log.v(TAG, message);
    }

    public static void d(String TAG, String message, Object... args) {
        if (LEVEL > DEBUG) {
            return;
        }
        message = String.format(message, args);
        Log.d(TAG, message);
    }

    public static void i(String TAG, String message, Object... args) {
        if (LEVEL > INFO) {
            return;
        }
        message = String.format(message, args);
        Log.i(TAG, message);
    }

    public static void w(String TAG, String message, Object... args) {
        if (LEVEL > WARN) {
            return;
        }
        message = String.format(message, args);
        Log.w(TAG, message);
    }

    public static void e(String TAG, String message, Object... args) {
        if (LEVEL > ERROR) {
            return;
        }
        message = String.format(message, args);
        Log.e(TAG, message);
    }
}
