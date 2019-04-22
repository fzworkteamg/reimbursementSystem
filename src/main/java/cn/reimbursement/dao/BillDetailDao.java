package cn.reimbursement.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.reimbursement.pojo.BillDetail;

@Repository
public interface BillDetailDao {
	int insertBillDetailDao(@Param("id") String id, @Param("name") String name, @Param("amounts") BigDecimal amounts,
			@Param("occurDate") String occurDate);

	BillDetail selectBillDetailById(@Param("id") String id);
}
