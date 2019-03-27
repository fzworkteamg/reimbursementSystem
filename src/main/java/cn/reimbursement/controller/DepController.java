package cn.reimbursement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ServerResult<List<String>> selectDepByCompany(HttpServletRequest request) {
		System.out.println("param:"+request.getParameter("company"));
		return depService.selectDepByCompany(request.getParameter("company"));
	}

}
