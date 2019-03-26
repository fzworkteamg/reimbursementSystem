package cn.reimbursement.service;

import java.util.List;
import java.util.Map;

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
	
	//test
	public ServerResult insertProcess(Map<String, String> processMap) {
		processDao.insertProcess(processMap);
		return null;
	}
	
	//test
	public ServerResult selectProcess(Map<String, String> processMap) {
		List<Process> procssList=processDao.selectProcess(processMap);
		for (Process process : procssList) {
			System.out.println(process);
		}
		return null;
	}

}
