package cn.reimbursement.dao;

import org.apache.ibatis.annotations.Param;

public interface ProcessDao {
	String selectProcessByCompanyAndDepartment(@Param("processCompany")String processCompany,@Param("processDepartment")String processDepartment);
}
