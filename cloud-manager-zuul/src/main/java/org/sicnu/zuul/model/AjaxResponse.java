package org.sicnu.zuul.model;


import org.sicnu.zuul.exception.DefException;
import org.sicnu.zuul.exception.ExceptionType;

public class AjaxResponse {
    private boolean isok;
    private int code;
    private String message;
    private  Object data;

    public static AjaxResponse error(DefException e){
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(e.getCode());
        if(e.getCode() == ExceptionType.USER_INPUT_ERROR.getCode()){
            resultBean.setMessage(e.getMessage());
        }else if(e.getCode() == ExceptionType.SYSTEM_ERROR.getCode()){
            resultBean.setMessage(e.getMessage() + ",请将该异常信息发送给管理员!");
        }else{
            resultBean.setMessage("系统出现未知异常，请联系管理员!");
        }
        return resultBean;
    }

    public static AjaxResponse error(Object data){
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(400);
        resultBean.setMessage("操作失败");
        resultBean.setData(data);
        return resultBean;
    }

    public static AjaxResponse success() {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("操作成功");
        return resultBean;
    }

    public static AjaxResponse success(Object data) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("操作成功");
        resultBean.setData(data);
        return resultBean;
    }

    public boolean isIsok() {
        return isok;
    }

    public void setIsok(boolean isok) {
        this.isok = isok;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
