package cn.reimbursement.pojo;

import java.sql.Date;

import org.springframework.stereotype.Component;

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
	private Integer billAmount;
	private String billInvoiceStatusName;
	private Integer billInvoiceAmount;
	private String billReimbursementPersonConfirm;
	private String billChargePerson;
	private String billRegistrantPerson;
	private Date billRegistrantDate;
	private Integer billIsEnd;
	private Date billEndDate;

}
