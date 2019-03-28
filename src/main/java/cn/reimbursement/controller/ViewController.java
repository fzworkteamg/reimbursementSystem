package cn.reimbursement.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.reimbursement.util.Util;

@Controller
@RequestMapping("/view")
public class ViewController {
	
	
	@RequestMapping("/toLogin")
	public String toLigin() {
		return "login";
	}
	
	@RequestMapping("/toIndex")
	public String toIndex(HttpServletRequest request) {
		if(Util.isLogin(request))
			return "index";
		return "login";
	}

	@RequestMapping("/toShow")
	public String toShow(HttpServletRequest request){
		if(Util.isLogin(request))
			return "show";
		return "login";
	}

	@RequestMapping("/toAddBill")
	public String toAddBill(HttpServletRequest request){
		if(Util.isLogin(request))
			return "addBill";
		return "login";
	}
	
}
