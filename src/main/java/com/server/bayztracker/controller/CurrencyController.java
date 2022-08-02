package com.server.bayztracker.controller;

import com.server.bayztracker.entity.Currency;
import com.server.bayztracker.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Iterable<Currency> addNewCurrency() {
        return currencyService.fetchAllCurrencyDetail();
    }

}
