package cn.reimbursement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.BillDao;
import cn.reimbursement.dao.CurrentStepDao;
import cn.reimbursement.dao.ProcessDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.pojo.Bill;
import cn.reimbursement.service.ProcessService;
import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

@Service
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	private ProcessDao processDao;

	@Autowired
	private BillDao billDao;

	@Autowired
	private CurrentStepDao currentStepDao;

	public ServerResult<String> selectProcessContentByBillId(String billId) {
		Bill bill = billDao.selectBillById(billId);
		if (bill == null)
			return new ServerResult<String>(1, InfoEnum.FAIL.toString());
		String processContent = processDao.selectProcessByCompanyAndDepartment(bill.getBillCompany(),
				bill.getBillReimbursementDep());
		int currentStep = currentStepDao.selectCurrentStepByBillId(billId) - 1;
		String resultString = "审核流程：" + processContent + "		待审核：" + processContent.split("\\|")[currentStep];
		return new ServerResult<String>(0, InfoEnum.SUCCESS.getValue(), resultString);
	}

}
