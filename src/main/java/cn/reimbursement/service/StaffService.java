package cn.reimbursement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.reimbursement.util.ServerResult;

public interface StaffService {
	ServerResult loginByTelAndPassword(HttpServletRequest request,String staffTel,String staffPassword);
	ServerResult<List<String>> selectStaffByCompanyAndDep(String company,String dep);
	void loginOut(HttpServletRequest request);
	ServerResult updateOaStaff()throws Exception;
}
