package cn.reimbursement.service;

import javax.servlet.http.HttpServletRequest;

import cn.reimbursement.util.ServerResult;

public interface StaffService {
	public ServerResult loginByIdAndPassword(HttpServletRequest request,String staffId,String staffPassword);
}
