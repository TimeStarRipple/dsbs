package com.whut.dsbs.common.dto;

import java.io.Serializable;

/**
 * 投标产品生产成本信息
 *
 * Created by zyb on 2017-06-02.
 */
public class BiddingProduceCost implements Serializable{

    private int id;

    private int biddingId;

    //人员工时费用
    private double personWorkingHours;
    //人员加班费用
    private double staffOvertimeCosts;
    //管理服务费用
    private double manageServiceCharges;
    //工具磨损成本
    private double toolWearCosts;
    //材料配件价格
    private double materialAccessoriesPrices;

    //总的生产成本
    private double sumProducePrices;

    public BiddingProduceCost() {
    }

    public BiddingProduceCost(int id, int biddingId, double personWorkingHours, double staffOvertimeCosts, double manageServiceCharges, double toolWearCosts, double materialAccessoriesPrices, double sumProducePrices) {
        this.id = id;
        this.biddingId = biddingId;
        this.personWorkingHours = personWorkingHours;
        this.staffOvertimeCosts = staffOvertimeCosts;
        this.manageServiceCharges = manageServiceCharges;
        this.toolWearCosts = toolWearCosts;
        this.materialAccessoriesPrices = materialAccessoriesPrices;
        this.sumProducePrices = sumProducePrices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBiddingId() {
        return biddingId;
    }

    public void setBiddingId(int biddingId) {
        this.biddingId = biddingId;
    }

    public double getPersonWorkingHours() {
        return personWorkingHours;
    }

    public void setPersonWorkingHours(double personWorkingHours) {
        this.personWorkingHours = personWorkingHours;
    }

    public double getStaffOvertimeCosts() {
        return staffOvertimeCosts;
    }

    public void setStaffOvertimeCosts(double staffOvertimeCosts) {
        this.staffOvertimeCosts = staffOvertimeCosts;
    }

    public double getManageServiceCharges() {
        return manageServiceCharges;
    }

    public void setManageServiceCharges(double manageServiceCharges) {
        this.manageServiceCharges = manageServiceCharges;
    }

    public double getToolWearCosts() {
        return toolWearCosts;
    }

    public void setToolWearCosts(double toolWearCosts) {
        this.toolWearCosts = toolWearCosts;
    }

    public double getMaterialAccessoriesPrices() {
        return materialAccessoriesPrices;
    }

    public void setMaterialAccessoriesPrices(double materialAccessoriesPrices) {
        this.materialAccessoriesPrices = materialAccessoriesPrices;
    }

    public double getSumProducePrices() {
        return sumProducePrices;
    }

    public void setSumProducePrices(double sumProducePrices) {
        this.sumProducePrices = sumProducePrices;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BiddingProduceCost{");
        sb.append("id=").append(id);
        sb.append(", biddingId=").append(biddingId);
        sb.append(", personWorkingHours=").append(personWorkingHours);
        sb.append(", staffOvertimeCosts=").append(staffOvertimeCosts);
        sb.append(", manageServiceCharges=").append(manageServiceCharges);
        sb.append(", toolWearCosts=").append(toolWearCosts);
        sb.append(", materialAccessoriesPrices=").append(materialAccessoriesPrices);
        sb.append(", sumProducePrices=").append(sumProducePrices);
        sb.append('}');
        return sb.toString();
    }
}
