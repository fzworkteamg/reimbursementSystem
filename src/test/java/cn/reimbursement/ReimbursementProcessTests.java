package cn.reimbursement;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.reimbursement.service.ProcessService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReimbursementProcessTests {
	
	@Autowired
	private ProcessService processService;
	
	@Test
	public void testSelectProcessById() {
		System.out.println(processService.selectProcessById(1));
	}
	
	@Test
	public void testInsertProcess() {
		Map<String,String> map=new HashMap<String,String>();
		map.put("process_company", "河北分1公司");
		map.put("process_department", "营销部");
		System.out.println(processService.insertProcess(map));
	}
	
	@Test
	public void testSelectProcess() {
		Map<String,String> map=new HashMap<String,String>();
		map.put("processCompany", "河北分公司");
		map.put("processDepartment", "营销部");
		System.out.println(processService.selectProcess(map));
	}

}
