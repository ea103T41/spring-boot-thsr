package com.euphy.learn.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "fare")
public class Fare implements Serializable {

    private Integer id;
    private Integer ticketType;
    private Integer fareClass;
    private Integer cabinClass;
    private Integer price;
    private RailFare railFare;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    public Integer getTicketType() {
        return ticketType;
    }

    public void setTicketType(Integer ticketType) {
        this.ticketType = ticketType;
    }

    @Column
    public Integer getFareClass() {
        return fareClass;
    }

    public void setFareClass(Integer fareClass) {
        this.fareClass = fareClass;
    }

    @Column
    public Integer getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(Integer cabinClass) {
        this.cabinClass = cabinClass;
    }

    @Column
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "rail_fare_id")
    public RailFare getRailFare() {
        return railFare;
    }

    public void setRailFare(RailFare railFare) {
        this.railFare = railFare;
    }
}
