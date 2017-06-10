package com.whut.dsbs.common.dto;

import java.io.Serializable;

/**
 * 材料控制柜实体类
 *
 * Created by zyb on 2017-05-29.
 */
public class Cabinet extends BaseMaterial implements Serializable{

    private String cabinetEnvironment;

    private String flontPanelSize;

    private String rearPanelSize;

    private String leftPanelSize;

    private String rightPanelSize;

    private String floorSize;

    private String roofSize;

    public Cabinet() {
    }

    public Cabinet(String materialCode, String materialName, String materialTypeCode, String cabinetEnvironment, String flontPanelSize, String rearPanelSize, String leftPanelSize, String rightPanelSize, String floorSize, String roofSize) {
        super(materialCode, materialName, materialTypeCode);
        this.cabinetEnvironment = cabinetEnvironment;
        this.flontPanelSize = flontPanelSize;
        this.rearPanelSize = rearPanelSize;
        this.leftPanelSize = leftPanelSize;
        this.rightPanelSize = rightPanelSize;
        this.floorSize = floorSize;
        this.roofSize = roofSize;
    }

    public String getCabinetEnvironment() {
        return cabinetEnvironment;
    }

    public void setCabinetEnvironment(String cabinetEnvironment) {
        this.cabinetEnvironment = cabinetEnvironment;
    }

    public String getFlontPanelSize() {
        return flontPanelSize;
    }

    public void setFlontPanelSize(String flontPanelSize) {
        this.flontPanelSize = flontPanelSize;
    }

    public String getRearPanelSize() {
        return rearPanelSize;
    }

    public void setRearPanelSize(String rearPanelSize) {
        this.rearPanelSize = rearPanelSize;
    }

    public String getLeftPanelSize() {
        return leftPanelSize;
    }

    public void setLeftPanelSize(String leftPanelSize) {
        this.leftPanelSize = leftPanelSize;
    }

    public String getRightPanelSize() {
        return rightPanelSize;
    }

    public void setRightPanelSize(String rightPanelSize) {
        this.rightPanelSize = rightPanelSize;
    }

    public String getFloorSize() {
        return floorSize;
    }

    public void setFloorSize(String floorSize) {
        this.floorSize = floorSize;
    }

    public String getRoofSize() {
        return roofSize;
    }

    public void setRoofSize(String roofSize) {
        this.roofSize = roofSize;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cabinet{");
        sb.append("materialCode='").append(materialCode).append('\'');
        sb.append(", materialName='").append(materialName).append('\'');
        sb.append(", materialTypeCode='").append(materialTypeCode).append('\'');
        sb.append(", cabinetEnvironment='").append(cabinetEnvironment).append('\'');
        sb.append(", flontPanelSize='").append(flontPanelSize).append('\'');
        sb.append(", rearPanelSize='").append(rearPanelSize).append('\'');
        sb.append(", leftPanelSize='").append(leftPanelSize).append('\'');
        sb.append(", rightPanelSize='").append(rightPanelSize).append('\'');
        sb.append(", floorSize='").append(floorSize).append('\'');
        sb.append(", roofSize='").append(roofSize).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
