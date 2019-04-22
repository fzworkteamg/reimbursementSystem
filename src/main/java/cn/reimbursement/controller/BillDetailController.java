package cn.reimbursement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.pojo.BillDetail;
import cn.reimbursement.service.BillDetailService;
import cn.reimbursement.util.LayuiResult;

/**
 * @author linweijie
 * @date 2019年4月22日
 */
@RestController
@RequestMapping("/billDetail")
public class BillDetailController {

	@Autowired
	private BillDetailService billDetailService;

	@RequestMapping("/selectBillDetailByBillId")
	public LayuiResult<List<BillDetail>> selectBillDetailByBillId(@RequestParam("billId") String billId,
			@RequestParam("limit") int limit, @RequestParam("page") int page) {
		return billDetailService.selectBillDetailByBillId(billId, limit, page);
	}

}
