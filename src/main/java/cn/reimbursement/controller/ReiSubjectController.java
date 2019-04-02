package cn.reimbursement.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.service.ReiSubjectService;
import cn.reimbursement.util.LayuiResult;

@RestController
@RequestMapping("/reiSubject")
public class ReiSubjectController {
	
	@Autowired
	private ReiSubjectService reiSubjectService;
	
	@GetMapping("/selectDepContentCommentByCompanyDep")
	public LayuiResult<List<Map<String, String>>> selectDepContentCommentByCompanyDep(HttpServletRequest request,String page,String limit,String company,String dep) {
		return reiSubjectService.selectDepContentCommentByCompanyDep(request,page,limit,company,dep);
	}
	
}
