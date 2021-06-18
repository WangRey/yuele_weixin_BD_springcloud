package org.sicnu.zuul.exception;

public class DefException extends  RuntimeException{
    private int code ;
    private String message;
    private DefException(){}
    public DefException(ExceptionType e, String message){
        this.code = e.getCode();
        this.message = message;
    }
    public int getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }


}