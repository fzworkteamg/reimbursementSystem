package cn.reimbursement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.reimbursement.service.DepService;
import cn.reimbursement.util.ServerResult;

@RestController
@RequestMapping("/dep")
public class DepController {
	
	@Autowired
	private DepService depService;
	
	@PostMapping("/selectDepByCompany")
	public ServerResult<List<String>> selectDepByCompany(String company) {
		return depService.selectDepByCompany(company);
	}
	
}
