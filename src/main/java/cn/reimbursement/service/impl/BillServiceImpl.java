package cn.reimbursement.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.reimbursement.dao.BillDao;
import cn.reimbursement.dao.CurrentStepDao;
import cn.reimbursement.dao.ProcessDao;
import cn.reimbursement.dao.ProcessStatusDao;
import cn.reimbursement.dao.TotalBillDetailDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.enums.SessionEnum;
import cn.reimbursement.pojo.Bill;
import cn.reimbursement.pojo.Staff;
import cn.reimbursement.service.BillService;
import cn.reimbursement.util.LayuiResult;
import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillDao billDao;
	@Autowired
	private ProcessDao processDao;
	@Autowired
	private ProcessStatusDao processStatusDao;
	@Autowired
	private CurrentStepDao currentStepDao;
//	@Autowired
//	private TotalBillDetailDao totalBillDetailDao;

	public ServerResult<List<Bill>> selctBillByCompany(HttpServletRequest request) throws Exception {
		Staff staff = (Staff) request.getSession().getAttribute(SessionEnum.STAFF.getValue());
		List<Bill> billList = billDao.selectBillByCompany(staff.getCompanyName());
		return new ServerResult<List<Bill>>(0, InfoEnum.SUCCESS.getValue(), billList);
	}

	@Transactional
	public ServerResult<String> insertBill(HttpServletRequest httpServletRequest) {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
		// 判断总帐ID是否为空
//		String isTotal = (String) request.getSession().getAttribute("isTotal");
//		String totalBillId = (String) request.getSession().getAttribute("totalBillId");
		String billId = request.getParameter("bill_id_pre") + request.getParameter("bill_id_suff");
//		if (isTotal != null || !StringUtils.isEmpty(isTotal)) {
//			if (totalBillDetailDao.insertTotalBillDetail(totalBillId, billId) == 0) {
//				return new ServerResult<String>(1, InfoEnum.FAIL.toString());
//			}
//		}
		Map<String, String[]> requestMap = request.getParameterMap();
		String processContent = processDao.selectProcessByCompanyAndDepartment(request.getParameter("staffCompany"),
				request.getParameter("staffDep"));
		if (processContent == null)
			return new ServerResult<String>(1, InfoEnum.FAIL.toString());
		Enumeration<String> keyEnumeration = request.getParameterNames();
		Map<String, String> billMap = new HashMap<String, String>();
		String[] keyArray = new String[] { "bill_id_pre", "bill_id_suff", "staffCompany", "staffDep" };
		while (keyEnumeration.hasMoreElements()) {
			String key = keyEnumeration.nextElement();
			boolean isPut = true;
			for (int i = 0; i < keyArray.length; i++) {
				if (StringUtils.equals(keyArray[i], key)) {
					isPut = false;
					break;
				}
			}
			if (isPut == true)
				billMap.put(key, requestMap.get(key)[0]);
		}
		billMap.put("bill_id", billId);
		String[] processContents = processContent.split("\\|");
		for (int i = 0; i < processContents.length;)
			if (processStatusDao.insertProcessStatus(billId, processContents[i],
					i == 0 ? InfoEnum.WAIT_AUDIT.getValue() : "", ++i,
					request.getParameter("bill_belong_company")) == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return new ServerResult<String>(1, InfoEnum.FAIL.getValue());
			}
		if (currentStepDao.insertCurrentStep(billId) == 0) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new ServerResult<String>(1, InfoEnum.FAIL.getValue());
		}
		if (billDao.selectBillCountById(billId) > 0) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new ServerResult<String>(1, InfoEnum.FAIL.getValue());
		}
		if (billDao.insertBill(billMap) == 0) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new ServerResult<String>(1, InfoEnum.FAIL.getValue());
		}
		if (billDao.selectBillCountById(billId) > 0) {
			return new ServerResult<String>(0, InfoEnum.SUCCESS.getValue());
		}
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return new ServerResult<String>(1, InfoEnum.FAIL.getValue());
	}

	public LayuiResult<List<Bill>> selectBill(HttpServletRequest request) {
		Map<String, String[]> requestMap = request.getParameterMap();
		Enumeration<String> keyEnumeration = request.getParameterNames();
		Map<String, Object> billMap = new HashMap<String, Object>();
		while (keyEnumeration.hasMoreElements()) {
			String key = keyEnumeration.nextElement();
			if (requestMap.get(key)[0].toString().endsWith("--"))
				billMap.put(key, "");
			else
				billMap.put(key, requestMap.get(key)[0]);
		}
		int limit = Integer.parseInt(request.getParameter("limit"));
		int page = Integer.parseInt(request.getParameter("page"));
		billMap.put("limit", limit);
		billMap.put("start", (limit * (page - 1)));
		List<Bill> billList = billDao.selectBill(billMap);
		return new LayuiResult<List<Bill>>(InfoEnum.SUCCESS.getValue(), billList, 0, billDao.selectBillCount(billMap));
	}

	public LayuiResult<List<Bill>> selectBillByMonth(HttpServletRequest request) throws ParseException {
		String date = request.getParameter("date");
		List<Bill> billList = billDao.selectBillByMonth(date.split("-")[0], date.split("-")[1]);
		return new LayuiResult<List<Bill>>(InfoEnum.SUCCESS.getValue(), billList, 0, billList.size());
	}

	public LayuiResult<List<Bill>> selectBillByAudit(HttpServletRequest request, String processStatusState) {
		Staff staff = (Staff) request.getSession().getAttribute(SessionEnum.STAFF.getValue());
		if (staff == null)
			return new LayuiResult<List<Bill>>(InfoEnum.FAIL.getValue(), null, 1, 0);
		String staffName = (StringUtils.equals(InfoEnum.AUDITED.getValue(), processStatusState)
				|| (StringUtils.equals(InfoEnum.REJECT.getValue(), processStatusState))) ? staff.getStaffName() : "";
		String processStatusProcessName = staff.getDepName() + "-" + staff.getDutyName();
		int limit = Integer.parseInt(request.getParameter("limit"));
		int page = Integer.parseInt(request.getParameter("page"));
		List<String> billIdList = processStatusDao.selectProcessStatusBillIds(staff.getCompanyName(),
				processStatusProcessName, processStatusState, staffName, limit, limit * (page - 1));
		List<Bill> billList = new ArrayList<>();
		if (StringUtils.isNotBlank(staffName)) {
			for (String billId : billIdList) {
				billList.add(billDao.selectBillById(billId));
			}
		} else {
			for (String billId : billIdList) {
				if (1 != billDao.selectIsEndById(billId)) {
					billList.add(billDao.selectBillById(billId));
				}
			}
		}
		return new LayuiResult<List<Bill>>(InfoEnum.SUCCESS.getValue(), billList, 0,
				processStatusDao.selectProcessStatusBillIdsCount(staff.getCompanyName(), processStatusProcessName,
						processStatusState, staffName));
	}

	@Transactional
	public ServerResult<String> auditBill(HttpServletRequest request, String billId, String auditSummary,
			String contractStatus, String invoiceStatus) {
		Staff staff = (Staff) (request.getSession().getAttribute(SessionEnum.STAFF.getValue()));
		int currentStepNumber = currentStepDao.selectCurrentStepByBillId(billId);
		int processStatusCount = processStatusDao.selectCountByBillId(billId);

		if (currentStepNumber < processStatusCount) {
			if (processStatusDao.updateStateByStep(billId, currentStepNumber, InfoEnum.AUDITED.getValue(),
					staff.getStaffName(), auditSummary) == 0
					|| processStatusDao.updateStateByStep(billId, ++currentStepNumber, InfoEnum.WAIT_AUDIT.getValue(),
							"", "") == 0
					|| currentStepDao.updateCurrentStepNumberByBillId(billId, currentStepNumber) == 0
					|| billDao.updateBillStatusById(billId, contractStatus, invoiceStatus) == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return new ServerResult<String>(1);
			}
			return new ServerResult<String>(0);

		}
		if (currentStepNumber == processStatusCount) {
			if (billDao.updateBillEnd(billId) == 0
					|| processStatusDao.updateStateByStep(billId, currentStepNumber, InfoEnum.AUDITED.getValue(),
							staff.getStaffName(), auditSummary) == 0
					|| billDao.updateBillStatusById(billId, contractStatus, invoiceStatus) == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return new ServerResult<String>(1);
			}
		}
		return new ServerResult<String>(0);
	}

	@Transactional
	public ServerResult<String> rejectBill(HttpServletRequest request, String billId, String opinion) {
		int currentStep = currentStepDao.selectCurrentStepByBillId(billId);
		Staff staff = (Staff) (request.getSession().getAttribute(SessionEnum.STAFF.getValue()));
		if (processStatusDao.updateStateByStep(billId, currentStep, InfoEnum.REJECT.getValue(), staff.getStaffName(),
				opinion) == 0) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new ServerResult<String>(1);
		}
		if (currentStep != 1) {
			if (currentStepDao.updateCurrentStepNumberByBillId(billId, --currentStep) == 0 || processStatusDao
					.updateStateByStep(billId, currentStep, InfoEnum.WAIT_AUDIT.getValue(), "", "") == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return new ServerResult<String>(1);
			}
		}
		return new ServerResult<String>(0);
	}

	@Transactional
	public ServerResult<String> updateBill(HttpServletRequest httpServletRequest) {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
		Map<String, String[]> requestMap = request.getParameterMap();
		Enumeration<String> keyEnumeration = request.getParameterNames();
		Map<String, String> billMap = new HashMap<String, String>();
		while (keyEnumeration.hasMoreElements()) {
			String key = keyEnumeration.nextElement();
			billMap.put(key, requestMap.get(key)[0]);
		}
		String billId = request.getParameter("bill_id");
		if (billDao.updateBill(billMap) == 0) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new ServerResult<String>(1, InfoEnum.FAIL.getValue());
		}
		Staff staff = (Staff) (request.getSession().getAttribute(SessionEnum.STAFF.getValue()));
		if (staff == null) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new ServerResult<String>(1, InfoEnum.FAIL.getValue());
		}
		if (processStatusDao.updateStateByStep(billId, 1, InfoEnum.WAIT_AUDIT.getValue(), staff.getStaffName(),
				"") == 0) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new ServerResult<String>(1);
		}
		return new ServerResult<String>(0, InfoEnum.SUCCESS.getValue());
	}

	@Transactional
	public ServerResult<String> deleteBill(String billId) {
		if (currentStepDao.deleteCurrentStepByBillId(billId) == 0
				|| processStatusDao.deleteProcessStatusByBillId(billId) == 0 || billDao.deleteBillById(billId) == 0) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new ServerResult<String>(1);
		}
		return new ServerResult<String>(0);
	}

}
