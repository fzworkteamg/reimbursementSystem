package cn.reimbursement.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.reimbursement.pojo.Bill;
import cn.reimbursement.util.LayuiResult;
import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

public interface BillService {
	/*
	 * @Description: 通过公司查询账单
	 * 
	 * @param request
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	ServerResult<List<Bill>> selctBillByCompany(HttpServletRequest request) throws Exception;

	/*
	 * @Description: 增加账单
	 * 
	 * @param request
	 * 
	 * @return
	 */
	ServerResult insertBill(HttpServletRequest request);

	/*
	 * @Description: 通过条件查询账单
	 * 
	 * @param request
	 * 
	 * @return
	 */
	LayuiResult<List<Bill>> selectBill(HttpServletRequest request);

	/*
	 * @Description: 查询本月账单
	 * 
	 * @param request
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	LayuiResult<List<Bill>> selectBillByMonth(HttpServletRequest request) throws ParseException;

	/*
	 * @Description: 查询指定状态的账单：待审核，已审核，驳回
	 * 
	 * @param request
	 * 
	 * @param processStatusState
	 * 
	 * @return
	 */
	LayuiResult<List<Bill>> selectBillByAudit(HttpServletRequest request, String processStatusState);

	/*
	 * @Description: 审核账单
	 * 
	 * @param request
	 * 
	 * @param billId
	 * 
	 * @param auditSummary
	 * 
	 * @param contractStatus
	 * 
	 * @param invoiceStatus
	 * 
	 * @return
	 */
	ServerResult auditBill(HttpServletRequest request, String billId, String auditSummary, String contractStatus,
			String invoiceStatus);

	/*
	 * @Description: 驳回账单
	 * 
	 * @param request
	 * 
	 * @param billId
	 * 
	 * @param opinion
	 * 
	 * @return
	 */
	ServerResult rejectBill(HttpServletRequest request, String billId, String opinion);

	/*
	 * @Description: 更新账单
	 * 
	 * @param request
	 * 
	 * @return
	 */
	ServerResult updateBill(HttpServletRequest request);

	/*
	 * @Description: 删除账单
	 * 
	 * @param billId
	 * 
	 * @return
	 */
	ServerResult deleteBill(String billId);
}
