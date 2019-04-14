package cn.reimbursement.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.ProcessStatusDao;
import cn.reimbursement.service.ProcessStatusService;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

@Service
public class ProcessStatusServiceImpl implements ProcessStatusService {

	@Autowired
	private ProcessStatusDao processStatusDao;

	public List<Map<String, String>> selectNameStateOptionById(String billId) {
		return processStatusDao.selectNameStateOptionById(billId);
	}

}
