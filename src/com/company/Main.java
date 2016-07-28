package com.company;

import com.company.PhoneInfo.Phone;
import com.meelive.ingkee.v1.core.d.b.*;
import com.squareup.okhttp.OkHttpClient;
import org.apache.commons.codec.binary.Hex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.PublicKey;
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入手机号码：");
        String pn = scanner.next();
        String phone = Phone.getPhone(pn);
        String secret = Phone.getSecret(pn);
        String codejson = "{\"source\":\"login\",\"region\":\"cn\",\"secret\":\"" +secret+
                "\",\"phone\":\"" +phone+
                "\"}";
        System.out.println(codejson);
    
        System.out.println("请输入request_id");
        String request_id = scanner.next();
        System.out.println("请输入验证码");
        String code = scanner.next();
        String phone1 = Phone.getPhone(pn);
        String secret1 = Phone.getSecret1(getRealPhone1(pn, code));
        String loginjson = "{\"secret\":\"" +secret1+
                "\",\"phone\":\"" +phone1+
                "\",\"code\":\"" +code+
                "\",\"request_id\":\"" +request_id+
                "\"}";
        System.out.println(loginjson);


    }

}
