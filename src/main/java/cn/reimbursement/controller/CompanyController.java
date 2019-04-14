package cn.reimbursement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.service.CompanyService;
import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	/*
	 * @Description: 查询所有公司
	 * 
	 * @return
	 */
	@PostMapping("/selectCompany")
	public ServerResult<List<String>> selectCompany() {
		return companyService.selectCompany();
	}

}
