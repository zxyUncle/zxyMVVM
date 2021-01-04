package com.zxy.zxyhttp.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zsf on 2021/1/4 15:55
 * ******************************************
 * * 日志格式化
 * ******************************************
 */
public class LogcatUitls {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.e(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.e(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    public static void printStirng(String tag, String msg) {
        printLine(tag, true);
        Log.e(tag, "║ " + msg);
        printLine(tag, false);
    }

    public static void printGet(String tag, String header , String msg) {
        printLine(tag, true);
        Log.e(tag, "║ " + "header:"+header);
        Log.e(tag, "║ " + msg);
        printLine(tag, false);
    }

    public static void printPost(String tag, String header, String msg) {
        printLine(tag, true);
        Log.e(tag, "║ " + header);
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        message = LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.e(tag, "║ " + line);
        }
        printLine(tag, false);
    }

    public static void printJson(String tag, String msg) {
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        printLine(tag, true);
        message = LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.e(tag, "║ " + line);
        }
        printLine(tag, false);
    }
}

