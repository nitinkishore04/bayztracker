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
    public Iterable<Currency> getListOfAllCurrency() {
        return currencyService.fetchAllCurrencyDetail();
    }

    @GetMapping("/get/name")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Currency getCurrencyBySymbol(@RequestParam String symbolName) {
        return currencyService.getByName(symbolName);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Currency deleteCurrencyBySymbol(@RequestParam String symbolName) {
        return currencyService.deleteCurrencyBySymbol(symbolName);
    }

    @GetMapping("/archived")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Iterable<Currency> getArchivedCoin() {
        return currencyService.getArchivedCoin();
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Currency updateCurrency(@RequestParam String name, @RequestBody Currency currency) {
        return currencyService.updateExistingCoin(name, currency);
    }
}
