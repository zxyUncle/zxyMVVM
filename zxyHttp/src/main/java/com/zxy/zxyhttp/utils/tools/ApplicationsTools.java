package com.zxy.zxyhttp.utils.tools;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import java.lang.reflect.Method;
/**
 * Created by zsf on 2021/1/4 14:38
 * ******************************************
 * * 直接获取application的Context，不需要通过App.getContext
 * ******************************************
 */
public class ApplicationsTools {


    @NonNull

    public static Application context() {

        return CURRENT;

    }


    @SuppressLint("StaticFieldLeak")

    private static final Application CURRENT;


    static {

        try {

            Object activityThread = getActivityThread();

            Object app = activityThread.getClass().getMethod("getApplication")

                    .invoke(activityThread);

            CURRENT = (Application) app;

        } catch (Throwable e) {

            throw new IllegalStateException("Can not access Application context by magic code, boom!", e);

        }

    }


    private static Object getActivityThread() {

        Object activityThread = null;

        try {

            @SuppressLint("PrivateApi") Method method = Class.forName("android.app.ActivityThread")

                    .getMethod("currentActivityThread");

            method.setAccessible(true);

            activityThread = method.invoke(null);

        } catch (final Exception e) {

            Log.w("zxy", e);

        }

        return activityThread;

    }

}
