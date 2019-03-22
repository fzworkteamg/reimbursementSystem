package cn.reimbursement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.reimbursement.pojo.Process;
import cn.reimbursement.dao.ProcessDao;

@Service
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	private ProcessDao processDao;

	public Process selectProcessById(Integer processId) {
		return processDao.selectProcessById(processId);
	}

}
