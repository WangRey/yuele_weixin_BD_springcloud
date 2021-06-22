package org.sicnu.shop.model.enums;


public enum SysLogType {

    All("All", "全部日志"),
    LoginLog("LoginLog", "登录日志"),
    SystemLog("SystemLog", "系统日志"),
    OperationLog("OperationLog", "操作日志");

    SysLogType(String type, String typeExplain) {
        this.type = type;
        this.typeExplain = typeExplain;
    }

    private String type;
    private String typeExplain;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeExplain() {
        return typeExplain;
    }

    public void setTypeExplain(String typeExplain) {
        this.typeExplain = typeExplain;
    }
}
