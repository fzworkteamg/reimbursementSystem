package cn.reimbursement.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.reimbursement.dao.BillDao;
import cn.reimbursement.dao.CurrentStepDao;
import cn.reimbursement.dao.ProcessDao;
import cn.reimbursement.dao.ProcessStatusDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.enums.SessionEnum;
import cn.reimbursement.pojo.Bill;
import cn.reimbursement.pojo.Staff;
import cn.reimbursement.util.LayuiResult;
import cn.reimbursement.util.ServerResult;

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

	public ServerResult<List<Bill>> selctBillByCompany(HttpServletRequest request) throws Exception {
		Staff staff = (Staff) request.getSession().getAttribute("staff");
		String companyName = staff.getCompanyName();
		List<Bill> billList = billDao.selectBillByCompany(companyName);
		return new ServerResult<List<Bill>>(0, InfoEnum.SUCCESS.getValue(), billList);
	}

	public ServerResult insertBill(HttpServletRequest httpServletRequest) {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
		String processContent = processDao.selectProcessByCompanyAndDepartment(request.getParameter("staffCompany"),
				request.getParameter("staffDep"));
		if (processContent == null)
			return new ServerResult(1, InfoEnum.FAIL.toString());
		String[] processContents = processContent.split("\\|");
		String billId = request.getParameter("bill_id_pre") + request.getParameter("bill_id_suff");
		for (int i = 0; i < processContents.length;)
			processStatusDao.insertProcessStatus(billId, processContents[i], i == 0 ? "待审核" : "", i++,
					request.getParameter("bill_belong_company"));
		currentStepDao.insertCurrentStep(billId);
		Map<String, String> billMap = new HashMap<String, String>();
		billMap.put("bill_id", billId);
		billMap.put("bill_amount", request.getParameter("bill_amount"));
		billMap.put("bill_company", request.getParameter("bill_company"));
		billMap.put("bill_produce_date", request.getParameter("bill_produce_date"));
		billMap.put("bill_reimbursement_dep", request.getParameter("bill_reimbursement_dep"));
		billMap.put("bill_registrant_date", request.getParameter("bill_registrant_date"));
		billMap.put("bill_type", request.getParameter("bill_type"));
		billMap.put("bill_summary", request.getParameter("bill_summary"));
		billMap.put("bill_subject", request.getParameter("bill_subject"));
		billMap.put("bill_belong_company", request.getParameter("bill_belong_company"));
		billMap.put("bill_reimbursement_person", request.getParameter("bill_reimbursement_person"));
		billMap.put("bill_invoice_status_name", request.getParameter("bill_invoice_status_name"));
		billMap.put("bill_charge_person", request.getParameter("bill_charge_person"));
		billMap.put("bill_invoice_amount", request.getParameter("bill_invoice_amount"));
		billMap.put("bill_registrant_person", request.getParameter("bill_registrant_person"));
		billMap.put("bill_contract_status_name", request.getParameter("bill_contract_status_name"));
		billMap.put("bill_attribute", request.getParameter("bill_attribute"));
		int count = billDao.selectBillCountById(billId);
		if (count > 0) {
			return new ServerResult(1, InfoEnum.FAIL.getValue());
		}
		billDao.insertBill(billMap);
		count = billDao.selectBillCountById(billId);
		if (count > 0) {
			return new ServerResult(0, InfoEnum.SUCCESS.getValue());
		}
		return new ServerResult(1, InfoEnum.FAIL.getValue());
	}

	public LayuiResult<List<Bill>> selectBill(HttpServletRequest request) {
		Map<String, String> billMap = new HashMap<String, String>();
		if (request.getParameter("company").endsWith("--")) {
			billMap.put("billCompany", "");
		} else {
			billMap.put("billCompany", request.getParameter("company"));
		}
		if (request.getParameter("dep").endsWith("--")) {
			billMap.put("billDep", "");
		} else {
			billMap.put("billDep", request.getParameter("dep"));
		}
		if (request.getParameter("attribute").endsWith("--"))
			billMap.put("billAttribute", "");
		else
			billMap.put("billAttribute", request.getParameter("attribute"));

		if (request.getParameter("type").endsWith("--"))
			billMap.put("billType", "");
		else
			billMap.put("billType", request.getParameter("type"));

		if (request.getParameter("invoice").endsWith("--"))
			billMap.put("billInvoiceStatusName", "");
		else
			billMap.put("billInvoiceStatusName", request.getParameter("invoice"));

		if (request.getParameter("contract").endsWith("--"))
			billMap.put("billContractStatusName", "");
		else
			billMap.put("billContractStatusName", request.getParameter("contract"));
		billMap.put("billId", request.getParameter("billId"));
		billMap.put("billChargePerson", request.getParameter("chargePerson"));
		billMap.put("billRegistrantPerson", request.getParameter("registrantPerson"));
		billMap.put("billSummary", request.getParameter("summary"));
		billMap.put("billSubject", request.getParameter("subject"));
		billMap.put("billProduceDate", request.getParameter("produceData"));
		billMap.put("billRegistrantDate", request.getParameter("registrantDate"));
		billMap.put("billEndDate", request.getParameter("endDate"));
		billMap.put("amountLow", request.getParameter("amountLow"));
		billMap.put("amoutHigh", request.getParameter("amoutHigh"));
		List<Bill> billList = billDao.selectBill(billMap);
		return new LayuiResult<List<Bill>>(InfoEnum.SUCCESS.getValue(), billList, 0, billList.size());
	}

	public LayuiResult<List<Bill>> selectBillByMonth(HttpServletRequest request) throws ParseException {
		String date = request.getParameter("date");
		List<Bill> billList = billDao.selectBillByMonth(date.split("-")[0], date.split("-")[1]);
		return new LayuiResult<List<Bill>>(InfoEnum.SUCCESS.getValue(), billList, 0, billList.size());
	}

	public LayuiResult<List<Bill>> selectBillByAudit(HttpServletRequest request, String processStatusState) {
		Staff staff = (Staff) request.getSession().getAttribute("staff");
		if (staff == null)
			return new LayuiResult<List<Bill>>(InfoEnum.FAIL.getValue(), null, 1, 0);
		String staffName = (InfoEnum.AUDITED.getValue().equals(processStatusState) || InfoEnum.REJECT.getValue().equals(processStatusState))  ? staff.getStaffName() : "";
		String processStatusCompany = staff.getCompanyName();
		String processStatusProcessName = staff.getDepName() + "-" + staff.getDutyName();
		List<String> billIdList = processStatusDao.selectProcessStatusBillIds(processStatusCompany,
				processStatusProcessName, processStatusState, staffName);
		List<Bill> billList = new ArrayList<>();
		if (staffName != "") {
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
		return new LayuiResult<List<Bill>>(InfoEnum.SUCCESS.getValue(), billList, 0, billList.size());
	}

	@Transactional
	public ServerResult auditBill(HttpServletRequest request, String billId, String auditSummary, String contractStatus,
			String invoiceStatus) {
		Staff staff = (Staff) (request.getSession().getAttribute(SessionEnum.STAFF.getValue()));
		int currentStepNumber = currentStepDao.selectCurrentStepByBillId(billId);
		int processStatusCount = processStatusDao.selectCountByBillId(billId);
		
		if (currentStepNumber < processStatusCount) {
			if (processStatusDao.updateStateByStep(billId, currentStepNumber, InfoEnum.AUDITED.getValue(),
					staff.getStaffName(), auditSummary) == 0
					|| processStatusDao.updateStateByStep(billId, ++currentStepNumber, InfoEnum.WAIT_AUDIT.getValue(),
							"", "") == 0
					|| currentStepDao.updateCurrentStepNumberByBillId(billId, currentStepNumber) == 0
					||  billDao.updateBillStatusById(billId, contractStatus, invoiceStatus) == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return new ServerResult(1);
			}
			return new ServerResult(0);

		}
		if (currentStepNumber == processStatusCount) {
			if (billDao.updateBillEnd(billId) == 0
					|| processStatusDao.updateStateByStep(billId, currentStepNumber, InfoEnum.AUDITED.getValue(),
							staff.getStaffName(), auditSummary) == 0
					|| billDao.updateBillStatusById(billId, contractStatus, invoiceStatus) == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return new ServerResult(1);
			}
		}
		return new ServerResult(0);
	}
	
	@Transactional
	public ServerResult rejectBill(HttpServletRequest request, String billId,String opinion) {
		int currentStep=currentStepDao.selectCurrentStepByBillId(billId);
		Staff staff = (Staff) (request.getSession().getAttribute(SessionEnum.STAFF.getValue()));
		if(processStatusDao.updateStateByStep(billId, currentStep, InfoEnum.REJECT.getValue(), staff.getStaffName(), opinion)==0) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new ServerResult(1);
		}
		if(currentStep!=1) {
			if(currentStepDao.updateCurrentStepNumberByBillId(billId,--currentStep)==0
			|| processStatusDao.updateStateByStep(billId, currentStep, InfoEnum.WAIT_AUDIT.getValue(), "", "")==0){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return new ServerResult(1);
			}
		}
		return new ServerResult(0);
	}

	
	public ServerResult updateBill(HttpServletRequest httpServletRequest) {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
		Map<String, String> billMap = new HashMap<String,String>();
		billMap.put("bill_id", request.getParameter("bill_id"));
		billMap.put("bill_amount", request.getParameter("bill_amount"));
		billMap.put("bill_company", request.getParameter("bill_company"));
		billMap.put("bill_produce_date", request.getParameter("bill_produce_date"));
		billMap.put("bill_reimbursement_dep", request.getParameter("bill_reimbursement_dep"));
		billMap.put("bill_registrant_date", request.getParameter("bill_registrant_date"));
		billMap.put("bill_type", request.getParameter("bill_type"));
		billMap.put("bill_summary", request.getParameter("bill_summary"));
		billMap.put("bill_subject", request.getParameter("bill_subject"));
		billMap.put("bill_belong_company", request.getParameter("bill_belong_company"));
		billMap.put("bill_reimbursement_person", request.getParameter("bill_reimbursement_person"));
		billMap.put("bill_invoice_status_name", request.getParameter("bill_invoice_status_name"));
		billMap.put("bill_charge_person", request.getParameter("bill_charge_person"));
		billMap.put("bill_invoice_amount", request.getParameter("bill_invoice_amount"));
		billMap.put("bill_registrant_person", request.getParameter("bill_registrant_person"));
		billMap.put("bill_contract_status_name", request.getParameter("bill_contract_status_name"));
		billMap.put("bill_attribute", request.getParameter("bill_attribute"));
		System.out.println(billMap.toString());
		int num=billDao.updateBill(billMap);
		if(num==0) 
			return new ServerResult(1,InfoEnum.FAIL.getValue());
		return new ServerResult(0,InfoEnum.SUCCESS.getValue());
	}

}
