package com.company;

import com.company.PhoneInfo.Phone;
import com.company.bean.loginbean;
import com.company.utils.GetInfoThread;
import com.company.utils.GetPhones;
import com.company.utils.HttpUtils;
import com.google.gson.Gson;
import com.meelive.ingkee.v1.core.d.b.*;
import com.squareup.okhttp.OkHttpClient;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Scanner;

import static com.company.SercCode.getRealPhone;
import static com.company.SercCode.getRealPhone1;


public class Main {

    private static String phone;


    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
//        try {
//            PublicKey a = o.a(new FileInputStream("C:\\Users\\dg\\Desktop\\source\\assets\\rsa_public_key.pem"));
//            String s = new String(Hex.encodeHex(o.a("17092938252".getBytes(), a)));
//            System.out.println(s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String string = "17092938252";
//        string = getRealPhone(string);
//        try {
//            PublicKey localPublicKey = com.meelive.ingkee.common.util.o.a(new FileInputStream("rsa_public_key.pem"));
//            phone = Hex.encodeHexString(com.meelive.ingkee.common.util.o.a(string.getBytes(), localPublicKey));
//            System.out.println("phone:"+phone);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String secret = abc.a(string.getBytes());
//        System.out.println("secret:"+secret);

        /*Scanner scanner = new Scanner(System.in);
        System.out.println("请输入手机号码：");
        String pn = scanner.next();
        String phone = Phone.getPhone(pn);
        String secret = Phone.getSecret(pn);
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
                String phone1 = Phone.getPhone(pn);
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("请输入验证码:");
                String code = scanner1.next();
                String secret1 = Phone.getSecret1(getRealPhone1(pn, code));
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
                        JSONObject uesrInfo = new JSONObject(result);
                        uesrInfo.put("phone",pn);
                        HttpUtils.doPostAsy(UPLOADURL, uesrInfo.toString(), new HttpUtils.CallBack() {
                            @Override
                            public void onRequestComplete(String result) {
                                System.out.println(result);
                            }
                        });
                    }
                });
            }
        });*/
//        System.out.println("请输入request_id");
//        String request_id = scanner.next();
//        System.out.println("请输入验证码");
//        String code = scanner.next();

//        System.out.println(loginjson);

        int count = 3;
        String[] nums = GetPhones.getNumbers(count);
        if (nums.length!=count){
            System.out.println("获取不到资源，请手动释放手机号资源");
        }else {
//            System.out.println(Arrays.toString(nums));
            for (String num :
                    nums) {
                System.out.println(num);
                GetInfoThread getInfoThread = new GetInfoThread(num);
                getInfoThread.run();

            }
        }



    }

}
