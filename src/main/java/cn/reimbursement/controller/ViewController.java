package cn.reimbursement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {
	
	
	@PostMapping("/toLogin")
	public String selectProcessById(Integer processId) {
		return "login";
	}
	
}
