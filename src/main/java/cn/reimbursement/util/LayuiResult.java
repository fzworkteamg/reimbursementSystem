package cn.reimbursement.util;

import lombok.Data;

@Data
public class LayuiResult<T> {
	private String msg;
    private  T data;
    private  int code;
    private  int count;
    public LayuiResult(String msg, T data, int code, int count) {
        this.msg = msg;
        this.data = data;
        this.code = code;
        this.count = count;
    }

    public LayuiResult() {
    }

}
