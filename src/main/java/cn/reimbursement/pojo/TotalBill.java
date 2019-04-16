package cn.reimbursement.pojo;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

/**
 * @author linweijie
 * @date 2019年4月16日
 */
@Data
public class TotalBill {
	private String totalBillId;
	private String totalBillName;
	private int companyName;
	private BigDecimal totalBillMoney;
	private Date totalBillregistDate;
	private String totalBillregistPerson;
	private String totalBillremark;
}
