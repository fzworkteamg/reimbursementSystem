package cn.reimbursement.service;

import java.util.List;
import java.util.Map;

public interface ProcessStatusService {
	List<Map<String,String>> selectNameStateOptionById(String billId);
}
