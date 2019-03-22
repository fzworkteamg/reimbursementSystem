package cn.reimbursement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.reimbursement.dao")
public class ReimbursementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReimbursementApplication.class, args);
	}

}
