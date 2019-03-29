package cn.reimbursement.service;

import java.util.List;
import java.util.Map;

import cn.reimbursement.util.ServerResult;

public interface ReiSubjectService {
	List<Map<String,String>> selectDepContentCommentByCompanyDep(String company,String dep);
}
