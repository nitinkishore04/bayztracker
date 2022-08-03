package com.server.bayztracker.service;

import com.server.bayztracker.dao.AlertRepository;
import com.server.bayztracker.dao.CurrencyRepository;
import com.server.bayztracker.entity.Alert;
import com.server.bayztracker.entity.Currency;
import com.server.bayztracker.entity.Status;
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

    @Override
    public Iterable<Alert> getAllAlertForACoin(String coinSymbol) {
        return alertRepository.findAllByCurrencyAndCreatedBy(coinSymbol, Util.getUserName());
    }

    @Override
    public Alert getAlertById(Integer id) {
        Optional<Alert> res = alertRepository.findById(id);
        if (res.isPresent()) {
            return res.get();
        } else {
            throw new RuntimeException("Id = " + id + " is invalid");
        }
    }

    @Override
    public Alert deleteAlertIfNotTriggered(Integer id) {
        Alert alert = getAlertById(id);
        if(alert.getStatus() == Status.NEW) {
            alert.setStatus(Status.CANCELLED);
        } else {
            throw new RuntimeException("Alert cannot be changed from alert = " + alert.getStatus() + " state.");
        }
        return alertRepository.save(alert);
    }

    @Override
    public Alert acknowledgeAlert(Integer id) {
        Alert alert = getAlertById(id);
        if(alert.getStatus() == Status.NEW) {
            alert.setStatus(Status.ACKED);
        } else {
            throw new RuntimeException("Alert cannot be changed from alert = " + alert.getStatus() + " state.");
        }
        return alertRepository.save(alert);
    }

}
