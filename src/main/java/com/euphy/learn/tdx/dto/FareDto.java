package com.euphy.learn.tdx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FareDto {
    private Integer ticketType;
    private Integer fareClass;
    private Integer cabinClass;
    private Integer price;

    public Integer getTicketType() {
        return ticketType;
    }

    @JsonProperty("TicketType")
    public void setTicketType(Integer ticketType) {
        this.ticketType = ticketType;
    }

    public Integer getFareClass() {
        return fareClass;
    }

    @JsonProperty("FareClass")
    public void setFareClass(Integer fareClass) {
        this.fareClass = fareClass;
    }

    public Integer getCabinClass() {
        return cabinClass;
    }

    @JsonProperty("CabinClass")
    public void setCabinClass(Integer cabinClass) {
        this.cabinClass = cabinClass;
    }

    public Integer getPrice() {
        return price;
    }

    @JsonProperty("Price")
    public void setPrice(Integer price) {
        this.price = price;
    }
}
