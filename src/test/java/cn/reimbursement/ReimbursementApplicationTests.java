package cn.reimbursement;

import cn.reimbursement.util.AddressListTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReimbursementApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void test(){
		AddressListTool.getAccessToken();
	}
}
