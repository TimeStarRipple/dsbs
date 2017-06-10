package com.whut.dsbs.common.dto;

import java.io.Serializable;

/**
 * 材料保护器
 *
 * Created by zyb on 2017-05-29.
 */
public class Protector extends BaseMaterial implements Serializable{

    private String protectorModel;

    private String unitMeasurement;

    private String unitDosage;

    private String materialsNeeded;

    private String remarks;

    private String belongsSeries;

    private String casing;

    private String structure;

    private String properTemperature;

    public Protector() {
    }

    public Protector(String materialCode, String materialName, String materialTypeCode, String protectorModel, String unitMeasurement, String unitDosage, String materialsNeeded, String remarks, String belongsSeries, String casing, String structure, String properTemperature) {
        super(materialCode, materialName, materialTypeCode);
        this.protectorModel = protectorModel;
        this.unitMeasurement = unitMeasurement;
        this.unitDosage = unitDosage;
        this.materialsNeeded = materialsNeeded;
        this.remarks = remarks;
        this.belongsSeries = belongsSeries;
        this.casing = casing;
        this.structure = structure;
        this.properTemperature = properTemperature;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getProtectorModel() {
        return protectorModel;
    }

    public void setProtectorModel(String protectorModel) {
        this.protectorModel = protectorModel;
    }

    public String getUnitMeasurement() {
        return unitMeasurement;
    }

    public void setUnitMeasurement(String unitMeasurement) {
        this.unitMeasurement = unitMeasurement;
    }

    public String getUnitDosage() {
        return unitDosage;
    }

    public void setUnitDosage(String unitDosage) {
        this.unitDosage = unitDosage;
    }

    public String getMaterialsNeeded() {
        return materialsNeeded;
    }

    public void setMaterialsNeeded(String materialsNeeded) {
        this.materialsNeeded = materialsNeeded;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getMaterialTypeCode() {
        return materialTypeCode;
    }

    public void setMaterialTypeCode(String materialTypeCode) {
        this.materialTypeCode = materialTypeCode;
    }

    public String getBelongsSeries() {
        return belongsSeries;
    }

    public void setBelongsSeries(String belongsSeries) {
        this.belongsSeries = belongsSeries;
    }

    public String getCasing() {
        return casing;
    }

    public void setCasing(String casing) {
        this.casing = casing;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getProperTemperature() {
        return properTemperature;
    }

    public void setProperTemperature(String properTemperature) {
        this.properTemperature = properTemperature;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Protector{");
        sb.append("materialCode='").append(materialCode).append('\'');
        sb.append(", materialName='").append(materialName).append('\'');
        sb.append(", protectorModel='").append(protectorModel).append('\'');
        sb.append(", unitMeasurement='").append(unitMeasurement).append('\'');
        sb.append(", unitDosage='").append(unitDosage).append('\'');
        sb.append(", materialsNeeded='").append(materialsNeeded).append('\'');
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append(", materialTypeCode='").append(materialTypeCode).append('\'');
        sb.append(", belongsSeries='").append(belongsSeries).append('\'');
        sb.append(", casing='").append(casing).append('\'');
        sb.append(", structure='").append(structure).append('\'');
        sb.append(", properTemperature='").append(properTemperature).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
