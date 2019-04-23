package cn.reimbursement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRelationDao {
	int insertBillRelation(@Param("billId") String billId, @Param("billDetailId") String billDetailId);

	List<String> selectBillDetaiIdByBillId(@Param("billId") String billId, @Param("limit") int limit,
			@Param("start") int start);

	List<String> selectAllBillDetaiIdByBillId(@Param("billId") String billId);

	int selectBillDetaiIdByBillIdCount(@Param("billId") String billId);

	int deleteBillRelationByBillId(@Param("billId") String billId);
}
