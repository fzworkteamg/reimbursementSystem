package cn.reimbursement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRelationDao {
	int insertBillRelation(@Param("billId") String billId, @Param("billDetailId") String billDetailId);

	List<String> selectBillDetaiIdByBillId(@Param("billId") String billId, @Param("limit") int limit,
			@Param("start") int start);

	int selectBillDetaiIdByBillIdCount(@Param("billId") String billId);
}
