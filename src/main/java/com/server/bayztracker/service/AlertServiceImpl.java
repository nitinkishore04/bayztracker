package com.server.bayztracker.service;

import com.server.bayztracker.dao.AlertRepository;
import com.server.bayztracker.dao.CurrencyRepository;
import com.server.bayztracker.entity.Alert;
import com.server.bayztracker.entity.Currency;
import com.server.bayztracker.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Alert createAlert(Alert alert) {
        validateCoin(alert);
        alert.setCreatedBy(Util.getUserName());
        return alertRepository.save(alert);
    }

    private void validateCoin(Alert alert) {
        Optional<Currency> coin = currencyRepository.findBySymbol(alert.getCurrency());
        if(!coin.isPresent()) {
            //TODO: create not valid coin exception
            throw new RuntimeException("Invalid Coin Symbol");
        }
    }

    @Override
    public Iterable<Alert> getAllAlertSetByUser() {
        return alertRepository.findAllByCreatedBy(Util.getUserName());
    }
}
