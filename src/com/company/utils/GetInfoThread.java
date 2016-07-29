package com.company.utils;

import com.company.PhoneInfo.Phone;
import org.json.JSONObject;

import java.util.Scanner;

import static com.company.SercCode.getRealPhone1;
import static com.company.utils.GetPhones.token;

/**
 * Created by dg on 2016/7/29.
 */
public class GetInfoThread implements Runnable {
    public static String LOGINURL = "http://service.inke.com/user/account/phone_login?lc=3000000000005852&cv=IK2.9.50_Android&cc=TG36001&ua=samsungSM-N7508V&uid=0&sid=&devi=352203065389185&imsi=&imei=352203065389185&icc=&conn=WIFI&vv=1.0.3-2016060211417.android&aid=c0590722d45ca695&osversion=android_18&proto=4&smid=DuEdLy786y%2B5h9D0%2BOvvHjExiUJ0pOrcnuOkw6HK2riyVnTLWuq%2By4ds8D28Ueyx9%2BRElIe00SnoPSaLz1Zqs0sg";
    public static String CODEURL = " http://service.inke.com/user/account/phone_code?lc=3000000000005860&cv=IK2.9.50_Android&cc=TG36011&ua=Meizum2note&uid=0&sid=&devi=867570028396103&imsi=460026208089352&imei=867570028396103&icc=898600f2261478202497&conn=WIFI&vv=1.0.3-2016060211417.android&aid=caf33cfb45b6cd66&osversion=android_22&proto=4&smid=DuRCYMJK7iW%2BsDMckPgAXpsIRqhtVX0LftNnFOymKTNUnHwA9%2F0COYdTgdEiGrNGcojZSgBQPQStfvxmVPjaX5Cw";
    public static String UPLOADURL = "http://192.168.199.233:3000/usercollection";
    private String codeString;

    public GetInfoThread(String phonenum) {
        this.phonenum = phonenum;
    }

    private String phonenum;
    @Override
    public void run() {

//        System.out.println("请输入手机号码：");
        String phone = Phone.getPhone(phonenum);
        String secret = Phone.getSecret(phonenum);
        String codejson = "{\"source\":\"login\",\"region\":\"cn\",\"secret\":\"" +secret+
                "\",\"phone\":\"" +phone+
                "\"}";
//        Gson gson = new Gson();
//        loginbean loginbean = gson.fromJson(codejson, loginbean.class);
        System.out.println(codejson);
//        HttpUtils.DoPost(CODEURL,codejson);
        HttpUtils.doPostAsy(CODEURL, codejson, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                JSONObject jsonObject = new JSONObject(result);
                String request_id = jsonObject.getString("request_id");
                String phone1 = Phone.getPhone(phonenum);
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(5000);
                        codeString = HttpUtils.doGet("http://api.yma0.com/http.aspx?action=getVcodeAndReleaseMobile&uid=wangyou&token=" + token + "&pid=19662&mobile=" + phonenum);
                        if (codeString.startsWith(phonenum))
                            break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println(codeString);
                if (!codeString.startsWith(phonenum)){
                    System.out.println("未接收到验证码");
                    return;
                }
                String code = codeString.substring(codeString.indexOf("【映客】") + "【映客】".length(), codeString.indexOf("【映客】") + "【映客】".length() + 4);
//                Scanner scanner1 = new Scanner(System.in);
//                System.out.println("请输入验证码:");
//                String code = scanner1.next();
                String secret1 = Phone.getSecret1(getRealPhone1(phonenum, code));
                String loginjson = "{\"secret\":\"" +secret1+
                        "\",\"phone\":\"" +phone1+
                        "\",\"code\":\"" +code+
                        "\",\"request_id\":\"" +request_id+
                        "\"}";
                System.out.println(loginjson);
                HttpUtils.doPostAsy(LOGINURL, loginjson, new HttpUtils.CallBack() {
                    @Override
                    public void onRequestComplete(String result) {
                        System.out.println("最后登录信息:"+result);
                        if (result.contains("错误"))
                            return;
                        JSONObject uesrInfo = new JSONObject(result);

                        uesrInfo.put("phone",phonenum);
                        try {
                            HttpUtils.doPostAsy(UPLOADURL, uesrInfo.toString(), new HttpUtils.CallBack() {
                                @Override
                                public void onRequestComplete(String result) {
                                    System.out.println(result);
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }
}
