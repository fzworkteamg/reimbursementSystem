package cn.reimbursement.controller;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSObject;
import org.apache.ibatis.jdbc.SqlBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.reimbursement.util.Util;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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

	@RequestMapping("/toBillDetail")
	public String toBillDetail(String data, HttpServletRequest request){
		System.out.println(data);
		if(Util.isLogin(request))
			return "audit";
		return "login";
	}
	
}
