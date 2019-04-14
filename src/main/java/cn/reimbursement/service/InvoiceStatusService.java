package cn.reimbursement.service;

import java.util.List;

import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

public interface InvoiceStatusService {

	/*
	 * @Description: 查询所有发票状态名
	 * 
	 * @return
	 */
	ServerResult<List<String>> selectInvoiceStatusName();
}
