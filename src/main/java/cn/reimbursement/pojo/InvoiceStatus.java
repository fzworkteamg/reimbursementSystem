package cn.reimbursement.pojo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class InvoiceStatus {
	private Integer invoiceStatusId;
	private String invoiceStatusName;
}
