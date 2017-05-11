package com.whut.dsbs.common.dto;

import java.io.Serializable;

/**
 * url权限
 *
 * Created by zyb on 2017-05-09.
 */
public class Permission implements Serializable{

    private int id;

    private String url;

    private String name;

    private String value;

    public Permission() {
    }

    public Permission(int id, String url, String name, String value) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Permission{");
        sb.append("id=").append(id);
        sb.append(", url='").append(url).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
