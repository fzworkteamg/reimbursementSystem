package cn.reimbursement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {
	
	
	@RequestMapping("/toLogin")
	public String toLigin() {
		return "login";
	}
	
	@RequestMapping("/toIndex")
	public String toIndex() {
		return "index";
	}
	
	
}