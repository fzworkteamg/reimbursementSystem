package cn.reimbursement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.ContractStatusDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.service.ContractStatusService;
import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

@Service
public class ContractStatusServiceImple implements ContractStatusService {

	@Autowired
	private ContractStatusDao contractStatusDao;

	public ServerResult<List<String>> selectContractStatusName() {
		return new ServerResult<List<String>>(0, InfoEnum.SUCCESS.getValue(),
				contractStatusDao.selectContractStatusName());
	}

}
