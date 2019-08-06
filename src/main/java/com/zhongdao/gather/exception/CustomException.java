package com.zhongdao.gather.exception;

/**
 * 自定义异常
 */
public class CustomException extends RuntimeException {

    public CustomException(long code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public CustomException(){
      this(100001,"all error");
    }

    private long code;
    private String msg;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
