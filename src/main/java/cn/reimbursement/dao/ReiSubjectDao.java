package cn.reimbursement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReiSubjectDao {
	List<Map<String, String>> selectDepContentCommentByCompanyDep(@Param("company")String company,@Param("dep")String dep);
}
