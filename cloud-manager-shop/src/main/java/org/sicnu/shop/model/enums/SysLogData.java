package org.sicnu.shop.model.enums;

/**
 * @author Tracy
 * @date 2020/11/8 15:41
 */
public enum SysLogData {


    NotFound("Not Found", "查找数据为空"),
    Success("Success", "操作成功"),
    Fail("Fail", "操作失败"),
    BadRequest("Bad Request, Check Your Params", "参数错误"),
    UserNameORPasswordError("Username or Password Error", "用户名或密码错误"),
    NotLogin("Not Login! Login First!", "尚未登录");

    SysLogData() {
    }

    SysLogData(String msg) {
        this.msg = msg;
    }

    SysLogData(String msg, String explain) {
        this.msg = msg;
        this.explain = explain;
    }

    private String msg;
    private String explain;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
