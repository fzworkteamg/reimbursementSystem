package cn.reimbursement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.reimbursement.pojo.Staff;

@Repository
public interface StaffDao {
	Staff selectStaffById(@Param("staffId") String staffId);
	List<Staff> selectStaffByCompanyAndDep(@Param("company")String company, @Param("dep")String dep);
}