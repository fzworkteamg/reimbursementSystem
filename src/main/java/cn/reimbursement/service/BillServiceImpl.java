package cn.reimbursement.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.BillDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.pojo.Bill;
import cn.reimbursement.pojo.Staff;
import cn.reimbursement.util.ServerResult;

@Service
public class BillServiceImpl implements BillService {
	
	@Autowired
	private BillDao billDao;
	
	public ServerResult<List<Bill>> selctBillByCompany(HttpServletRequest request) throws Exception{
		Staff staff=(Staff) request.getSession().getAttribute("staff");
		String companyName=staff.getCompanyName();
		List<Bill> billList=billDao.selectBillByCompany(companyName);
		return new ServerResult<List<Bill>>(0,InfoEnum.SUCCESS.toString(),billList);
	}

	public ServerResult insertBill(Map<String, Object> billMap) {
		String billId=(String)billMap.get("bill_id");
		int count=billDao.selectBillById(billId);
		if(count>0) {
			return new ServerResult(1,InfoEnum.FAIL.toString());
		}
		billDao.insertBill(billMap);
		count=billDao.selectBillById(billId);
		if(count>0) {
			return new ServerResult(0,InfoEnum.SUCCESS.toString());
		}
		return new ServerResult(1,InfoEnum.FAIL.toString());
	}

}
