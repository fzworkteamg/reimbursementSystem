package cn.reimbursement.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.pojo.Bill;
import cn.reimbursement.service.BillService;
import cn.reimbursement.util.LayuiResult;
import cn.reimbursement.util.ServerResult;

@RestController
@RequestMapping("/bill")
public class BillController {

	@Autowired
	private BillService billService;

	@PostMapping("/selctBillByCompany")
	public ServerResult<List<Bill>> selctBillByCompany(HttpServletRequest request) throws Exception {
		return billService.selctBillByCompany(request);
	}

	@PostMapping("/insertBill")
	public ServerResult insertBill(HttpServletRequest request) {
		return billService.insertBill(request);
	}

	@PostMapping("/selectBill")
	public LayuiResult<List<Bill>> selectBill(HttpServletRequest request) {
		return billService.selectBill(request);
	}

	@PostMapping("/selectBillByMonth")
	public LayuiResult<List<Bill>> selectBillByMonth(HttpServletRequest request) throws ParseException {
		return billService.selectBillByMonth(request);
	}

	@PostMapping("/selectBillWaitAudit")
	LayuiResult<List<Bill>> selectBillWaitAudit(HttpServletRequest request) {
		return billService.selectBillByAudit(request,"待审核");
	}
	
	@PostMapping("/selectBillAudited")
	LayuiResult<List<Bill>> selectBillAudited(HttpServletRequest request) {
		return billService.selectBillByAudit(request,"已审核");
	}
	
	@PostMapping("/auditBill")
	ServerResult auditBill(HttpServletRequest request, @RequestParam("billId")String billId, @RequestParam("audit_summary")String auditSummary, @RequestParam("selectContractStatus")String contractStatus,
			@RequestParam("selectInvoiceStatus")String invoiceStatus) {
		return billService.auditBill(request, billId, auditSummary, contractStatus, invoiceStatus);
	}
}
