package cn.reimbursement.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.reimbursement.pojo.Bill;
import cn.reimbursement.util.LayuiResult;
import cn.reimbursement.util.ServerResult;

public interface BillService {
	ServerResult<List<Bill>> selctBillByCompany(HttpServletRequest request) throws Exception;
	ServerResult insertBill(Map<String,Object> billMap);
	LayuiResult<List<Bill>> selectBill(HttpServletRequest request);
}
