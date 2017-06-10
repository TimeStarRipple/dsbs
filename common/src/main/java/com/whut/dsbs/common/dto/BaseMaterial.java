package com.whut.dsbs.common.dto;

import java.io.Serializable;

/**
 * 材料基础类
 *
 * Created by zyb on 2017-05-29.
 */
public class BaseMaterial implements Serializable{

    protected String materialCode;

    protected String materialName;

    protected String materialTypeCode;

    public BaseMaterial() {
    }

    public BaseMaterial(String materialCode, String materialName, String materialTypeCode) {
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.materialTypeCode = materialTypeCode;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialTypeCode() {
        return materialTypeCode;
    }

    public void setMaterialTypeCode(String materialTypeCode) {
        this.materialTypeCode = materialTypeCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseMaterial{");
        sb.append("materialCode='").append(materialCode).append('\'');
        sb.append(", materialName='").append(materialName).append('\'');
        sb.append(", materialTypeCode='").append(materialTypeCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
