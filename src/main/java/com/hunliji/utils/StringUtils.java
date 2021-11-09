package com.hunliji.utils;

import org.apache.http.util.TextUtils;

public class StringUtils {

    /**
     * 转化驼峰写法（首字母大写)
     */
    public static String captureStringLeaveUnderscore(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        String[] strings = text.split("_");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(captureName(strings[0]));
        for (int i = 1; i < strings.length; i++) {
            stringBuilder.append(captureName(strings[i]));
        }
        return stringBuilder.toString();
    }

    /**
     * 第一个字母大写其余字母全部小写
     */
    public static String captureName(String text) {
        if (text.length() > 0) {
            text = text.substring(0, 1).toUpperCase() + text.substring(1);
        }
        return text;
    }

    /**
     * 重复字符串多少次
     */
    public static String repeatStr(String repeat, int number) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < number; i++) {
            stringBuilder.append(repeat);
        }
        return stringBuilder.toString();
    }
}
