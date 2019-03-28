package cn.reimbursement.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.reimbursement.dao.StaffDao;
import cn.reimbursement.enums.InfoEnum;
import cn.reimbursement.pojo.Staff;
import cn.reimbursement.util.ServerResult;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffDao staffDao;

	public ServerResult loginByIdAndPassword(HttpServletRequest request,String staffId,String staffPassword) {
		Staff staff=staffDao.selectStaffById(staffId);
		if(staff==null||!staff.getStaffPassword().equals(staffPassword)) 
			return new ServerResult(1,InfoEnum.FAIL.toString());
		HttpSession session=request.getSession();
		staff.setStaffPassword("");
		session.setAttribute("staff", staff);
		return new ServerResult(0,InfoEnum.SUCCESS.toString());
	}

	public ServerResult<List<String>> selectStaffByCompanyAndDep(String company, String dep) {
		List<Staff> staffList=staffDao.selectStaffByCompanyAndDep(company, dep);
		List<String> staffDutyList=new ArrayList<String>();
		
		for(int i=0;i<staffList.size();i++) 
			staffDutyList.add(staffList.get(i).getStaffName()+"|"+staffList.get(i).getDutyName());
		
		return new ServerResult<List<String>>(0,InfoEnum.SUCCESS.toString(),staffDutyList);
	}

}
