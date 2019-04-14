package cn.reimbursement.service;

import java.util.List;

import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

public interface CompanyService {

	/*
	 * @Description: 查询所有公司
	 * 
	 * @return
	 */
	ServerResult<List<String>> selectCompany();
}
