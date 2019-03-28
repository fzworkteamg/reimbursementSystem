package cn.reimbursement.service;

import java.util.List;

import cn.reimbursement.util.ServerResult;

public interface ContractStatusService {
	ServerResult<List<String>> selectContractStatusName();
}
