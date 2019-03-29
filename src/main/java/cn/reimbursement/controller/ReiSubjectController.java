package cn.reimbursement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.service.ReiSubjectService;
import cn.reimbursement.util.ServerResult;

@RestController
@RequestMapping("/reiSubject")
public class ReiSubjectController {
	
	@Autowired
	private ReiSubjectService reiSubjectService;
	
	@PostMapping("/selectDepContentCommentByCompanyDep")
	public List<Map<String,String>> selectDepContentCommentByCompanyDep(String company,String dep) {
		return reiSubjectService.selectDepContentCommentByCompanyDep(company,dep);
	}
	
}
