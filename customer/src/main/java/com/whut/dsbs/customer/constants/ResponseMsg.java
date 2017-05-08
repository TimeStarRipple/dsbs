package com.whut.dsbs.customer.constants;

import java.io.Serializable;

/**
 * 作为http响应返回的封装数据
 *
 * Created by zyb on 2017-05-08.
 */
public class ResponseMsg implements Serializable{

    private int status;

    private String statusText;

    private Object data;

    public ResponseMsg() {
    }

    public ResponseMsg(int status, String statusText, Object data) {
        this.status = status;
        this.statusText = statusText;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseMsg{");
        sb.append("status=").append(status);
        sb.append(", statusText='").append(statusText).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
