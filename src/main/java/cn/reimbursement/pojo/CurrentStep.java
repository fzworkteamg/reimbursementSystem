package cn.reimbursement.pojo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CurrentStep {
	private String currentStepBillId;
	private Integer currentStepBillnumber;

}
