package cn.reimbursement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.ReiSubjectDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.util.ServerResult;

@Service
public class ReiSubjectServiceImpl implements ReiSubjectService {
	
	@Autowired
	private ReiSubjectDao reiSubjectDao;
	
	public List<Map<String, String>> selectDepContentCommentByCompanyDep(String company,String dep) {
		return reiSubjectDao.selectDepContentCommentByCompanyDep(company, dep);
	}

}
