//package org.sicnu.zuul.exception;
//
//import org.sicnu.zuul.model.AjaxResponse;
//import org.springframework.validation.BindException;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@ControllerAdvice
//public class WebExceptionHandler {
//    @ExceptionHandler(DefException.class)
//    @ResponseBody
//    public AjaxResponse defException(DefException e) {
//        if(e.getCode() == ExceptionType.SYSTEM_ERROR.getCode()){
//            //400异常不需要持久化，将异常信息以友好的方式告知用户就可以
//            //TODO 将500异常信息持久化处理，方便运维人员处理
//        }
//        return AjaxResponse.error(e);
//    }
//
////    @ExceptionHandler(Exception.class)
////    @ResponseBody
////    public AjaxResponse exception(Exception e) {
////        //TODO 将异常信息持久化处理，方便运维人员处理
////        return AjaxResponse.error(new DefException(ExceptionType.OTHER_ERROR,"未知异常"));
////    }
//
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public AjaxResponse handleBindException(MethodArgumentNotValidException ex) {
//        FieldError fieldError = ex.getBindingResult().getFieldError();
//        return AjaxResponse.error(new DefException(ExceptionType.USER_INPUT_ERROR,fieldError.getDefaultMessage()));
//    }
//
//
//    @ExceptionHandler(BindException.class)
//    @ResponseBody
//    public AjaxResponse handleBindException(BindException ex) {
//        FieldError fieldError = ex.getBindingResult().getFieldError();
//        return AjaxResponse.error(new DefException(ExceptionType.USER_INPUT_ERROR,fieldError.getDefaultMessage()));
//    }
//
//}
