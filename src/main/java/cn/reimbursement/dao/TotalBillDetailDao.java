package cn.reimbursement.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author linweijie
 * @date 2019年4月16日
 */
public interface TotalBillDetailDao {
	int insertTotalBillDetail(@Param("totalBillId")String totalBillId,@Param("billId")String billId);
	void deleteTotalBillDetailByTotalBillId(@Param("totalBillId")String totalBillId);
}
