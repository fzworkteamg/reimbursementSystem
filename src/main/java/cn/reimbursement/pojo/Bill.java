package cn.reimbursement.pojo;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.stereotype.Component;

import cn.reimbursement.vo.BillVo;
import lombok.Data;

@Data
@Component
public class Bill {
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

	public static BillVo toBillVo(Bill bill, int reimbursementStatus) {
		return new BillVo(bill.getBillId(), bill.getBillAttribute(), bill.getBillCompany(), bill.getBillType(),
				bill.getBillReimbursementDep(), bill.getBillSubject(), bill.getBillBelongCompany(),
				bill.getBillReimbursementPerson(), bill.getBillSummary(), bill.getBillAmount(),
				bill.getBillContractStatusName(), bill.getBillInvoiceStatusName(), bill.getBillInvoiceAmount(),
				bill.getBillReimbursementPersonConfirm(), bill.getBillChargePerson(), bill.getBillRegistrantPerson(),
				bill.getBillRegistrantDate(), bill.getBillProduceDate(), bill.getBillIsEnd(), bill.getBillEndDate(),
				reimbursementStatus);
	}

}
