package cn.reimbursement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.reimbursement.dao.CompanyDao;
import cn.reimbursement.dao.DepDao;
import cn.reimbursement.dao.StaffDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.enums.SessionEnum;
import cn.reimbursement.pojo.Staff;
import cn.reimbursement.service.StaffService;
import cn.reimbursement.util.ServerResult;
import cn.reimbursement.util.WxUtil;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffDao staffDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private DepDao depDao;

	public ServerResult loginByTelAndPassword(HttpServletRequest request, String staffTel, String staffPassword) {
		Staff staff = staffDao.selectStaffByTel(staffTel);
		if (staff == null || !staff.getStaffPassword().equals(staffPassword))
			return new ServerResult(1, InfoEnum.FAIL.toString());
		HttpSession session = request.getSession();
		staff.setStaffPassword("");
		session.setAttribute(SessionEnum.STAFF.getValue(), staff);
		return new ServerResult(0, InfoEnum.SUCCESS.getValue());
	}

	public ServerResult<List<String>> selectStaffByCompanyAndDep(String company, String dep) {
		List<Staff> staffList = staffDao.selectStaffByCompanyAndDep(company, dep);
		List<String> staffDutyList = new ArrayList<String>();

		for (int i = 0; i < staffList.size(); i++)
			staffDutyList.add(staffList.get(i).getStaffName() + "|" + staffList.get(i).getDutyName());

		return new ServerResult<List<String>>(0, InfoEnum.SUCCESS.getValue(), staffDutyList);
	}

	public void loginOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Staff staff = (Staff) session.getAttribute(SessionEnum.STAFF.getValue());
		if (staff != null)
			session.setAttribute(SessionEnum.STAFF.getValue(), "");
	}

	@Transactional
	public ServerResult updateOaStaff() throws Exception {
		int staffCount = staffDao.selectStaffCount();
		int deleteStaffCount = staffDao.deleteStaff();
		if (staffCount != deleteStaffCount) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new ServerResult(1);
		}
		List<Map<String, Object>> strList = WxUtil.getOaStaffInfo(WxUtil.getAccessToken(), "2", "1");
		for (Map<String, Object> staffMap : strList) {
			String strDep = staffMap.get("department").toString().replaceAll("\\[", "");
			strDep = strDep.replaceAll("\\]", "");
			int depId = Integer.valueOf(strDep);
			if (depId <= 13) {
				String companyName = companyDao.selectCompanyById(2);
				if (StringUtils.isEmpty(companyName)) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return new ServerResult(1);
				}
				staffMap.put("companyName", companyName);
				String depName = depDao.selectDepById(depId);
				if (StringUtils.isEmpty(depName)) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return new ServerResult(1);
				}
				staffMap.put("depName", depName);
			} else if (depId >= 14) {
				String companyName = companyDao.selectCompanyById(depId);
				if (StringUtils.isEmpty(companyName)) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return new ServerResult(1);
				}
				staffMap.put("companyName", companyName);
				String depName = depDao.selectDepById(depId);
				if (StringUtils.isEmpty(depName)) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return new ServerResult(1);
				}
				staffMap.put("depName", depName);
			}
			if (staffDao.insertWxStaff(staffMap) == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return new ServerResult(1);
			}
		}
		return new ServerResult(0);
	}

}
