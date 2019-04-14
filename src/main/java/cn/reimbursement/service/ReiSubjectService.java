package cn.reimbursement.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.reimbursement.util.LayuiResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

public interface ReiSubjectService {
	/*
	 * @Description: 通过公司部门查询部门名、科目内容、科目备注
	 * 
	 * @param request
	 * 
	 * @param company
	 * 
	 * @param dep
	 * 
	 * @return
	 */
	LayuiResult<List<Map<String, String>>> selectDepContentCommentByCompanyDep(HttpServletRequest request,
			String company, String dep);
}
