package com.whut.dsbs.common.dto;

import java.io.Serializable;

/**
 * 材料电缆
 *
 * Created by zyb on 2017-05-29.
 */
public class Cable extends BaseMaterial implements Serializable{

    private String cableModel;

    private String cableSpecifications;

    private String insulationMaterials;

    private String protectiveMaterial;

    private String armorMaterial;

    private String temperatureLevel;

    public Cable() {
    }

    public Cable(String materialCode, String materialName, String materialTypeCode, String cableModel, String cableSpecifications, String insulationMaterials, String protectiveMaterial, String armorMaterial, String temperatureLevel) {
        super(materialCode, materialName, materialTypeCode);
        this.cableModel = cableModel;
        this.cableSpecifications = cableSpecifications;
        this.insulationMaterials = insulationMaterials;
        this.protectiveMaterial = protectiveMaterial;
        this.armorMaterial = armorMaterial;
        this.temperatureLevel = temperatureLevel;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getCableModel() {
        return cableModel;
    }

    public void setCableModel(String cableModel) {
        this.cableModel = cableModel;
    }

    public String getCableSpecifications() {
        return cableSpecifications;
    }

    public void setCableSpecifications(String cableSpecifications) {
        this.cableSpecifications = cableSpecifications;
    }

    public String getInsulationMaterials() {
        return insulationMaterials;
    }

    public void setInsulationMaterials(String insulationMaterials) {
        this.insulationMaterials = insulationMaterials;
    }

    public String getProtectiveMaterial() {
        return protectiveMaterial;
    }

    public void setProtectiveMaterial(String protectiveMaterial) {
        this.protectiveMaterial = protectiveMaterial;
    }

    public String getArmorMaterial() {
        return armorMaterial;
    }

    public void setArmorMaterial(String armorMaterial) {
        this.armorMaterial = armorMaterial;
    }

    public String getTemperatureLevel() {
        return temperatureLevel;
    }

    public void setTemperatureLevel(String temperatureLevel) {
        this.temperatureLevel = temperatureLevel;
    }

    public String getMaterialTypeCode() {
        return materialTypeCode;
    }

    public void setMaterialTypeCode(String materialTypeCode) {
        this.materialTypeCode = materialTypeCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cable{");
        sb.append("materialCode='").append(materialCode).append('\'');
        sb.append(", materialName='").append(materialName).append('\'');
        sb.append(", cableModel='").append(cableModel).append('\'');
        sb.append(", cableSpecifications='").append(cableSpecifications).append('\'');
        sb.append(", insulationMaterials='").append(insulationMaterials).append('\'');
        sb.append(", protectiveMaterial='").append(protectiveMaterial).append('\'');
        sb.append(", armorMaterial='").append(armorMaterial).append('\'');
        sb.append(", temperatureLevel='").append(temperatureLevel).append('\'');
        sb.append(", materialTypeCode='").append(materialTypeCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
