package cn.reimbursement.service;
import cn.reimbursement.pojo.Process;

public interface ProcessService {
	Process selectProcessById(Integer processId);
}
