package cn.reimbursement.vo;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author linweijie
 * @date 2019年4月23日
 */
@Component
@Data
public class BillVo {
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

	public BillVo() {
		super();
	}

	public BillVo(String billId, String billAttribute, String billCompany, String billType, String billReimbursementDep,
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

}
