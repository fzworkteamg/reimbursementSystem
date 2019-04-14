package cn.reimbursement.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.reimbursement.dao.CompanyDao;
import cn.reimbursement.dao.DepDao;
import cn.reimbursement.dao.StaffDao;
import cn.reimbursement.enums.WxEnum;
import net.sf.json.JSONObject;

@Component
public class WxUtil {
	
	@Autowired
	private StaffDao staffDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private DepDao depDao;
	
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
			is.close();
			return demoJson;
		}
	}
	
	//根据部门号获取部门及子部门
	public static List<Map<String,Object>> getDepartment(Integer depNum) throws IOException {
		String accesstoken = WxUtil.getAccessToken();
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + accesstoken+"&id="+depNum;
		JSONObject jsonObject = WXUtil.getJson(url);
		List<Map<String,Object>> departmentList = (List<Map<String,Object>>) jsonObject.get("department");
		return departmentList;
	}
	
	//获取所有公司
	public static List<Map<String,Object>> getCompanys()throws IOException{
		List<Map<String,Object>> companyList=WxUtil.getDepartment(14);
		List<Map<String,Object>> depList=WxUtil.getDepartment(2);
		for (Map<String, Object> map : depList) {
			if((Integer)map.get("id")==2) {
				companyList.add(map);
				break;
			}
		}
		for (Map<String, Object> map : companyList) {
			if((Integer)map.get("id")==14) {
				companyList.remove(map);
				break;
			}
		}
		return companyList;
	}
	//获取指定公司的部门
	public static List<Map<String,Object>> getDepByCompanyId(Integer companyId) throws IOException{
		List<Map<String,Object>> depList=WxUtil.getDepartment(companyId);
		for (Map<String, Object> map : depList) {
			for(int i=0;i<depList.size();i++) {
				if(!(depList.get(i).get("parentid").toString().equals(companyId.toString())) || (Integer)depList.get(i).get("parentid")==14) {
					depList.remove(i);
				}
			}
		}
		return depList;
	}
	
	
	public static String getAccessToken() throws IOException {
		String access_token = "";
		String AppId = WxEnum.APP_ID.getValue();// 第三方用户唯一凭证
		String secret = WxEnum.SECRET.getValue();// 第三方用户唯一凭证密钥，即appsecret
		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + AppId + "&corpsecret=" + secret;
		JSONObject demoJson = WXUtil.getJson(url);
		access_token = demoJson.getString("access_token");
		return access_token;
	}

	//根据部门id获取员工
	public static List<Map<String,Object>> getOaStaffInfo(String accessToken, String departmentId, String fetchChild)
			throws Exception {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=" + accessToken + "&department_id="
				+ departmentId + "&fetch_child=" + fetchChild;
		JSONObject jsonObject = WXUtil.getJson(url);
		List<Map<String,Object>> userList = (List<Map<String,Object>>) jsonObject.get("userlist");
		return userList;
	}
	
	@Transactional
	@Scheduled(cron = "0 0 0 * * ? ")
	private void updateOaStaff() throws IOException, Exception {
		int staffCount=staffDao.selectStaffCount();
		int deleteStaffCount=staffDao.deleteStaff();
		if (staffCount != deleteStaffCount) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		List<Map<String, Object>> strList = WxUtil.getOaStaffInfo(WxUtil.getAccessToken(), "2", "1");
		for (Map<String, Object> staffMap : strList) {
			String strDep = staffMap.get("department").toString().replaceAll("\\[", "");
			strDep = strDep.replaceAll("\\]", "");
			int depId = Integer.valueOf(strDep);
			if (depId <= 13) {
				String companyName = companyDao.selectCompanyById(2);
				if (StringUtils.isEmpty(companyName)) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
				staffMap.put("companyName", companyName);
				String depName = depDao.selectDepById(depId);
				if (StringUtils.isEmpty(depName)) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
				staffMap.put("depName", depName);
			} else if (depId >= 14) {
				String companyName = companyDao.selectCompanyById(depId);
				if (StringUtils.isEmpty(companyName)) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
				staffMap.put("companyName", companyName);
				String depName = depDao.selectDepById(depId);
				if (StringUtils.isEmpty(depName)) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
				staffMap.put("depName", depName);
			}
			if (staffDao.insertWxStaff(staffMap) == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
	}
	// public static String getTicket(String access_token) {
	// String ticket = null;
	// String url =
	// "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" +
	// access_token + "&type=jsapi";//这个url链接和参数不能变
	// try {
	// URL urlGet = new URL(url);
	// HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	// http.setRequestMethod("GET"); // 必须是get方式请求
	// http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	// http.setDoOutput(true);
	// http.setDoInput(true);
	// System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//
	// 连接超时30秒
	// System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
	// http.connect();
	// InputStream is = http.getInputStream();
	// int size = is.available();
	// byte[] jsonBytes = new byte[size];
	// is.read(jsonBytes);
	// String message = new String(jsonBytes, "UTF-8");
	// JSONObject demoJson = JSONObject.fromObject(message);
	// System.out.println("JSON字符串：" + demoJson);
	// ticket = demoJson.getString("ticket");
	// is.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return ticket;
	// }


	// public static String SHA1(String decript) {
	// try {
	// MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
	// digest.update(decript.getBytes());
	// byte messageDigest[] = digest.digest();
	// // Create Hex String
	// StringBuffer hexString = new StringBuffer();
	// // 字节数组转换为 十六进制 数
	// for (int i = 0; i < messageDigest.length; i++) {
	// String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
	// if (shaHex.length() < 2) {
	// hexString.append(0);
	// }
	// hexString.append(shaHex);
	// }
	// return hexString.toString();
	//
	// } catch (NoSuchAlgorithmException e) {
	// e.printStackTrace();
	// }
	// return "";
	// }
}
