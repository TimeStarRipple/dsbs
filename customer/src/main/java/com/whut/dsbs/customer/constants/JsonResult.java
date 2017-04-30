package com.whut.dsbs.customer.constants;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

/**
 * 返回给前端的json对象
 *
 * Created by zyb on 2017-04-29.
 */
public class JsonResult implements Serializable{

    /**
     * 返回的编码
     */
    private String code;

    /**
     * 返回的信息
     */
    private String message;

    /***
     * 返回的对象
     */
    private Object object;

    /**
     * 记录总数（未分页）
     */
    private Long total;

    public JsonResult() {
    }

    public JsonResult(String code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;

        if(object instanceof Page) {
            this.setTotal(Long.valueOf(((Page)object).getTotal()));
        } else {
            if(object instanceof List){
                this.setTotal(Long.valueOf((long)((List)object).size()));
            }
            else{
                this.setTotal(1L);
            }

        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("JsonResult{");
        sb.append("code='").append(code).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", object=").append(object);
        sb.append('}');
        return sb.toString();
    }
}
