package cn.reimbursement.util;

import lombok.Data;

@Data
public class ServerResult<T> {
	private Integer status;
	private String msg;
	private T data;

	public ServerResult(Integer status, String msg, T data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public ServerResult(Integer status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	
	public ServerResult() {
		super();
	}

	public ServerResult(Integer status) {
		super();
		this.status = status;
	}

}
