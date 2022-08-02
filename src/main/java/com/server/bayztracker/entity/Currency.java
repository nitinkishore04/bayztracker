package com.server.bayztracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.bayztracker.exception.UnsupportedCurrencyCreationException;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class Currency {

    @Id
    private String name;

    private String  symbol;

    private float currentPrice;

    @JsonIgnore
    private String createdTime = new Date().toString();

    private Boolean enabled;

    @JsonIgnore
    private Boolean triggered = false;

    public void validate() {
        List<String> forbiddenCurrency = Arrays.asList("ETH", "LTC", "ZKN", "MRD", "LPR", "GBZ");

        if (forbiddenCurrency.stream().anyMatch(symbol::equalsIgnoreCase)) {
            throw new UnsupportedCurrencyCreationException(symbol + " Currency isn't Supported");
        }
    }
}
