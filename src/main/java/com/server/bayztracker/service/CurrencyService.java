package com.server.bayztracker.service;

import com.server.bayztracker.entity.Currency;

public interface CurrencyService {

    Currency addNewCurrency(Currency currency);

    Iterable<Currency> fetchAllCurrencyDetail();
}
