package cn.reimbursement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.service.ProcessService;
import cn.reimbursement.util.ServerResult;

@RestController
@RequestMapping("/process")
public class ProcessController {
	
	@Autowired
	private ProcessService processService;
	
	//通过账单的id,查询与之对应的流程内容，弹出当前账单流程页面
	//未用
	@PostMapping("/selectProcessContentByBillId")
	public ServerResult<String> selectProcessContentByBillId(String billId) {
		return processService.selectProcessContentByBillId(billId);
	}
	
}
