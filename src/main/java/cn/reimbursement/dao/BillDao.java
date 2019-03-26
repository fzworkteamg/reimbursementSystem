package cn.reimbursement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.reimbursement.pojo.Bill;

@Repository
public interface BillDao {
	public List<Bill> selectBillByCompany(@Param("billCompany")String billCompany);
	public int selectBillById(@Param("billId")String billId);
	void insertBill(@Param("billMap")Map<String,Object> billMap);
}
