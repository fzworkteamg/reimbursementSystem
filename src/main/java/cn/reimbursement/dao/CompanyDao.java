package cn.reimbursement.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao {
	ArrayList<String> selectCompany();
	Integer insertCompany(@Param("companyId")Integer companyId,@Param("companyName")String companyName);
	String selectCompanyById(@Param("companyId")Integer companyId);
}
