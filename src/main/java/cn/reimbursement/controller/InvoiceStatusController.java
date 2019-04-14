package cn.reimbursement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.service.InvoiceStatusService;
import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

@RestController
@RequestMapping("/invoiceStatus")
public class InvoiceStatusController {

	@Autowired
	private InvoiceStatusService invoiceStatusService;

	/*
	 * @Description: 查询所有发票状态名
	 * 
	 * @return
	 */
	@PostMapping("/selectInvoiceStatus")
	public ServerResult<List<String>> selectInvoiceStatusName() {
		return invoiceStatusService.selectInvoiceStatusName();
	}

}
