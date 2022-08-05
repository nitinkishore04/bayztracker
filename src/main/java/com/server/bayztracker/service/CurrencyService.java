package com.server.bayztracker.service;

import com.server.bayztracker.entity.Currency;

public interface CurrencyService {

    Currency addNewCurrency(Currency currency);

    Iterable<Currency> fetchAllCurrencyDetail();

    Currency getByName(String name);

    Currency deleteCurrencyBySymbol(String symbolName);

    Iterable<Currency> getArchivedCoin();

    Currency updateExistingCoin(String name, Currency currency);
}
