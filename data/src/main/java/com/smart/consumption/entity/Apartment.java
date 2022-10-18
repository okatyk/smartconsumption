package com.smart.consumption.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "APARTMENTS")
@Data
public class Apartment {
    @Id
    private long id;
    @Column(name = "FLOOR")
    String measuring;
    @Column(name = "NUMBER")
    String unitOfMeasure;
}
