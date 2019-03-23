package cn.reimbursement.pojo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ContractStatus {
	private Integer contractStatusId;
	private String contractStatusName;
}
