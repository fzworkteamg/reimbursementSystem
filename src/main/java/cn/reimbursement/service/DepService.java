package cn.reimbursement.service;

import java.util.List;

import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

public interface DepService {

	/*
	 * @Description: 查询指定公司下的所有部门
	 * 
	 * @param companyName
	 * 
	 * @return
	 */
	ServerResult<List<String>> selectDepByCompany(String companyName);
}
