package cn.reimbursement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessStatusDao {
	void insertProcessStatus(@Param("processStatusBillId") String processStatusBillId,
			@Param("processStatusName") String processStatusName,
			@Param("processStatusState") String processStatusState, @Param("processStatusStep") int processStatusStep,
			@Param("processStatusBCompany") String processStatusCompany);

	List<String> selectProcessStatusBillIds(@Param("processStatusCompany") String processStatusCompany,
			@Param("processStatusProcessName") String processStatusProcessName,
			@Param("processStatusState") String processStatusState, @Param("staffName") String staffName);

	Integer selectCountByBillId(@Param("billId") String billId);

	Integer updateStateByStep(@Param("billId") String billId, @Param("step") Integer step, @Param("audit") String audit,
			@Param("StaffName") String StaffName, @Param("auditSummary") String auditSummary);

	Integer selectProcessStatusByBillIdProcessName(@Param("billId") String billId,
			@Param("processStatusCompany") String processStatusCompany,
			@Param("processStatusProcessName") String processStatusProcessName);

	List<Map<String, String>> selectNameStateOptionById(@Param("billId") String billId);
	
	Integer selectRejectByBillIdAndStep(@Param("billId") String billId,@Param("step")Integer step);
	
}
