package com.company.PhoneInfo;

import com.company.abc;
import com.meelive.ingkee.common.util.o;
import org.apache.commons.codec.binary.Hex;

import java.io.FileInputStream;
import java.security.PublicKey;

import static com.company.SercCode.getRealPhone;

/**
 * Created by dg on 2016/7/28.
 */
public class Phone {
    public static String getPhone(String s) {

        try {
            s = getRealPhone(s);
            PublicKey localPublicKey = com.meelive.ingkee.common.util.o.a(new FileInputStream("rsa_public_key.pem"));
            String phone = Hex.encodeHexString(o.a(s.getBytes(), localPublicKey));
//            System.out.println("phone:" + phone);
            return phone;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getSecret(String s) {
        return abc.a(getRealPhone(s).getBytes());
    }
    public static String getSecret1(String s) {
        return abc.a(s.getBytes());
    }
}
