package cn.reimbursement.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.reimbursement.pojo.Staff;

@Repository
public interface StaffDao {
	public Staff selectStaffById(@Param("staffId") String staffId);
}