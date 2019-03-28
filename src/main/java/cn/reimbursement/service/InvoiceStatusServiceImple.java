package cn.reimbursement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.InvoiceStatusDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.util.ServerResult;

@Service
public class InvoiceStatusServiceImple implements InvoiceStatusService{
	
	@Autowired
	private InvoiceStatusDao invoiceStatusDao;
	
	public ServerResult<List<String>> selectInvoiceStatusName() {
		return new ServerResult<List<String>>(0,InfoEnum.SUCCESS.toString(),invoiceStatusDao.selectInvoiceStatusName());
	}
	
}
