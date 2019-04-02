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
	
	public LayuiResult<List<Map<String, String>>> selectDepContentCommentByCompanyDep(HttpServletRequest request,String page,String limit,String company,String dep) {
//		int pageNum = (page == null ? 1 : Integer.valueOf(page));
//		int limitSize = (limit == null ? 10 : Integer.valueOf(limit));
//        PageHelper.startPage(pageNum, limitSize);
		List<Map<String, String>> subjectOMapList=reiSubjectDao.selectDepContentCommentByCompanyDep(company, dep);
//		PageInfo pageInfo = new PageInfo(subjectOMapList);
		return new LayuiResult(InfoEnum.SUCCESS.toString(),subjectOMapList,0,subjectOMapList.size());
	}

}
