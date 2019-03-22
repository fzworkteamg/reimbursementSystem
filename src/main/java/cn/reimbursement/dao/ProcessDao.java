package cn.reimbursement.dao;

import org.apache.ibatis.annotations.Param;
import cn.reimbursement.pojo.Process;

public interface ProcessDao {
	Process selectProcessById(@Param("processId")Integer processId);
}
