package com.whut.dsbs.common.dto;

import java.io.Serializable;

/**
 * 投标报价所需材料实体类
 *
 * Created by zyb on 2017-05-29.
 */
public class BiddingMaterial implements Serializable{

    private int biddingId;

    private String materialCode;

    private String materialName;

    private int neededNumber;

    private double costPrice;

    private int buyNumber;

    private double buyPrice;

    private String materialTypeCode;

    private String materialTypeName;

    private int isEnough;

    private int sumNumber;

    private MaterialType materialType;

    private BaseMaterial materialObject;

    private Material material;

    public BiddingMaterial() {
    }

    public BiddingMaterial(int biddingId, String materialCode, String materialName, int neededNumber, double costPrice, int buyNumber, double buyPrice, String materialTypeCode, String materialTypeName, int isEnough, int sumNumber, MaterialType materialType, BaseMaterial materialObject, Material material) {
        this.biddingId = biddingId;
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.neededNumber = neededNumber;
        this.costPrice = costPrice;
        this.buyNumber = buyNumber;
        this.buyPrice = buyPrice;
        this.materialTypeCode = materialTypeCode;
        this.materialTypeName = materialTypeName;
        this.isEnough = isEnough;
        this.sumNumber = sumNumber;
        this.materialType = materialType;
        this.materialObject = materialObject;
        this.material = material;
    }

    public int getBiddingId() {
        return biddingId;
    }

    public void setBiddingId(int biddingId) {
        this.biddingId = biddingId;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public int getNeededNumber() {
        return neededNumber;
    }

    public void setNeededNumber(int neededNumber) {
        this.neededNumber = neededNumber;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public int getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(int buyNumber) {
        this.buyNumber = buyNumber;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getMaterialTypeCode() {
        return materialTypeCode;
    }

    public void setMaterialTypeCode(String materialTypeCode) {
        this.materialTypeCode = materialTypeCode;
    }

    public int getIsEnough() {
        return isEnough;
    }

    public void setIsEnough(int isEnough) {
        this.isEnough = isEnough;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public BaseMaterial getMaterialObject() {
        return materialObject;
    }

    public void setMaterialObject(BaseMaterial materialObject) {
        this.materialObject = materialObject;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public int getSumNumber() {
        return sumNumber;
    }

    public void setSumNumber(int sumNumber) {
        this.sumNumber = sumNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BiddingMaterial{");
        sb.append("biddingId=").append(biddingId);
        sb.append(", materialCode='").append(materialCode).append('\'');
        sb.append(", materialName='").append(materialName).append('\'');
        sb.append(", neededNumber=").append(neededNumber);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", buyNumber=").append(buyNumber);
        sb.append(", buyPrice=").append(buyPrice);
        sb.append(", materialTypeCode='").append(materialTypeCode).append('\'');
        sb.append(", materialTypeName='").append(materialTypeName).append('\'');
        sb.append(", isEnough=").append(isEnough);
        sb.append(", sumNumber=").append(sumNumber);
        sb.append(", materialType=").append(materialType);
        sb.append(", materialObject=").append(materialObject);
        sb.append(", material=").append(material);
        sb.append('}');
        return sb.toString();
    }
}
