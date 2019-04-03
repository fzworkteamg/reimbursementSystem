package cn.reimbursement.enums;

public enum InfoEnum {
	
	SUCCESS("成功"),FAIL("失败"),WAIT_AUDIT("待审核"),AUDITED("已审核");
	
    private final String name;
    
    private InfoEnum(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
