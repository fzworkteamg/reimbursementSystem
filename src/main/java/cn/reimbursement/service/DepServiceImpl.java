package cn.reimbursement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.DepDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.util.ServerResult;

@Service
public class DepServiceImpl implements DepService {
	
	@Autowired
	private DepDao depDao;
	
	public ServerResult<List<String>> selectDepByCompany(String companyName) {
		return new ServerResult<List<String>>(0,InfoEnum.SUCCESS.toString(),depDao.selectDepByCompany(companyName));
	}

}
