package cn.reimbursement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.reimbursement.pojo.Bill;

@Repository
public interface BillDao {
	List<Bill> selectBillByCompany(@Param("billCompany")String billCompany);
	int selectBillCountById(@Param("billId")String billId);
	void insertBill(@Param("billMap")Map<String,String> billMap);
	List<Bill> selectBill(@Param("billMap")Map<String,String> billMap);
	List<Bill> selectBillByMonth(@Param("year") String year,@Param("month") String month);
	Bill selectBillById(@Param("billId")String billId);
	Integer updateBillStatusById(@Param("billId")String billId,@Param("contractStatus")String contractStatus,@Param("invoiceStatus")String invoiceStatus);
	Integer updateBillEnd(@Param("billId")String billId);
}
