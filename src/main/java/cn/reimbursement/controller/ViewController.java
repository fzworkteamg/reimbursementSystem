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
import net.sf.json.JSONObject;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

@Controller
@RequestMapping("/view")
public class ViewController {

	@Autowired
	private StaffService staffService;
	@Autowired
	private ViewService viewService;

	/*
	 * @Description: 跳转到登陆页面
	 * 
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}

	/*
	 * @Description: 跳转到主页
	 * 
	 * @return
	 */
	@RequestMapping("/toIndex")
	public String toIndex() {
		return "index";
	}

	/*
	 * @Description: 跳转到展示页面
	 * 
	 * @return
	 */
	@RequestMapping("/toShow")
	public String toShow() {
		return "show";
	}

	/*
	 * @Description: 跳转到增加账单页面
	 * 
	 * @return
	 */
	@RequestMapping("/toAddBill")
	public String toAddBill(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.setAttribute("totalBillId", request.getParameter("totalBillId"));
		return "bill/addBill";
	}

	/*
	 * @Description: 跳转到账单详情页
	 * 
	 * @param data
	 * 
	 * @param request
	 * 
	 * @return
	 */
	@RequestMapping("/toBillDetail")
	public String toBillDetail(String data, HttpServletRequest request) {
		viewService.toBillDetail(data, request);
		HttpSession session = (HttpSession) request.getSession();
		JSONObject object = JSONObject.fromObject(data);
		if (session.getAttribute(SessionEnum.STATUS.getValue()).equals(NumberEnum.TWO.getValue())) {
			session.setAttribute(SessionEnum.BILL.getValue(), object);
			return "bill/billAlter";
		}
		session.setAttribute(SessionEnum.BILL.getValue(), object);
		return "bill/audit";
	}

	/*
	 * @Description: 注销功能，返回到登陆页面
	 * 
	 * @param request
	 * 
	 * @return
	 */
	@RequestMapping("loginOut")
	public String loginOut(HttpServletRequest request) {
		staffService.loginOut(request);
		return "login";
	}

	/*
	 * @Description:弹出总账新增页面
	 *
	 * @param request
	 *
	 * @return
	 */
	@RequestMapping("toAddTotalBill")
	public String toAddTotalBill(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.setAttribute("isTotal", request.getParameter("isTotal"));
		return "bill/addTotalBill";
	}

}
