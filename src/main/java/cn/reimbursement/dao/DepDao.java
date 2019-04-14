package cn.reimbursement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepDao {
	List<String> selectDepByCompany(@Param("companyName")String companyName);
	Integer insertDep(@Param("depId")Integer depId,@Param("depName")String depName,@Param("companyName")String companyName);
	String selectDepById(@Param("depId")Integer depId);
}
