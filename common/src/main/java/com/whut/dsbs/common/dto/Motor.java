package com.whut.dsbs.common.dto;

import java.io.Serializable;

/**
 * 材料电机
 *
 * Created by zyb on 2017-05-29.
 */
public class Motor extends BaseMaterial implements Serializable{

    private String motorModel;

    private String unitMeasurement;

    private String unitDosage;

    private String materialsNeeded;

    private String remarks;

    private String belongsSeries;

    private String casing;

    private String powerRange;

    private double singleTurnPower;

    private double externalDiameter;

    private double singleHorsepower;

    private String horsepowerRange;

    public Motor() {
    }

    public Motor(String materialCode, String materialName, String materialTypeCode, String motorModel, String unitMeasurement, String unitDosage, String materialsNeeded, String remarks, String belongsSeries, String casing, String powerRange, double singleTurnPower, double externalDiameter, double singleHorsepower, String horsepowerRange) {
        super(materialCode, materialName, materialTypeCode);
        this.motorModel = motorModel;
        this.unitMeasurement = unitMeasurement;
        this.unitDosage = unitDosage;
        this.materialsNeeded = materialsNeeded;
        this.remarks = remarks;
        this.belongsSeries = belongsSeries;
        this.casing = casing;
        this.powerRange = powerRange;
        this.singleTurnPower = singleTurnPower;
        this.externalDiameter = externalDiameter;
        this.singleHorsepower = singleHorsepower;
        this.horsepowerRange = horsepowerRange;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMotorModel() {
        return motorModel;
    }

    public void setMotorModel(String motorModel) {
        this.motorModel = motorModel;
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

    public String getPowerRange() {
        return powerRange;
    }

    public void setPowerRange(String powerRange) {
        this.powerRange = powerRange;
    }

    public double getSingleTurnPower() {
        return singleTurnPower;
    }

    public void setSingleTurnPower(double singleTurnPower) {
        this.singleTurnPower = singleTurnPower;
    }

    public double getExternalDiameter() {
        return externalDiameter;
    }

    public void setExternalDiameter(double externalDiameter) {
        this.externalDiameter = externalDiameter;
    }

    public double getSingleHorsepower() {
        return singleHorsepower;
    }

    public void setSingleHorsepower(double singleHorsepower) {
        this.singleHorsepower = singleHorsepower;
    }

    public String getHorsepowerRange() {
        return horsepowerRange;
    }

    public void setHorsepowerRange(String horsepowerRange) {
        this.horsepowerRange = horsepowerRange;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Motor{");
        sb.append("materialCode='").append(materialCode).append('\'');
        sb.append(", materialName='").append(materialName).append('\'');
        sb.append(", motorModel='").append(motorModel).append('\'');
        sb.append(", unitMeasurement='").append(unitMeasurement).append('\'');
        sb.append(", unitDosage='").append(unitDosage).append('\'');
        sb.append(", materialsNeeded='").append(materialsNeeded).append('\'');
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append(", materialTypeCode='").append(materialTypeCode).append('\'');
        sb.append(", belongsSeries='").append(belongsSeries).append('\'');
        sb.append(", casing='").append(casing).append('\'');
        sb.append(", powerRange='").append(powerRange).append('\'');
        sb.append(", singleTurnPower=").append(singleTurnPower);
        sb.append(", externalDiameter=").append(externalDiameter);
        sb.append(", singleHorsepower=").append(singleHorsepower);
        sb.append(", horsepowerRange='").append(horsepowerRange).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
