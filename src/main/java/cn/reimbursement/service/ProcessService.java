package cn.reimbursement.service;
import cn.reimbursement.util.ServerResult;

public interface ProcessService {
	public ServerResult<String> selectProcessContentByBillId(String billId);
}
