package com.server.bayztracker.dao;

import com.server.bayztracker.entity.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, String> {
}
