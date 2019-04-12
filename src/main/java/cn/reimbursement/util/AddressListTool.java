package cn.reimbursement.util;

import net.sf.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddressListTool {
    public static String getAccessToken() {
        String access_token = "";
//        String grant_type = "client_credential";//获取access_token填写client_credential
        String AppId= "wxdc2a7022949cf052";//第三方用户唯一凭证
        String secret= "DDM3QsH6EY-7xanGo1h4PN5ZhVh9Ep6fAxF9elT7qGY";//第三方用户唯一凭证密钥，即appsecret
        //这个url链接地址和参数皆不能变
//        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+AppId+"&secret="+secret;
        String url =  "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+AppId+"&corpsecret="+secret;
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = JSONObject.fromObject(message);
            System.out.println("JSON字符串："+demoJson);
            access_token = demoJson.getString("access_token");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(access_token);
        return access_token;
    }
}
