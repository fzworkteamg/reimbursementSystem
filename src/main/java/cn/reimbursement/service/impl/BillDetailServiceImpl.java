package cn.reimbursement.service.impl;

import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.BillDetailDao;
import cn.reimbursement.dao.BillRelationDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.pojo.BillDetail;
import cn.reimbursement.service.BillDetailService;
import cn.reimbursement.util.LayuiResult;

/**
 * @author linweijie
 * @date 2019年4月22日
 */

@Service
public class BillDetailServiceImpl implements BillDetailService {

	@Autowired
	private BillDetailDao billDetailDao;
	@Autowired
	private BillRelationDao billRelationDao;

	@Override
	public LayuiResult<List<BillDetail>> selectBillDetailByBillId(String billId, int limit, int page) {
		int start = limit * (page - 1);
		List<String> billDetailIdList = billRelationDao.selectBillDetaiIdByBillId(billId, limit, start);
		List<BillDetail> billDetailList = Lists.newArrayList();
		for (String billDetailId : billDetailIdList) {
			billDetailList.add(billDetailDao.selectBillDetailById(billDetailId));
		}
		return new LayuiResult<List<BillDetail>>(InfoEnum.SUCCESS.getValue(), billDetailList, 0,
				billRelationDao.selectBillDetaiIdByBillIdCount(billId));
	}

}
