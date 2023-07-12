package com.lucas.receipt.processor.repositories.entities;

import javax.persistence.*;

@Entity
public class ReceiptDataObject {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    @Column(name = "POINTS")
    long points;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }
}
