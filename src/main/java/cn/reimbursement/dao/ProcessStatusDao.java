package cn.reimbursement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessStatusDao {
	void insertProcessStatus(@Param("processStatusBillId") String processStatusBillId,
			@Param("processStatusName") String processStatusName,
			@Param("processStatusState") String processStatusState, @Param("processStatusStep") int processStatusStep,
			@Param("processStatusBCompany") String processStatusCompany);

	List<String> selectProcessStatusBillIds(@Param("processStatusCompany") String processStatusCompany,
			@Param("processStatusProcessName") String processStatusProcessName);

	Integer selectCountByBillId(@Param("billId") String billId);

	Integer updateStateByStep(@Param("billId") String billId, @Param("step") Integer step,
			@Param("audit") String audit,@Param("StaffName")String StaffName, @Param("auditSummary")String auditSummary);

	Integer selectProcessStatusByBillIdProcessName(@Param("billId") String billId,
			@Param("processStatusCompany") String processStatusCompany,
			@Param("processStatusProcessName") String processStatusProcessName);
}
