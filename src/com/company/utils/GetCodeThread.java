package com.company.utils;

/**
 * Created by dg on 2016/7/29.
 */
public class GetCodeThread extends Thread {
    private String token;
    private String phonenum;
    public boolean isCopy;
    public String code;
    public GetCodeThread(String token, String phonenum) {
        this.token = token;
        this.phonenum = phonenum;
        this.isCopy=false;
    }

    @Override
    public void run() {
        String codeString = HttpUtils.doGet("http://api.yma0.com/http.aspx?action=getVcodeAndReleaseMobile&uid=wangyou&token=" + token + "&pid=19662&mobile=" + phonenum);
        if(codeString.startsWith(phonenum)){
            //终止
            this.isCopy=true;
            this.code=codeString;
        }
    }
}
