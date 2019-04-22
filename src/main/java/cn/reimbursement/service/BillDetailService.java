package cn.reimbursement.service;
/**
* @author linweijie
* @date 2019年4月22日
*/

import java.util.List;

import cn.reimbursement.pojo.BillDetail;
import cn.reimbursement.util.LayuiResult;

public interface BillDetailService {
	/*
	 * @Description: 通过账单号分页查询
	 * @param billId
	 * @return
	 */
	LayuiResult<List<BillDetail>> selectBillDetailByBillId(String billId, int limit, int page);
}
