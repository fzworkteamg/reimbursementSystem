package cn.reimbursement.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import cn.reimbursement.pojo.Process;
import cn.reimbursement.util.ServerResult;

public interface ProcessDao {
	Process selectProcessById(@Param("processId")Integer processId);
	public ServerResult insertProcess(@Param("processMap")Map<String,String> processMap);
}
