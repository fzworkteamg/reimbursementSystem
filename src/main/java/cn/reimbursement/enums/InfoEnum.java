package cn.reimbursement.enums;

public enum InfoEnum {

	SUCCESS("成功"), FAIL("失败"), WAIT_AUDIT("待审核"), AUDITED("已审核"),REJECT("驳回");

	private final String info;

	private InfoEnum(String info) {
		this.info = info;
	}

	public String getValue() {
		return info;
	}

}
