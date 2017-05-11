package com.whut.dsbs.common.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 角色（用于权限控制）
 *
 * Created by zyb on 2017-05-09.
 */
public class Role implements Serializable{

    private int id;

    private String name;

    private String type;

    private List<Permission> permissions;

    public Role() {
    }

    public Role(int id, String name, String type, List<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.permissions = permissions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", permissions=").append(permissions);
        sb.append('}');
        return sb.toString();
    }
}
