package cn.reimbursement.pojo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProcessStatus {
	private Integer processStatusId;
	private Integer processStatusBillId;
	private String processStatusProcessName;
	private String processStatusState;
	private String processStatusOpinion;
	private Integer processStatusStep;
	private String processStatusCompany;
	private Date processStatusLastUpload;

}
