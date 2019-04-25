package cn.reimbursement.vo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import cn.reimbursement.pojo.Bill;
import lombok.Data;

/**
 * @author linweijie
 * @date 2019年4月23日
 */
@Component
@Data
public class BillReimbursementStatusVo {
	private String billId;
	private String billAttribute;
	private String billCompany;
	private String billType;
	private String billReimbursementDep;
	private String billSubject;
	private String billBelongCompany;
	private String billReimbursementPerson;
	private String billSummary;
	private BigDecimal billAmount;
	private String billContractStatusName;
	private String billInvoiceStatusName;
	private BigDecimal billInvoiceAmount;
	private Integer billReimbursementPersonConfirm;
	private String billChargePerson;
	private String billRegistrantPerson;
	private Date billRegistrantDate;
	private Date billProduceDate;
	private Integer billIsEnd;
	private Date billEndDate;
	private Integer reimbursementStatus;

	public BillReimbursementStatusVo() {
		super();
	}

	public BillReimbursementStatusVo(String billId, String billAttribute, String billCompany, String billType, String billReimbursementDep,
			String billSubject, String billBelongCompany, String billReimbursementPerson, String billSummary,
			BigDecimal billAmount, String billContractStatusName, String billInvoiceStatusName,
			BigDecimal billInvoiceAmount, Integer billReimbursementPersonConfirm, String billChargePerson,
			String billRegistrantPerson, Date billRegistrantDate, Date billProduceDate, Integer billIsEnd,
			Date billEndDate, Integer reimbursementStatus) {
		super();
		this.billId = billId;
		this.billAttribute = billAttribute;
		this.billCompany = billCompany;
		this.billType = billType;
		this.billReimbursementDep = billReimbursementDep;
		this.billSubject = billSubject;
		this.billBelongCompany = billBelongCompany;
		this.billReimbursementPerson = billReimbursementPerson;
		this.billSummary = billSummary;
		this.billAmount = billAmount;
		this.billContractStatusName = billContractStatusName;
		this.billInvoiceStatusName = billInvoiceStatusName;
		this.billInvoiceAmount = billInvoiceAmount;
		this.billReimbursementPersonConfirm = billReimbursementPersonConfirm;
		this.billChargePerson = billChargePerson;
		this.billRegistrantPerson = billRegistrantPerson;
		this.billRegistrantDate = billRegistrantDate;
		this.billProduceDate = billProduceDate;
		this.billIsEnd = billIsEnd;
		this.billEndDate = billEndDate;
		this.reimbursementStatus = reimbursementStatus;
	}

	public static BillReimbursementStatusVo toBillVo(Bill bill, int reimbursementStatus) {
		return new BillReimbursementStatusVo(bill.getBillId(), bill.getBillAttribute(), bill.getBillCompany(), bill.getBillType(),
				bill.getBillReimbursementDep(), bill.getBillSubject(), bill.getBillBelongCompany(),
				bill.getBillReimbursementPerson(), bill.getBillSummary(), bill.getBillAmount(),
				bill.getBillContractStatusName(), bill.getBillInvoiceStatusName(), bill.getBillInvoiceAmount(),
				bill.getBillReimbursementPersonConfirm(), bill.getBillChargePerson(), bill.getBillRegistrantPerson(),
				bill.getBillRegistrantDate(), bill.getBillProduceDate(), bill.getBillIsEnd(), bill.getBillEndDate(),
				reimbursementStatus);
	}
	public static List<BillReimbursementStatusVo> toBillVoList(List<Bill> billList){
		List<BillReimbursementStatusVo> billVoList = Lists.newArrayList();
		for (Bill bill : billList) {
			if (bill.getBillIsEnd() == 0) {
				billVoList.add(BillReimbursementStatusVo.toBillVo(bill, 0));
				continue;
			}
			if (bill.getBillIsEnd() == 1 && bill.getBillReimbursementPersonConfirm() == 0) {
				billVoList.add(BillReimbursementStatusVo.toBillVo(bill, 1));
				continue;
			}
			if (bill.getBillIsEnd() == 1 && bill.getBillReimbursementPersonConfirm() == 1) {
				billVoList.add(BillReimbursementStatusVo.toBillVo(bill, 2));
			}
		}
		return billVoList;
	}
}
