package cn.reimbursement.service;

import javax.servlet.http.HttpServletRequest;

public interface ViewService {
	void toBillDetail(String data, HttpServletRequest request);
}
