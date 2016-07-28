package com.company;

/**
 * Created by dg on 2016/7/28.
 */
public class SercCode {
    public static String getRealPhone(String phone){
        String prefix = pro("+86");
        return prefix+phone;
    }

    private static String pro(String phone) {
        return phone.replace("+", "");
    }
    public static String getRealPhone1(String phone,String code){
        String prefix = pro("+86");
        prefix.replace("+","");
        return prefix+phone+"#"+code;
    }
}
