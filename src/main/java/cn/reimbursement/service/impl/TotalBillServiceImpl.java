package cn.reimbursement.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.TotalBillDao;
import cn.reimbursement.dao.TotalBillDetailDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.pojo.Bill;
import cn.reimbursement.service.TotalBillService;
import cn.reimbursement.util.LayuiResult;
import cn.reimbursement.util.ServerResult;

/**
 * @author linweijie
 * @date 2019年4月16日
 */
@Service
public class TotalBillServiceImpl implements TotalBillService {

	@Autowired
	private TotalBillDao totalBillDao;
	@Autowired
	private TotalBillDetailDao totalBillDetailDao;

	@Override
	public LayuiResult<List<Bill>> selectBillByTotalBillId(HttpServletRequest request) {
		int limit = Integer.parseInt(request.getParameter("limit"));
		int page = Integer.parseInt(request.getParameter("page"));
		int start = limit * (page - 1);
		String totalBillId = request.getParameter("totalBillId");
		List<Bill> l = totalBillDao.selectBillByTotalBillId(totalBillId, limit, start);
		return new LayuiResult<List<Bill>>(InfoEnum.SUCCESS.getValue(), l, 0,
				totalBillDao.selectBillCountByTotalBillId(totalBillId));
	}

	@Override
	public ServerResult<String> deleteTotalBillDetailByTotalBillId(HttpServletRequest request) {
		String totalBillId = (String) request.getSession().getAttribute("totalBillId");
		totalBillDetailDao.deleteTotalBillDetailByTotalBillId(totalBillId);
		return new ServerResult<String>(0, InfoEnum.SUCCESS.getValue());
	}

}
