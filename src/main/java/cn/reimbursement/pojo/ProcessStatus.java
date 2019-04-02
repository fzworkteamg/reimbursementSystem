package cn.reimbursement.pojo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProcessStatus {
	private Integer processStatusId;
	private String processStatusBillId;
	private String processStatusProcessName;
	private String processStatusState;
	private String processStatusOpinion;
	private Integer processStatusStep;
	private String processStatusCompany;
	private String processStatusAuditor;
	private Date processStatusLastUpload;

}
