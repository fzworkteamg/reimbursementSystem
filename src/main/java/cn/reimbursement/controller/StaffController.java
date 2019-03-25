package cn.reimbursement.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ServerResult loginByIdAndPassword(HttpServletRequest request,String staffId,String staffPassword) {
		return staffService.loginByIdAndPassword(request, staffId, staffPassword);
	}
	
}
