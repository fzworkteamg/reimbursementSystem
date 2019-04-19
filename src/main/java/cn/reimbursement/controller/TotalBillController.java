package cn.reimbursement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.pojo.Bill;
import cn.reimbursement.service.TotalBillService;
import cn.reimbursement.util.LayuiResult;
import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月16日
 */

@RestController
@RequestMapping("/totalBill")
public class TotalBillController {

	@Autowired
	private TotalBillService totalBillService;

	/*
	 * @Description: 通过总帐单Id查询对应的账单
	 * 
	 * @param request
	 * 
	 * @return
	 */
	@PostMapping("/selectBillByTotalBillId")
	public LayuiResult<List<Bill>> selectBillByTotalBillId(HttpServletRequest request) {
		return totalBillService.selectBillByTotalBillId(request);
	}

	@PostMapping("/deleteTotalBillDetailByTotalBillId")
	public ServerResult<String> deleteTotalBillDetailByTotalBillId(HttpServletRequest request) {
		return totalBillService.deleteTotalBillDetailByTotalBillId(request);
	}

}
