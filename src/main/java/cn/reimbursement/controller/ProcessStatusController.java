package cn.reimbursement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.service.ProcessStatusService;

@RestController
@RequestMapping("/processStatus")
public class ProcessStatusController {
	
	@Autowired
	private ProcessStatusService processStatusService;
	
	@RequestMapping("/selectNameStateOptionById")
	public List<Map<String,String>> selectNameStateOptionById(String billId){
		return processStatusService.selectNameStateOptionById(billId);
	}
	
}
