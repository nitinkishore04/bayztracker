package com.server.bayztracker.dao;

import com.server.bayztracker.entity.Currency;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface CurrencyRepository extends CrudRepository<Currency, String> {
    Optional<Currency> findBySymbol(String symbol);

    Optional<Currency> findBySymbolAndActive(String symbol, Boolean active);

    Collection<Currency> findAllByActive(Boolean active);
}
