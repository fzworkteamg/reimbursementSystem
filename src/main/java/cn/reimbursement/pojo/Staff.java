package cn.reimbursement.pojo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Staff {
	private String staffId;
	private String staffPassword;
	private String staffName;
	private Integer staffSex;
	private String staffTel;
	private String staffEmail;
	private String companyName;
	private String depName;
	private String dutyName;
	private String temDutyName;
	private int isLogin;
}
