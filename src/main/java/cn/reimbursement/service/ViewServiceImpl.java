package cn.reimbursement.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.reimbursement.dao.CurrentStepDao;
import cn.reimbursement.dao.ProcessStatusDao;
import cn.reimbursement.enums.InfoEnum;
import net.sf.json.JSONObject;

@Service
public class ViewServiceImpl implements ViewService {

	@Autowired
	private ProcessStatusDao processStatusDao;
	@Autowired
	private CurrentStepDao currentStepDao;

	public void toBillDetail(String data, HttpServletRequest request) {
		JSONObject object = JSONObject.fromObject(data);
		HttpSession session = (HttpSession) request.getSession();
		session.setAttribute("bill", object);
		String billId=(String) object.get("billId");
		int billCount = processStatusDao.selectCountByBillId(billId);
		int currentStep = currentStepDao.selectCurrentStepByBillId(billId);
		if (billCount <= currentStep) {
			session.setAttribute("status", "1");
			return;
		}
		processStatusDao.updateStateByStep(billId, currentStep + 1, InfoEnum.AUDITED.toString());
		currentStepDao.updateCurrentStepNumberByBillId(billId, currentStep + 1);
		processStatusDao.updateStateByStep(billId, currentStep + 2, InfoEnum.WAIT_AUDIT.toString()); 
		session.setAttribute("status", "0");
	}

}
