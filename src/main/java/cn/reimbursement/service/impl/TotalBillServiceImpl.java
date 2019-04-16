package cn.reimbursement.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import cn.reimbursement.dao.TotalBillDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.pojo.Bill;
import cn.reimbursement.service.TotalBillService;
import cn.reimbursement.util.LayuiResult;

/**
 * @author linweijie
 * @date 2019年4月16日
 */
public class TotalBillServiceImpl implements TotalBillService {

	@Autowired
	private TotalBillDao totalBillDao;

	@Override
	public LayuiResult<List<Bill>> selectBillByTotalBillId(HttpServletRequest request) {
		int limit = Integer.parseInt(request.getParameter("limit"));
		int page = Integer.parseInt(request.getParameter("page"));
		int start = limit * (page - 1);
		String totalBillId = request.getParameter("totalBillId");
		return new LayuiResult<List<Bill>>(InfoEnum.SUCCESS.getValue(),
				totalBillDao.selectBillByTotalBillId(totalBillId,limit,start), 0,
				totalBillDao.selectBillCountByTotalBillId(totalBillId));
	}

}
