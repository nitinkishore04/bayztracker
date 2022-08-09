package com.server.bayztracker.service;

import com.server.bayztracker.dao.CurrencyRepository;
import com.server.bayztracker.entity.Currency;
import com.server.bayztracker.exception.InvalidActionException;
import com.server.bayztracker.exception.InvalidCoinException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

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
        return currencyRepository.findAllByActive(Boolean.TRUE);
    }

    @Override
    public Currency getByName(String name) {
        Optional<Currency> result = currencyRepository.findBySymbolAndActive(name, Boolean.TRUE);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new InvalidCoinException("There is no coin with symbol = " + name);
        }
    }

    @Override
    public Currency deleteCurrencyBySymbol(String symbolName) {
        Optional<Currency> result = currencyRepository.findBySymbolAndActive(symbolName, Boolean.TRUE);
        if (result.isPresent()) {
            Currency response = result.get();
            response.setActive(Boolean.FALSE);
            return currencyRepository.save(response);
        } else {
            throw new InvalidCoinException("There is no coin with symbol = " + symbolName);
        }
    }

    @Override
    public Iterable<Currency> getArchivedCoin() {
        return currencyRepository.findAllByActive(Boolean.FALSE);
    }

    @Override
    public Currency updateExistingCoin(String name, Currency currency) {
        Optional<Currency> res = currencyRepository.findById(name);
        if (res.isPresent()) {
            Currency queryResult = res.get();
            queryResult.setSymbol(currency.getSymbol());
            queryResult.setCurrentPrice(currency.getCurrentPrice());
            return currencyRepository.save(queryResult);
        }
        throw new InvalidActionException("Please pass valid Currency name.");
    }

}
