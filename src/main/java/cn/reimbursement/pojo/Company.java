package cn.reimbursement.pojo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Company {
	private Integer companyId;
	private String companyName;
}
