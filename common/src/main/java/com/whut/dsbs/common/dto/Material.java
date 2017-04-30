package com.whut.dsbs.common.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 制作产品所需要的材料
 *
 * Created by zyb on 2017-04-30.
 */
@Entity
@Table(name = "material")
public class Material implements Serializable{

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "material_name")
    private String materialName;

    @Column(name = "material_description")
    private String materialDescription;

    @Column(name = "material_quoted_price")
    private Double materialQuotedPrice;

    @Column(name = "material_price")
    private Double materialPrice;

    @Column(name = "material_number")
    private Double materialNumber;

    public Material() {
    }

    public Material(Integer id, String materialName, String materialDescription, Double materialQuotedPrice, Double materialPrice, Double materialNumber) {
        this.id = id;
        this.materialName = materialName;
        this.materialDescription = materialDescription;
        this.materialQuotedPrice = materialQuotedPrice;
        this.materialPrice = materialPrice;
        this.materialNumber = materialNumber;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Material{");
        sb.append("id=").append(id);
        sb.append(", materialName='").append(materialName).append('\'');
        sb.append(", materialDescription='").append(materialDescription).append('\'');
        sb.append(", materialQuotedPrice=").append(materialQuotedPrice);
        sb.append(", materialPrice=").append(materialPrice);
        sb.append(", materialNumber=").append(materialNumber);
        sb.append('}');
        return sb.toString();
    }
}
