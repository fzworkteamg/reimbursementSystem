package cn.reimbursement.service;

import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

public interface ProcessService {
	/*
	 * @Description: 通过账单号查询流程内容
	 * 
	 * @param billId
	 * 
	 * @return
	 */
	public ServerResult<String> selectProcessContentByBillId(String billId);
}
