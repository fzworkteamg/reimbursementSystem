package cn.reimbursement.util;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LayuiUploadResult<T> {
	private String msg;
    private  T data;
    private  int code;
    public LayuiUploadResult(String msg, T data, int code) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public LayuiUploadResult() {
    }

}
