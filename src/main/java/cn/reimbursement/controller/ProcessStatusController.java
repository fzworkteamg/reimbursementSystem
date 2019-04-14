package cn.reimbursement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.service.ProcessStatusService;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

@RestController
@RequestMapping("/processStatus")
public class ProcessStatusController {

	@Autowired
	private ProcessStatusService processStatusService;

	/*
	 * @Description: 通过billId查询流程状态表的流程名，审核状态，意见
	 * 
	 * @param billId
	 * 
	 * @return
	 */
	@RequestMapping("/selectNameStateOptionById")
	public List<Map<String, String>> selectNameStateOptionById(String billId) {
		return processStatusService.selectNameStateOptionById(billId);
	}

}
