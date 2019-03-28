package cn.reimbursement.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ContractStatusDao {
	List<String> selectContractStatusName();
}
