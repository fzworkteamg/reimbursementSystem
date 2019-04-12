package cn.reimbursement.util;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import netscape.javascript.JSObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class AddressListTool {

    static class WXUtil {
        static JSONObject getJson(String url) throws IOException {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String message = reader.readLine();
            JSONObject demoJson = JSONObject.fromObject(message);
            System.out.println("JSON字符串：" + demoJson);
            is.close();
            return demoJson;
        }
    }

    public static List getAllDepartment() throws IOException {
        String accesstoken2 = AddressListTool.getAccessToken();
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + accesstoken2;
        WXUtil.getJson(url);
        return null;
    }

    public static String getAccessToken() {
        String access_token = "";
        String AppId = "wxdc2a7022949cf052";//第三方用户唯一凭证
        String secret = "DDM3QsH6EY-7xanGo1h4PN5ZhVh9Ep6fAxF9elT7qGY";//第三方用户唯一凭证密钥，即appsecret
        //这个url链接地址和参数皆不能变
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + AppId + "&corpsecret=" + secret;
        try {
            JSONObject demoJson = WXUtil.getJson(url);
            access_token = demoJson.getString("access_token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(access_token);
        return access_token;
    }

//    public static String getTicket(String access_token) {
//        String ticket = null;
//        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";//这个url链接和参数不能变
//        try {
//            URL urlGet = new URL(url);
//            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
//            http.setRequestMethod("GET"); // 必须是get方式请求
//            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            http.setDoOutput(true);
//            http.setDoInput(true);
//            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
//            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
//            http.connect();
//            InputStream is = http.getInputStream();
//            int size = is.available();
//            byte[] jsonBytes = new byte[size];
//            is.read(jsonBytes);
//            String message = new String(jsonBytes, "UTF-8");
//            JSONObject demoJson = JSONObject.fromObject(message);
//            System.out.println("JSON字符串：" + demoJson);
//            ticket = demoJson.getString("ticket");
//            is.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ticket;
//    }

    public static JSONObject getOaStaffInfo(String accessToken, String departmentId, String fetchChild) throws Exception {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=" + accessToken + "&department_id=" + departmentId + "&fetch_child=" + fetchChild;
        JSONObject demoJson = WXUtil.getJson(url);
        return null;
    }

//    public static String SHA1(String decript) {
//        try {
//            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
//            digest.update(decript.getBytes());
//            byte messageDigest[] = digest.digest();
//            // Create Hex String
//            StringBuffer hexString = new StringBuffer();
//            // 字节数组转换为 十六进制 数
//            for (int i = 0; i < messageDigest.length; i++) {
//                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
//                if (shaHex.length() < 2) {
//                    hexString.append(0);
//                }
//                hexString.append(shaHex);
//            }
//            return hexString.toString();
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
}
