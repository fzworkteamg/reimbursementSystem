package cn.reimbursement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.ProcessStatusDao;

@Service
public class ProcessStatusServiceImpl implements ProcessStatusService {
	
	@Autowired
	private ProcessStatusDao processStatusDao;
	
	public List<Map<String, String>> selectNameStateOptionById(String billId) {
		return processStatusDao.selectNameStateOptionById(billId);
	}

}
