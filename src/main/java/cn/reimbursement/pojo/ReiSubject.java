package cn.reimbursement.pojo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ReiSubject {
	private Integer reiSubjectId;
	private String reiSubjectCompanyName;
	private String reiSubjectDepName;
	private String reiSubjectContent;
	private String reiSubjectComment;

}
