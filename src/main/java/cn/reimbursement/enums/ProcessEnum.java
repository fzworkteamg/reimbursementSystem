package cn.reimbursement.enums;

public enum ProcessEnum {
	
	SUCCESS("成功"),FAIL("失败");
	
    private final String name;
    
    private ProcessEnum(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
