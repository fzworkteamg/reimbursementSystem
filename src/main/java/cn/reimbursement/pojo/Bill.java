package cn.reimbursement.pojo;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Bill {
	private String billId;//
	private String billAttribute;
	private String billCompany;//
	private String billType;//
	private String billReimbursementDep;//
	private String billSubject;//
	private String billBelongCompany;//
	private String billReimbursementPerson;//
	private String billSummary;//
	private BigDecimal billAmount;//
	private String billContractStatusName;//
	private String billInvoiceStatusName;//
	private BigDecimal billInvoiceAmount;//
	private Integer billReimbursementPersonConfirm;
	private String billChargePerson;//
	private String billRegistrantPerson;//
	private Date billRegistrantDate;//
	private Date billProduceDate;//
	private Integer billIsEnd;
	private Date billEndDate;

}
