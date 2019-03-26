package cn.reimbursement.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao {
	ArrayList<String> selectCompany();
}
