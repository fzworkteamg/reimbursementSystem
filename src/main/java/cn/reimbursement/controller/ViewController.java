package cn.reimbursement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.reimbursement.enums.NumberEnum;
import cn.reimbursement.enums.SessionEnum;
import cn.reimbursement.service.StaffService;
import cn.reimbursement.service.ViewService;
import cn.reimbursement.util.Util;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/view")
public class ViewController {
	
	@Autowired
	private StaffService staffService;
	@Autowired
	private ViewService viewService;

	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping("/toIndex")
	public String toIndex() {
		return "index";
	}

	@RequestMapping("/toShow")
	public String toShow(){
		return "show";
	}

	@RequestMapping("/toAddBill")
	public String toAddBill(){
		return "addBill";
	}

	@RequestMapping("/toBillDetail")
	public String toBillDetail(String data, HttpServletRequest request){
		viewService.toBillDetail(data, request);
		HttpSession session = (HttpSession) request.getSession();
		JSONObject object = JSONObject.fromObject(data);
		if(session.getAttribute(SessionEnum.STATUS.getValue()).equals(NumberEnum.TWO.getValue())) {
			session.setAttribute(SessionEnum.BILL.getValue(),object);
			return "billAlter";
		}
		session.setAttribute(SessionEnum.BILL.getValue(),object);
		return "audit";
	}

	@RequestMapping("loginOut")
	public String loginOut(HttpServletRequest request) {
		staffService.loginOut(request);
		return "login";
	}

}
