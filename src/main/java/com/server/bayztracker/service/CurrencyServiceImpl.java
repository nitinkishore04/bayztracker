package com.server.bayztracker.service;

import com.server.bayztracker.dao.CurrencyRepository;
import com.server.bayztracker.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Currency addNewCurrency(Currency currency) {
        currency.validate();
        return currencyRepository.save(currency);
    }

    @Override
    public Iterable<Currency> fetchAllCurrencyDetail() {
        return currencyRepository.findAll();
    }
}
