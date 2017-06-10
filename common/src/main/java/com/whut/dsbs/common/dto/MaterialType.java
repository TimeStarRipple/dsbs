package com.whut.dsbs.common.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 材料类型
 *
 * Created by zyb on 2017-05-09.
 */
public class MaterialType implements Serializable{

    private String materialTypeCode;

    private String materialTypeName;

    private String materialTableName;

    private List<Material> materials;

    public MaterialType() {
    }

    public MaterialType(String materialTypeCode, String materialTypeName, String materialTableName, List<Material> materials) {
        this.materialTypeCode = materialTypeCode;
        this.materialTypeName = materialTypeName;
        this.materialTableName = materialTableName;
        this.materials = materials;
    }

    public String getMaterialTypeCode() {
        return materialTypeCode;
    }

    public void setMaterialTypeCode(String materialTypeCode) {
        this.materialTypeCode = materialTypeCode;
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

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MaterialType{");
        sb.append("materialTypeCode='").append(materialTypeCode).append('\'');
        sb.append(", materialTypeName='").append(materialTypeName).append('\'');
        sb.append(", materialTableName='").append(materialTableName).append('\'');
        sb.append(", materials=").append(materials);
        sb.append('}');
        return sb.toString();
    }
}
