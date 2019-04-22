package cn.reimbursement.pojo;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
* @author linweijie
* @date 2019年4月22日
*/

@Data
@Component
public class BillRelation {
	private String billId;
	private String billDetailId;
}
