package cn.reimbursement.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessStatusDao {
	void insertProcessStatus(@Param("processStatusBillId") int processStatusBillId,
			@Param("processStatusName") String processStatusName,
			@Param("processStatusState") String processStatusState,
			@Param("processStatusStep") int processStatusStep,
			@Param("processStatusBCompany") String processStatusCompany);
}
