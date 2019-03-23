package cn.reimbursement.pojo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Duty {
	private Integer dutyId;
	private String dutySeq;
	private String dutyName;
	private String depName;
	private String companyName;
}
