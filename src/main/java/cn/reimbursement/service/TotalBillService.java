package cn.reimbursement.service;
/**
* @author linweijie
* @date 2019年4月16日
*/

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.reimbursement.pojo.Bill;
import cn.reimbursement.util.LayuiResult;

public interface TotalBillService {
	
	/*
	 * @Description: 通过总帐单Id查询对应的账单
	 * @param request
	 * @return
	 */
	LayuiResult<List<Bill>> selectBillByTotalBillId(HttpServletRequest request);
}
