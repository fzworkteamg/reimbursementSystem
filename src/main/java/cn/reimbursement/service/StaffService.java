package cn.reimbursement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

public interface StaffService {

	/*
	 * @Description: 登陆功能
	 * 
	 * @param request
	 * 
	 * @param staffTel
	 * 
	 * @param staffPassword
	 * 
	 * @return
	 */
	ServerResult loginByTelAndPassword(HttpServletRequest request, String staffTel, String staffPassword);

	/*
	 * @Description: 通过公司部门筛选员工
	 * 
	 * @param company
	 * 
	 * @param dep
	 * 
	 * @return
	 */
	ServerResult<List<String>> selectStaffByCompanyAndDep(String company, String dep);

	/*
	 * @Description: 注销功能
	 * 
	 * @param request
	 */
	void loginOut(HttpServletRequest request);

	/*
	 * @Description: 同步企业微信OA账户
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	ServerResult updateOaStaff() throws Exception;
}
