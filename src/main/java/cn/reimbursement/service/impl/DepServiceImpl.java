package cn.reimbursement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.DepDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.service.DepService;
import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

@Service
public class DepServiceImpl implements DepService {

	@Autowired
	private DepDao depDao;

	public ServerResult<List<String>> selectDepByCompany(String companyName) {
		return new ServerResult<List<String>>(0, InfoEnum.SUCCESS.getValue(), depDao.selectDepByCompany(companyName));
	}

}
