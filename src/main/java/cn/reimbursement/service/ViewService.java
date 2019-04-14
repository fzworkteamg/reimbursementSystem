package cn.reimbursement.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author linweijie
 * @date 2019年4月14日
 */

public interface ViewService {

	/*
	 * @Description: 转到详情页
	 * 
	 * @param data
	 * 
	 * @param request
	 */
	void toBillDetail(String data, HttpServletRequest request);
}
