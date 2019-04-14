package cn.reimbursement.service;

import java.util.List;

import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

public interface ContractStatusService {

	/*
	 * @Description: 查询合同名
	 * 
	 * @return
	 */
	ServerResult<List<String>> selectContractStatusName();
}
