package cn.reimbursement.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.reimbursement.dao.ReiSubjectDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.util.LayuiResult;

@Service
public class ReiSubjectServiceImpl implements ReiSubjectService {
	
	@Autowired
	private ReiSubjectDao reiSubjectDao;
	
	public LayuiResult<List<Map<String, String>>> selectDepContentCommentByCompanyDep(HttpServletRequest request,String company,String dep) {
		int limit = Integer.parseInt(request.getParameter("limit"));
		int page = Integer.parseInt(request.getParameter("page"));
		int start=limit*(page-1);
		List<Map<String, String>> subjectOMapList=reiSubjectDao.selectDepContentCommentByCompanyDep(company, dep,limit,start);
		return new LayuiResult(InfoEnum.SUCCESS.getValue(),subjectOMapList,0,reiSubjectDao.selectDepContentCommentByCompanyDepCount(company, dep));
	}

}
