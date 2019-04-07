package cn.reimbursement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.enums.NumberEnum;
import cn.reimbursement.service.StaffService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReimbursementStaffTests {
	
	@Autowired
	private StaffService staffService;
	
	@Test
	public void testLoginByIdAndPassword() {
	}

}
