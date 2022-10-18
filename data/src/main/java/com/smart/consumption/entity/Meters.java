package com.smart.consumption.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "METERS")
@Data
public class Meters {
    @Id
    @Column(name = "NAME")
    String name;
    @Column(name = "APARTMENT")
    long apartment;
    @Column(name = "LOCATION")
    String location;
}
