package cn.reimbursement.util;

import javax.servlet.http.HttpServletRequest;

import cn.reimbursement.pojo.Staff;

public class Util {
	public static boolean isLogin(HttpServletRequest request) {
		Staff staff=(Staff) request.getSession().getAttribute("staff");
		if(staff==null)
			return false;
		return true;
	}
}
