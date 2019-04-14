package cn.reimbursement.service;

import java.util.List;
import java.util.Map;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

public interface ProcessStatusService {

	/*
	 * @Description: 通过账单号查询流程名、流程状态、流程意见
	 * 
	 * @param billId
	 * 
	 * @return
	 */
	List<Map<String, String>> selectNameStateOptionById(String billId);
}
