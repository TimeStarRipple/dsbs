package com.whut.dsbs.common.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 制作产品所需要的材料
 *
 * Created by zyb on 2017-04-30.
 */
public class Material implements Serializable{

    private Integer id;

    private String materialCode;

    private String materialName;

    private String materialDescription;

    private Double materialQuotedPrice;

    private Double materialPrice;

    private Double materialNumber;

    //材料类型id
    private int materialTypeId;

    //最后一次数据更新时间
    private Date lastUpdateTime;

    //材料类型
    private MaterialType materialType;

    //材料属性
    private Object materialAttribute;

    public Material() {
    }

    public Material(Integer id, String materialCode, String materialName, String materialDescription, Double materialQuotedPrice, Double materialPrice, Double materialNumber, int materialTypeId, Date lastUpdateTime, MaterialType materialType, Object materialAttribute) {
        this.id = id;
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.materialDescription = materialDescription;
        this.materialQuotedPrice = materialQuotedPrice;
        this.materialPrice = materialPrice;
        this.materialNumber = materialNumber;
        this.materialTypeId = materialTypeId;
        this.lastUpdateTime = lastUpdateTime;
        this.materialType = materialType;
        this.materialAttribute = materialAttribute;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public Double getMaterialQuotedPrice() {
        return materialQuotedPrice;
    }

    public void setMaterialQuotedPrice(Double materialQuotedPrice) {
        this.materialQuotedPrice = materialQuotedPrice;
    }

    public Double getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(Double materialPrice) {
        this.materialPrice = materialPrice;
    }

    public Double getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(Double materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public int getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(int materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public Object getMaterialAttribute() {
        return materialAttribute;
    }

    public void setMaterialAttribute(Object materialAttribute) {
        this.materialAttribute = materialAttribute;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Material{");
        sb.append("id=").append(id);
        sb.append(", materialCode='").append(materialCode).append('\'');
        sb.append(", materialName='").append(materialName).append('\'');
        sb.append(", materialDescription='").append(materialDescription).append('\'');
        sb.append(", materialQuotedPrice=").append(materialQuotedPrice);
        sb.append(", materialPrice=").append(materialPrice);
        sb.append(", materialNumber=").append(materialNumber);
        sb.append(", materialTypeId=").append(materialTypeId);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", materialType=").append(materialType);
        sb.append(", materialAttribute=").append(materialAttribute);
        sb.append('}');
        return sb.toString();
    }
}
