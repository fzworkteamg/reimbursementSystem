package cn.reimbursement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.reimbursement.service.StaffService;
import cn.reimbursement.util.Util;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/view")
public class ViewController {
	
	@Autowired
	private StaffService staffService;

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
		if(Util.isLogin(request)){
			JSONObject object = JSONObject.fromObject(data);
			HttpSession session = (HttpSession) request.getSession();
			session.setAttribute("bill",object);
			System.out.println(object);
			return "audit";
		}
		return "login";
	}

	@RequestMapping("loginOut")
	public String loginOut(HttpServletRequest request) {
		staffService.loginOut(request);
		return "login";
	}

}
