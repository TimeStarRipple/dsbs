package com.whut.dsbs.common.dto;

import java.io.Serializable;

/**
 * 材料主机
 *
 * Created by zyb on 2017-05-29.
 */
public class Host extends BaseMaterial implements Serializable{

    private String hostSeries;

    private String hostModel;

    private String hostParameter;

    private String remarks;

    public Host() {
    }

    public Host(String materialCode, String materialName, String materialTypeCode, String hostSeries, String hostModel, String hostParameter, String remarks) {
        super(materialCode, materialName, materialTypeCode);
        this.hostSeries = hostSeries;
        this.hostModel = hostModel;
        this.hostParameter = hostParameter;
        this.remarks = remarks;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getHostSeries() {
        return hostSeries;
    }

    public void setHostSeries(String hostSeries) {
        this.hostSeries = hostSeries;
    }

    public String getHostModel() {
        return hostModel;
    }

    public void setHostModel(String hostModel) {
        this.hostModel = hostModel;
    }

    public String getHostParameter() {
        return hostParameter;
    }

    public void setHostParameter(String hostParameter) {
        this.hostParameter = hostParameter;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Host{");
        sb.append("materialCode='").append(materialCode).append('\'');
        sb.append(", materialName='").append(materialName).append('\'');
        sb.append(", materialTypeCode='").append(materialTypeCode).append('\'');
        sb.append(", hostSeries='").append(hostSeries).append('\'');
        sb.append(", hostModel='").append(hostModel).append('\'');
        sb.append(", hostParameter='").append(hostParameter).append('\'');
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append(", materialTypeCode='").append(materialTypeCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
