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

/**
 * @author linweijie
 * @date 2019年4月14日
 */

@RestController
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private StaffService staffService;

	/*
	 * @Description: 员工登陆功能
	 * 
	 * @param map
	 * 
	 * @param request
	 * 
	 * @return
	 */
	@PostMapping("/loginByTelAndPassword")
	public ServerResult<String> loginByIdAndPassword(@RequestBody Map<String, String> map, HttpServletRequest request) {
		return staffService.loginByTelAndPassword(request, map.get("staffTel"), map.get("staffPassword"));
	}

	/*
	 * @Description: 通过公司和部门筛选员工
	 * 
	 * @param company
	 * 
	 * @param dep
	 * 
	 * @return
	 */
	@PostMapping("/selectStaffByCompanyAndDep")
	public ServerResult<List<String>> selectStaffByCompanyAndDep(String company, String dep) {
		return staffService.selectStaffByCompanyAndDep(company, dep);
	}

	/*
	 * @Description: 手动和OA数据同步
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	@PostMapping("/updateOaStaff")
	public ServerResult<String> updateOaStaff() throws Exception {
		return staffService.updateOaStaff();
	}

}
