
package com.example.cabbooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fromLocation;

    @Column(nullable = false)
    private String toLocation;

    @Column(nullable = false)
    private Integer typeOfCab;

    @Column(nullable = false)
    private Integer distanceKm;

    @Column(nullable = false)
    private Double fare;

    public Booking() {}

    public Booking(String fromLocation, String toLocation, Integer typeOfCab, Integer distanceKm, Double fare) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.typeOfCab = typeOfCab;
        this.distanceKm = distanceKm;
        this.fare = fare;
    }

    public Long getId()                     { return id; }
    public void setId(Long id)              { this.id = id; }
    public String getFromLocation()         { return fromLocation; }
    public void setFromLocation(String f)   { this.fromLocation = f; }
    public String getToLocation()           { return toLocation; }
    public void setToLocation(String t)     { this.toLocation = t; }
    public Integer getTypeOfCab()           { return typeOfCab; }
    public void setTypeOfCab(Integer t)     { this.typeOfCab = t; }
    public Integer getDistanceKm()          { return distanceKm; }
    public void setDistanceKm(Integer d)    { this.distanceKm = d; }
    public Double getFare()                 { return fare; }
    public void setFare(Double fare)        { this.fare = fare; }

    @Override
    public String toString() {
        return "Booking[id=" + id + ", from=" + fromLocation + ", to=" + toLocation
                + ", cab=" + typeOfCab + ", dist=" + distanceKm + "km, fare=R" + fare + "]";
    }
}
