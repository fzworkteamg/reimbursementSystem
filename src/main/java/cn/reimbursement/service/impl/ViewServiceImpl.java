package cn.reimbursement.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.BillDao;
import cn.reimbursement.dao.CurrentStepDao;
import cn.reimbursement.dao.ProcessStatusDao;
import cn.reimbursement.enums.NumberEnum;
import cn.reimbursement.enums.SessionEnum;
import cn.reimbursement.pojo.Staff;
import cn.reimbursement.service.ViewService;
import net.sf.json.JSONObject;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

@Service
public class ViewServiceImpl implements ViewService {

	@Autowired
	private ProcessStatusDao processStatusDao;
	@Autowired
	private CurrentStepDao currentStepDao;
	@Autowired
	private BillDao billDao;

	public void toBillDetail(String data, HttpServletRequest request) {
		JSONObject object = JSONObject.fromObject(data);
		HttpSession session = (HttpSession) request.getSession();
		session.setAttribute(SessionEnum.BILL_ID.getValue(), object);
		String billId = (String) object.get(SessionEnum.BILL_ID.getValue());
		int currentStep = currentStepDao.selectCurrentStepByBillId(billId);
		Staff staff = (Staff) session.getAttribute(SessionEnum.STAFF.getValue());
		String company = staff.getCompanyName();
		if (currentStep == 1) {
			if (processStatusDao.selectRejectByBillIdAndStep(billId, currentStep) == 1
					&& billDao.selectRegist(billId, company, staff.getStaffName()) != 0) {
				session.setAttribute(SessionEnum.STATUS.getValue(), NumberEnum.TWO.getValue());
				return;
			}
		}
		int billCount = processStatusDao.selectCountByBillId(billId);
		if (billCount < currentStep) {
			session.setAttribute(SessionEnum.STATUS.getValue(), NumberEnum.ONE.getValue());
			return;
		}
		String processName = staff.getDepName() + "-" + staff.getDutyName();
		int count = processStatusDao.selectProcessStatusByBillIdProcessName(billId, company, processName);
		if (count > 0)
			session.setAttribute(SessionEnum.STATUS.getValue(), NumberEnum.ZERO.getValue());
		else
			session.setAttribute(SessionEnum.STATUS.getValue(), NumberEnum.ONE.getValue());
	}

}
