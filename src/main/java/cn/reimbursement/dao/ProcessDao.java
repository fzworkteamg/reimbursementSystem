package cn.reimbursement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.reimbursement.pojo.Process;

public interface ProcessDao {
	Process selectProcessById(@Param("processId")Integer processId);
	void insertProcess(@Param("processMap")Map<String,String> processMap);
	List<Process> selectProcess(@Param("processMap")Map<String, String> processMap);
	String selectProcessByCompanyAndDepartment(@Param("processCompany")String processCompany,@Param("processDepartment")String processDepartment);
}
