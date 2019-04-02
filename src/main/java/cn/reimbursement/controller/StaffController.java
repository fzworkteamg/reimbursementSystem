package cn.reimbursement.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.service.StaffService;
import cn.reimbursement.util.ServerResult;

@RestController
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private StaffService staffService;

	@PostMapping("/loginByIdAndPassword")
	public ServerResult loginByIdAndPassword(@RequestBody Map<String,String> map,HttpServletRequest request) {
		return staffService.loginByIdAndPassword(request, map.get("staffId"), map.get("staffPassword"));
	}
	
	@PostMapping("/selectStaffByCompanyAndDep")
	public ServerResult<List<String>> selectStaffByCompanyAndDep(String company, String dep) {
		return staffService.selectStaffByCompanyAndDep(company, dep);
	}
	
	@PostMapping("/loginOut")
	public ServerResult loginOut(HttpServletRequest request) {
		return null;
	}
	
}
