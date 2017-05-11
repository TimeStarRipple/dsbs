package com.whut.dsbs.common.dto;

import java.io.Serializable;

/**
 * 材料类型
 *
 * Created by zyb on 2017-05-09.
 */
public class MaterialType implements Serializable{

    private int materialTypeId;

    private String materialTypeName;

    private String materialTableName;

    public MaterialType() {
    }

    public MaterialType(String materialTableName, int materialTypeId, String materialTypeName) {
        this.materialTableName = materialTableName;
        this.materialTypeId = materialTypeId;
        this.materialTypeName = materialTypeName;
    }

    public int getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(int materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public String getMaterialTableName() {
        return materialTableName;
    }

    public void setMaterialTableName(String materialTableName) {
        this.materialTableName = materialTableName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MaterialType{");
        sb.append("materialTypeId=").append(materialTypeId);
        sb.append(", materialTypeName='").append(materialTypeName).append('\'');
        sb.append(", materialTableName='").append(materialTableName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
