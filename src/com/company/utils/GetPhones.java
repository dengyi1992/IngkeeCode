package com.company.utils;

/**
 * Created by dg on 2016/7/29.
 */
public class GetPhones {
    public static String token;
    public static String[] getNumbers(){
        String ApiLoginInfo = HttpUtils.doGet("http://api.yma0.com/http.aspx?action=loginIn&uid=wangyou&pwd=redhat");
        token = ApiLoginInfo.substring("wangyou|".length());
        String GetNum = "http://api.yma0.com/http.aspx?action=getMobilenum&pid=19662&uid=wangyou&token=" + token +
                "&size=1";
//        System.out.println(GetNum);
        String num = HttpUtils.doGet(GetNum);
        String[] split = num.split("\\|");
        String[] nums = split[0].split(";");
        return nums;
    }
}
