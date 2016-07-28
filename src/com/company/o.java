package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/**
 * Created by dg on 2016/7/27.
 */
public final class o
{
    private static String a = "RSA";

    public static PublicKey a(InputStream paramInputStream)
            throws Exception
    {
        try
        {
            PublicKey localPublicKey = a(b(paramInputStream));
            return localPublicKey;
        }
        catch (IOException localIOException)
        {
            throw new Exception("公钥数据流读取错误");
        }
        catch (NullPointerException localNullPointerException)
        {
        }
        throw new Exception("公钥输入流为空");
    }

    public static PublicKey a(String paramString)
            throws Exception
    {
        try
        {
            byte[] arrayOfByte = com.company.a.a(paramString);
            RSAPublicKey localRSAPublicKey = (RSAPublicKey)KeyFactory.getInstance(a).generatePublic(new X509EncodedKeySpec(arrayOfByte));
            return localRSAPublicKey;
        }
        catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
        {
            throw new Exception("无此算法");
        }
        catch (InvalidKeySpecException localInvalidKeySpecException)
        {
            throw new Exception("公钥非法");
        }
        catch (NullPointerException localNullPointerException)
        {
        }
        throw new Exception("公钥数据为空");
    }

    public static byte[] a(byte[] paramArrayOfByte, PublicKey paramPublicKey)
    {
        try
        {
            Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            localCipher.init(1, paramPublicKey);
            byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte);
            return arrayOfByte;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return null;
    }

    private static String b(InputStream paramInputStream)
            throws IOException
    {
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
        StringBuilder localStringBuilder = new StringBuilder();
        while (true)
        {
            String str = localBufferedReader.readLine();
            if (str == null)
                break;
            if (str.charAt(0) == '-')
                continue;
            localStringBuilder.append(str);
            localStringBuilder.append('\r');
        }
        return localStringBuilder.toString();
    }
}
