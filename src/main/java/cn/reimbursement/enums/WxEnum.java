package cn.reimbursement.enums;

public enum WxEnum {
	APP_ID("wxdc2a7022949cf052"), SECRET("DDM3QsH6EY-7xanGo1h4PN5ZhVh9Ep6fAxF9elT7qGY");

	private final String info;

	private WxEnum(String info) {
		this.info = info;
	}

	public String getValue() {
		return info;
	}
}
