package com.server.bayztracker.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Currency {

    @Id
    private String name;

    private String  symbol;

    private String currentPrice;

    private String createdTime;

    private Boolean enabled;

    private Boolean triggered;
}
