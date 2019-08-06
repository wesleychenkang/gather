package com.zhongdao.gather.bean.common;

public class BaseResult<T> {
    /**
     * 信息
     */
    private String msg;
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 详细内容
     */
    private T detail;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getDetail() {
        return detail;
    }

    public void setDetail(T detail) {
        this.detail = detail;
    }
}
