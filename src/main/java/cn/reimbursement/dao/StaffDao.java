package cn.reimbursement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.reimbursement.pojo.Staff;

@Repository
public interface StaffDao {
	Staff selectStaffById(@Param("staffId") String staffId);
	Staff selectStaffByTel(@Param("staffTel")String staffTel);
	List<Staff> selectStaffByCompanyAndDep(@Param("company")String company, @Param("dep")String dep);
	Integer insertWxStaff(@Param("staffMap")Map<String,Object> staffMap);
	Integer selectStaffCount();
	Integer deleteStaff();
}