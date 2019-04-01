package cn.reimbursement.pojo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Process {
	private Integer processId;
	private String processCompany;
	private String processDepartment;
	private String processContent;
	private Integer processCount;
	private String processAuditor;
}
