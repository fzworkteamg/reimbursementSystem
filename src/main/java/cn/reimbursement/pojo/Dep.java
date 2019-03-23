package cn.reimbursement.pojo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Dep {
	private Integer depId;
	private String depSeq;
	private String depName;
	private String companyName;
}
