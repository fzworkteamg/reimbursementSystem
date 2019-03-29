package cn.reimbursement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.reimbursement.dao.BillDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.pojo.Bill;
import cn.reimbursement.pojo.Staff;
import cn.reimbursement.util.LayuiResult;
import cn.reimbursement.util.ServerResult;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillDao billDao;

	public ServerResult<List<Bill>> selctBillByCompany(HttpServletRequest request) throws Exception {
		Staff staff = (Staff) request.getSession().getAttribute("staff");
		String companyName = staff.getCompanyName();
		List<Bill> billList = billDao.selectBillByCompany(companyName);
		return new ServerResult<List<Bill>>(0, InfoEnum.SUCCESS.toString(), billList);
	}

	public ServerResult insertBill(Map<String, Object> billMap) {
		String billId = (String) billMap.get("bill_id");
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

	public LayuiResult<List<Bill>> selectBill(HttpServletRequest httpServletRequest) {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
		Map<String, String> billMap = new HashMap<String, String>();
		if (request.getParameter("company").endsWith("--"))
			billMap.put("billCompany", "");
		else
			billMap.put("billCompany", request.getParameter("company"));

		if (request.getParameter("dep").endsWith("--"))
			billMap.put("billDep", "");
		else
			billMap.put("billDep", request.getParameter("dep"));

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
		List<Bill> billList=billDao.selectBill(billMap);
		return new LayuiResult<List<Bill>>(InfoEnum.SUCCESS.toString(),billList,0,billList.size());
	}

}
