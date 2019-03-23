package cn.reimbursement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.pojo.Process;
import cn.reimbursement.service.ProcessService;
import cn.reimbursement.util.ServerResult;

@RestController
@RequestMapping("/process")
public class ProcessController {
	
	@Autowired
	private ProcessService processService;
	
	@PostMapping("/selectProcessById")
	public ServerResult<Process> selectProcessById(Integer processId) {
		return processService.selectProcessById(processId);
	}
	
}
