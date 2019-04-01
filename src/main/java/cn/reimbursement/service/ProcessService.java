package cn.reimbursement.service;
import cn.reimbursement.pojo.Process;
import cn.reimbursement.util.ServerResult;

public interface ProcessService {
	public ServerResult<Process> selectProcessById(Integer processId);
}
