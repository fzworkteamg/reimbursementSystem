package cn.reimbursement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.ProcessDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.pojo.Process;
import cn.reimbursement.util.ServerResult;

@Service
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	private ProcessDao processDao;

	public ServerResult<Process> selectProcessById(Integer processId) {
		System.out.println("zero");
		Process process=processDao.selectProcessById(processId);
		if(process!=null) {
			return new ServerResult<Process>(0,InfoEnum.SUCCESS.getName(),process);
		}
		return new ServerResult<Process>(1,InfoEnum.FAIL.toString());
	}
}
