package cn.reimbursement.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.reimbursement.util.LayuiResult;

public interface ReiSubjectService {
	LayuiResult<List<Map<String, String>>> selectDepContentCommentByCompanyDep(HttpServletRequest request,String page,String limit,String company,String dep);
}
