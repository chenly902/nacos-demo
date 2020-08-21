package com.easybii.nacos.nacosprivider.nacos.VO;

/**
 * Copyright (C), 2020,
 * Author: linyu902
 * Date: 2020/7/28 9:48
 * FileName: ExcelVO
 */
public class ExcelVO {
    private Long id;

    private String name;

    private String sep;

    private String dw;

    private String priceUrl;

    private String taxPrice;

    private String note;

    private String noTaxPrice;

    @Override
    public String toString() {
        return "ExcelVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sep='" + sep + '\'' +
                ", dw='" + dw + '\'' +
                ", priceUrl='" + priceUrl + '\'' +
                ", taxPrice='" + taxPrice + '\'' +
                ", note='" + note + '\'' +
                ", noTaxPrice='" + noTaxPrice + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSep() {
        return sep;
    }

    public void setSep(String sep) {
        this.sep = sep;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getPriceUrl() {
        return priceUrl;
    }

    public void setPriceUrl(String priceUrl) {
        this.priceUrl = priceUrl;
    }

    public String getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(String taxPrice) {
        this.taxPrice = taxPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoTaxPrice() {
        return noTaxPrice;
    }

    public void setNoTaxPrice(String noTaxPrice) {
        this.noTaxPrice = noTaxPrice;
    }
}
