package cn.reimbursement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.reimbursement.pojo.Bill;

@Repository
public interface BillDao {
	List<Bill> selectBillByCompany(@Param("billCompany")String billCompany);
	Integer selectBillCountById(@Param("billId")String billId);
	Integer insertBill(@Param("billMap")Map<String,String> billMap);
	List<Bill> selectBill(@Param("billMap")Map<String,Object> billMap);
	List<Bill> selectBillByMonth(@Param("year") String year,@Param("month") String month);
	Bill selectBillById(@Param("billId")String billId);
	Integer updateBillStatusById(@Param("billId")String billId,@Param("contractStatus")String contractStatus,@Param("invoiceStatus")String invoiceStatus);
	Integer updateBillEnd(@Param("billId")String billId);
	Integer selectIsEndById(@Param("billId")String billId);
	Integer selectRegist(@Param("billId")String billId,@Param("staffCompany")String staffCompany,@Param("staffName")String staffName);
	Integer updateBill(@Param("billMap")Map<String,String> billMap);
	Integer deleteBillById(@Param("billId")String billId);
	Integer selectBillCount(@Param("billMap")Map<String,Object> billMap);
}
