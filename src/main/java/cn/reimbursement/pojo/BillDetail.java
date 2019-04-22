package cn.reimbursement.pojo;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author linweijie
 * @date 2019年4月22日
 */

@Data
@Component
public class BillDetail {
	private String id;
	private String name;
	private BigDecimal amounts;
	private Date occurDate;
}
