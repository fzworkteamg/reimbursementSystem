package cn.reimbursement.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentStepDao {
	Integer insertCurrentStep(@Param("currentStepBillId")String currentStepBillId);
	Integer selectCurrentStepByBillId(@Param("billId")String billId);
	Integer updateCurrentStepNumberByBillId(@Param("billId")String billId,@Param("currentStepNumber")Integer currentStepNumber);
	Integer deleteCurrentStepByBillId(@Param("billId")String billId);
}
