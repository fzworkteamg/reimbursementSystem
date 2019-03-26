package cn.reimbursement.service;

import java.util.List;

import cn.reimbursement.util.ServerResult;

public interface DepService {
	ServerResult<List<String>> selectDepByCompany(String companyName);
}
