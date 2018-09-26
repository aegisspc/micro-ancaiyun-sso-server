package com.ancaiyun.util;


import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
    private static String regMobile = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9])|(16[0-9]))\\d{8}$";
    private static String regUsername = "^[A-Za-z0-9\u4e00-\u9fa5]{6,16}$";
    private static String regEmail = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private static String regRealName = "(([\u4E00-\u9FA5]{2,7})|([a-zA-Z]{3,10}))";
    private static String regPassword = "^[0-9a-zA-Z]{6,16}$";
    private static String num = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";//正负点数

    public static boolean patternString(String str, String reType)
    {
        String reg;
        if (StringUtils.isBlank(str) || StringUtils.isBlank(reType))
        {
            return false;
        }

        switch (reType) {
            case "mobile":
                reg = regMobile;
                break;
            case "username":
                reg = regUsername;
                break;
            case "email":
                reg = regEmail;
                break;
            case "realName":
                reg = regRealName;
                break;
            case "password":
                reg = regPassword;
                break;
            case "num":
                reg = num;
                break;
            default:
                reg = null;
                break;
        }

        if (reg == null)
        {
            return false;
        }

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        boolean rs = matcher.find();

        return rs;
    }
}
