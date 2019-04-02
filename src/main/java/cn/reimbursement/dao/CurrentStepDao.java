package cn.reimbursement.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentStepDao {
	void insertCurrentStep(@Param("currentStepBillId")int currentStepBillId);
}
