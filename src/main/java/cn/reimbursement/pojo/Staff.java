package cn.reimbursement.pojo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Staff {
	private String staffId;
	private String staffName;
	private Integer staffSex;
	private Integer staffAge;
	private String staffTel;
	private String staffEmail;
	private String staffAddress;
	private String companyName;
	private String depName;
	private String dutyName;
	private String temDutyName;
}
