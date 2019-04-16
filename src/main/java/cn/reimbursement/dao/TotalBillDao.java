package cn.reimbursement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.reimbursement.pojo.Bill;

/**
 * @author linweijie
 * @date 2019年4月16日
 */
public interface TotalBillDao {
	List<Bill> selectBillByTotalBillId(@Param("totalBillId") String totalBillId, @Param("limit") int limit,
			@Param("start") int start);

	int selectBillCountByTotalBillId(@Param("totalBillId") String totalBillId);
}
