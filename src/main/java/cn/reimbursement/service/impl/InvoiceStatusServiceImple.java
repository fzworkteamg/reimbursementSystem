package cn.reimbursement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.InvoiceStatusDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.service.InvoiceStatusService;
import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

@Service
public class InvoiceStatusServiceImple implements InvoiceStatusService {

	@Autowired
	private InvoiceStatusDao invoiceStatusDao;

	public ServerResult<List<String>> selectInvoiceStatusName() {
		return new ServerResult<List<String>>(0, InfoEnum.SUCCESS.getValue(),
				invoiceStatusDao.selectInvoiceStatusName());
	}

}
