package cn.reimbursement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.reimbursement.dao.CompanyDao;
import cn.reimbursement.dao.DepDao;
import cn.reimbursement.dao.StaffDao;
import cn.reimbursement.util.WxUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ReimbursementApplicationTests {
	
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private DepDao depDao;
	@Autowired
	private StaffDao staffDao;
	
	//插入员工
	@Test
	public void test() throws IOException, Exception{
		List<Map<String,Object>> strList=WxUtil.getOaStaffInfo(WxUtil.getAccessToken(), "2", "1");
		for (Map<String, Object> staffMap : strList) {
			String strDep=staffMap.get("department").toString().replaceAll("\\[", "");
			strDep=strDep.replaceAll("\\]", "");
			int depId=Integer.valueOf(strDep);
			if(depId<=13) {
				staffMap.put("companyName", companyDao.selectCompanyById(2));
				staffMap.put("depName", depDao.selectDepById(depId));
			}else if(depId>=14){
				staffMap.put("companyName", companyDao.selectCompanyById(depId));
				staffMap.put("depName", depDao.selectDepById(depId));
			}
			staffDao.insertWxStaff(staffMap);
		}
	}
	
	//测试插入部门与公司
	@Test
	public void test2() throws Exception {
		List<Map<String,Object>> strList=WxUtil.getCompanys();
		for (Map<String, Object> map : strList) {
			Integer id=(Integer) map.get("id");
			String name=(String) map.get("name");
			companyDao.insertCompany(id, name);
			if(id!=2) {
				depDao.insertDep(id, name+"部门", name);
			}else {
				List<Map<String,Object>> depList=WxUtil.getDepByCompanyId(2);
				System.out.println(depList.toString());
				for (Map<String, Object> depMap : depList) {
					depDao.insertDep((Integer)depMap.get("id"), (String)depMap.get("name"), name);
				}
			}
			
		}
	}
}
