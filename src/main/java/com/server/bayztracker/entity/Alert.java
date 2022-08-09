package com.server.bayztracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String currency;

    private float targetPrice;

    private String createdAt = new Date().toString();

    private Status status = Status.NEW;

    private String createdBy;

}
