package cn.reimbursement.enums;

public enum SessionEnum {
	STAFF("staff"), BILL("bill"), STATUS("status"),BILL_ID("billId");
	private String name;

	private SessionEnum(String name) {
		this.name = name;
	}

	public String getValue() {
		return name;
	}
}
