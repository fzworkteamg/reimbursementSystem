package cn.reimbursement.util;

import javax.servlet.http.HttpServletRequest;

import cn.reimbursement.enums.SessionEnum;
import cn.reimbursement.pojo.Staff;

public class Util {
	public static boolean isLogin(HttpServletRequest request) {
		System.out.println(request.getSession().getAttribute(SessionEnum.STAFF.getValue()));
		Staff staff=(Staff) request.getSession().getAttribute(SessionEnum.STAFF.getValue());
		if(staff==null)
			return false;
		return true;
	}
}
