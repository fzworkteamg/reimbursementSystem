package cn.reimbursement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.CompanyDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.util.ServerResult;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyDao companyDao;
	
	public ServerResult<List<String>> selectCompany() {
		List<String> companyList=companyDao.selectCompany();
		return new ServerResult<List<String>>(0,InfoEnum.SUCCESS.toString(),companyList);
	}

}
