package cn.reimbursement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.ContractStatusDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.util.ServerResult;

@Service
public class ContractStatusServiceImple implements ContractStatusService{
	
	@Autowired
	private ContractStatusDao contractStatusDao;
	
	public ServerResult<List<String>> selectContractStatusName() {
		return new ServerResult<List<String>>(0,InfoEnum.SUCCESS.toString(),contractStatusDao.selectContractStatusName());
	}
	
}
