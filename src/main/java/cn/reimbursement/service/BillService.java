package cn.reimbursement.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.reimbursement.pojo.Bill;
import cn.reimbursement.util.LayuiResult;
import cn.reimbursement.util.ServerResult;

public interface BillService {
	ServerResult<List<Bill>> selctBillByCompany(HttpServletRequest request) throws Exception;
	ServerResult insertBill(HttpServletRequest request);
	LayuiResult<List<Bill>> selectBill(HttpServletRequest request);
	LayuiResult<List<Bill>> selectBillByMonth(HttpServletRequest request) throws ParseException ;
	LayuiResult<List<Bill>> selectBillByAuditor (HttpServletRequest request);
}
