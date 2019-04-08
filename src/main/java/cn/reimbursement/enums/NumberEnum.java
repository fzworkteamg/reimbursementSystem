package cn.reimbursement.enums;

public enum NumberEnum {
	ZERO("0"), ONE("1"),TWO("2");
	
	private String number;

	private NumberEnum(String number) {
		this.number = number;
	}

	public String getValue() {
		return number;
	}
}
