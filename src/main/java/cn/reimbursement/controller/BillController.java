package cn.reimbursement.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public ServerResult<List<Bill>> selctBillByCompany(HttpServletRequest request) throws Exception{
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
	
	
}
