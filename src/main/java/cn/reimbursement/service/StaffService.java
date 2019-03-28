package cn.reimbursement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.reimbursement.util.ServerResult;

public interface StaffService {
	ServerResult loginByIdAndPassword(HttpServletRequest request,String staffId,String staffPassword);
	ServerResult<List<String>> selectStaffByCompanyAndDep(String company,String dep);
}
