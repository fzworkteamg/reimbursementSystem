package cn.reimbursement.service;

import java.util.List;
import java.util.Map;

import cn.reimbursement.util.LayuiResult;

public interface ReiSubjectService {
	LayuiResult<List<Map<String, String>>> selectDepContentCommentByCompanyDep(String company,String dep);
}
