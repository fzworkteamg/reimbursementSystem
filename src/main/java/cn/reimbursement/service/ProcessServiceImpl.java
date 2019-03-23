package cn.reimbursement.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.ProcessDao;
import cn.reimbursement.enums.ProcessEnum;
import cn.reimbursement.pojo.Process;
import cn.reimbursement.util.ServerResult;

@Service
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	private ProcessDao processDao;

	public ServerResult<Process> selectProcessById(Integer processId) {
		System.out.println("zero");
//		int[] arr=new int[] {0};
//		System.out.println(arr[1]);
		Process process=processDao.selectProcessById(processId);
		if(process!=null) {
			return new ServerResult<Process>(0,ProcessEnum.SUCCESS.getName(),process);
		}
		return new ServerResult<Process>(1,ProcessEnum.FAIL.toString());
	}

	public ServerResult insertProcess(Map<String, String> processMap) {
		processDao.insertProcess(processMap);
		return null;
	}

}
