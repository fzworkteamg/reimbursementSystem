package cn.reimbursement;

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
//		System.out.println(processService.selectProcessById(1));
	}

}
