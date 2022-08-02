package com.server.bayztracker.controller;

import com.server.bayztracker.entity.Currency;
import com.server.bayztracker.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Currency addNewCurrency(@RequestBody Currency currency) {
        return currencyService.addNewCurrency(currency);
    }
}
