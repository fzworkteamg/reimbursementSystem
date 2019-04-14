package cn.reimbursement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.service.ContractStatusService;
import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

@RestController
@RequestMapping("/contractStatus")
public class ContractStatusController {

	@Autowired
	private ContractStatusService contractStatusService;

	/*
	 * @Description: 查询所有合同状态名
	 * 
	 * @return
	 */
	@PostMapping("/selectContractStatus")
	public ServerResult<List<String>> selectInvoiceStatusName() {
		return contractStatusService.selectContractStatusName();
	}

}
