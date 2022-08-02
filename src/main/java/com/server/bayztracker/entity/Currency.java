package com.server.bayztracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.server.bayztracker.exception.UnsupportedCurrencyCreationException;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {

    @Id
    private String name;

    @Column(unique = true)
    private String  symbol;

    private float currentPrice;

    private String createdTime = new Date().toString();

    private Boolean enabled;

    private Boolean triggered = false;

    //To Check Whether Coin is Active or Not
    private Boolean active = true;

    public void validate() {
        List<String> forbiddenCurrency = Arrays.asList("ETH", "LTC", "ZKN", "MRD", "LPR", "GBZ");

        if (forbiddenCurrency.stream().anyMatch(symbol::equalsIgnoreCase)) {
            throw new UnsupportedCurrencyCreationException(symbol + " Currency isn't Supported");
        }
    }
}
