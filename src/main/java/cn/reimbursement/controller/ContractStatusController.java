package cn.reimbursement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.service.ContractStatusService;
import cn.reimbursement.util.ServerResult;

@RestController
@RequestMapping("/contractStatus")
public class ContractStatusController {
	
	@Autowired
	private ContractStatusService contractStatusService;
	
	
	//查询所有合同状态名
	@PostMapping("/selectContractStatus")
	public ServerResult<List<String>> selectInvoiceStatusName(){
		return contractStatusService.selectContractStatusName();
	}
	
}
