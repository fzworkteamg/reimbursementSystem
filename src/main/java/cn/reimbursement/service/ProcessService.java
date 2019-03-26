package cn.reimbursement.service;
import java.util.Map;

import cn.reimbursement.pojo.Process;
import cn.reimbursement.util.ServerResult;

public interface ProcessService {
	public ServerResult<Process> selectProcessById(Integer processId);
	public ServerResult insertProcess(Map<String,String> processMap);
	public ServerResult selectProcess(Map<String, String> processMap);
}
