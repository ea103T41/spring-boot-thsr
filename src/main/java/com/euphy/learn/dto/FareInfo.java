package com.euphy.learn.dto;

public class FareInfo {

    private String fareName;
    private Integer adultPrice;
    private Integer concessionPrice;
    private Integer groupPrice;

    public FareInfo(FareType fareType) {
        this.fareName = fareType.getFareTypeName();
    }

    public FareInfo() {
    }

    public String getFareName() {
        return fareName;
    }

    public void setFareName(String fareName) {
        this.fareName = fareName;
    }

    public Integer getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(Integer adultPrice) {
        this.adultPrice = adultPrice;
    }

    public Integer getConcessionPrice() {
        return concessionPrice;
    }

    public void setConcessionPrice(Integer concessionPrice) {
        this.concessionPrice = concessionPrice;
    }

    public Integer getGroupPrice() {
        return groupPrice;
    }

    public void setGroupPrice(Integer groupPrice) {
        this.groupPrice = groupPrice;
    }
}
