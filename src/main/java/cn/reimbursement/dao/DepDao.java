package cn.reimbursement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepDao {
	public List<String> selectDepByCompany(@Param("companyName")String companyName);
}
