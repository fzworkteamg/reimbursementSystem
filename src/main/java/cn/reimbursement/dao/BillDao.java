package cn.reimbursement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.reimbursement.pojo.Bill;
import cn.reimbursement.util.ServerResult;

@Repository
public interface BillDao {
	List<Bill> selectBillByCompany(@Param("billCompany")String billCompany);
	int selectBillById(@Param("billId")String billId);
	void insertBill(@Param("billMap")Map<String,Object> billMap);
	List<Bill> selectBill(@Param("billMap")Map<String,String> billMap);
}
