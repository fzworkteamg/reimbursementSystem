package cn.reimbursement.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.reimbursement.util.LayuiUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.pojo.Bill;
import cn.reimbursement.service.BillService;
import cn.reimbursement.util.LayuiResult;
import cn.reimbursement.util.ServerResult;
import cn.reimbursement.vo.BillReimbursementStatusVo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author linweijie
 * @date 2019年4月14日
 */
@RestController
@RequestMapping("/bill")
public class BillController {

	@Autowired
	private BillService billService;

	/*
	 * @Description: 查询登记人所属公司的所有账单
	 * 
	 * @param request
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	@PostMapping("/selctBillByCompany")
	public ServerResult<List<Bill>> selctBillByCompany(HttpServletRequest request) throws Exception {
		return billService.selctBillByCompany(request);
	}

	/*
	 * @Description: 增加账单
	 * 
	 * @param request
	 * 
	 * @return
	 */
	@PostMapping("/insertBill")
	public ServerResult<String> insertBill(HttpServletRequest request) {
		return billService.insertBill(request);
	}

	/*
	 * @Description: 通过条件查询账单
	 * 
	 * @param request
	 * 
	 * @return
	 */
	@PostMapping("/selectBill")
	public LayuiResult<List<Bill>> selectBill(HttpServletRequest request) {
		return billService.selectBill(request);
	}

	/*
	 * @Description: 查询本月的账单
	 * 
	 * @param request
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	@PostMapping("/selectBillByMonth")
	public LayuiResult<List<Bill>> selectBillByMonth(HttpServletRequest request) throws ParseException {
		return billService.selectBillByMonth(request);
	}

	/*
	 * @Description: 查询待审核的账单
	 * 
	 * @param request
	 * 
	 * @return
	 */
	@PostMapping("/selectBillWaitAudit")
	public LayuiResult<List<Bill>> selectBillWaitAudit(HttpServletRequest request) {
		return billService.selectBillByAudit(request, InfoEnum.WAIT_AUDIT.getValue());
	}

	/*
	 * @Description: 查询已审核的账单
	 * 
	 * @param request
	 * 
	 * @return
	 */
	@PostMapping("/selectBillAudited")
	public LayuiResult<List<Bill>> selectBillAudited(HttpServletRequest request) {
		return billService.selectBillByAudit(request, InfoEnum.AUDITED.getValue());
	}

	/*
	 * @Description: 查询被驳回到登记人的账单
	 * 
	 * @param request
	 * 
	 * @return
	 */
	@PostMapping("/selectBillWriteAndPast")
	public LayuiResult<List<Bill>> selectBillWriteAndPast(HttpServletRequest request) {
		return billService.selectBillByAudit(request, InfoEnum.REJECT.getValue());
	}

	/*
	 * @Description: 审核账单
	 * 
	 * @param request
	 * 
	 * @param billId
	 * 
	 * @param auditSummary
	 * 
	 * @param contractStatus
	 * 
	 * @param invoiceStatus
	 * 
	 * @return
	 */
	@PostMapping("/auditBill")
	public ServerResult<String> auditBill(HttpServletRequest request, @RequestParam("billId") String billId,
			@RequestParam("audit_summary") String auditSummary,
			@RequestParam("selectContractStatus") String contractStatus,
			@RequestParam("selectInvoiceStatus") String invoiceStatus) {
		return billService.auditBill(request, billId, auditSummary, contractStatus, invoiceStatus);
	}

	/*
	 * @Description: 驳回账单
	 * 
	 * @param request
	 * 
	 * @param billId
	 * 
	 * @param opinion
	 * 
	 * @return
	 */
	@PostMapping("/rejectBill")
	public ServerResult<String> rejectBill(HttpServletRequest request, @RequestParam("billId") String billId,
			@RequestParam("audit_summary") String opinion) {
		return billService.rejectBill(request, billId, opinion);
	}

	/*
	 * @Description: 登记人重新发起账单
	 * 
	 * @param request
	 * 
	 * @return
	 */
	@PostMapping("/updateBill")
	public ServerResult<String> updateBill(HttpServletRequest request) {
		return billService.updateBill(request);
	}

	/*
	 * @Description: 撤销(删除)账单
	 * 
	 * @param billId
	 * 
	 * @return
	 */
	@PostMapping("/delBill")
	public ServerResult<String> deleteBill(String billId) {
		return billService.deleteBill(billId);
	}

	/*
	 * @Description: 显示报销列表
	 * 
	 * @param request
	 * 
	 * @return
	 */
	@PostMapping("/reimbursedBill")
	LayuiResult<List<BillReimbursementStatusVo>> selectBillByReimbursementStatus(HttpServletRequest request) {
		return billService.selectBillByReimbursementStatus(request);
	}
	
	/*
	 * @Description: 更新账单确认到账
	 * @param billId
	 * @return
	 */
	@PostMapping("/updateBillReimbursementComfirmByBillId")
	public ServerResult<String> updateBillReimbursementComfirmByBillId(@RequestParam("billId")String billId){
		return billService.updateBillReimbursementComfirmByBillId(billId);
	}

	/*
	 * @Description: 批量上传附件
	 * @param request
	 * @return
	 */
	@RequestMapping("/addBillAppendix")
	public LayuiUploadResult<String> addBillAppendix(@RequestParam(value = "file", required = false) MultipartFile[] files,
													 MultipartHttpServletRequest request){
		return billService.addBillAppendix(request,files);
	}
}
