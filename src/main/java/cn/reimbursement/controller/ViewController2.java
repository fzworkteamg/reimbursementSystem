package cn.reimbursement.controller;


import cn.reimbursement.dao.CompanyDao;
import cn.reimbursement.service.CompanyService;
import cn.reimbursement.service.DepService;
import cn.reimbursement.util.ServerResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ViewController2 {

    @Autowired
    CompanyService companyService;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    DepService depService;
    @RequestMapping("/toShow")
    public String toShow(){
        return "show";
    }
    @RequestMapping("/messages")
    @ResponseBody
    public ServerResult<List<String>> test(HttpSession session){
        session.setAttribute("companys",companyDao.selectCompany());
        return companyService.selectCompany();
    }


}
