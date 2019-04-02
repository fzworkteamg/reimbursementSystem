package cn.reimbursement.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.reimbursement.dao.BillDao;
import cn.reimbursement.dao.CurrentStepDao;
import cn.reimbursement.dao.ProcessDao;
import cn.reimbursement.dao.ProcessStatusDao;
import cn.reimbursement.enums.InfoEnum;
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
		return new ServerResult<List<Bill>>(0, InfoEnum.SUCCESS.toString(), billList);
	}

	public ServerResult insertBill(HttpServletRequest httpServletRequest) {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
		String processContent = processDao.selectProcessByCompanyAndDepartment(request.getParameter("staffCompany"), request.getParameter("staffDep"));
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
		int count = billDao.selectBillById(billId);
		if (count > 0) {
			return new ServerResult(1, InfoEnum.FAIL.toString());
		}
		billDao.insertBill(billMap);
		count = billDao.selectBillById(billId);
		if (count > 0) {
			return new ServerResult(0, InfoEnum.SUCCESS.toString());
		}
		return new ServerResult(1, InfoEnum.FAIL.toString());
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
		return new LayuiResult<List<Bill>>(InfoEnum.SUCCESS.toString(), billList, 0, billList.size());
	}

	public LayuiResult<List<Bill>> selectBillByMonth(HttpServletRequest request) throws ParseException {
		String date=request.getParameter("date");
		List<Bill> billList=billDao.selectBillByMonth(date.split("-")[0],date.split("-")[1]);
		return new LayuiResult<List<Bill>>(InfoEnum.SUCCESS.toString(),billList,0,billList.size());
	}

}
