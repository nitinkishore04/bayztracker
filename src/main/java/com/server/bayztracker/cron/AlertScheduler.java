package com.server.bayztracker.cron;

import com.server.bayztracker.dao.AlertRepository;
import com.server.bayztracker.dao.CurrencyRepository;
import com.server.bayztracker.entity.Alert;
import com.server.bayztracker.entity.Currency;
import com.server.bayztracker.entity.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AlertScheduler {

    Logger logger = LoggerFactory.getLogger(AlertScheduler.class);

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Scheduled(cron = "0 0/30 * 1/1 * ?")
    public void sendAlert() {

        Collection<Alert> alerts = alertRepository.findAllByStatus(Status.NEW);
        alerts.forEach(alert -> {
            Currency currency = currencyRepository.findBySymbolAndActive(alert.getCurrency(), Boolean.TRUE).get();
            if (currency.getCurrentPrice() == alert.getTargetPrice()) {
                logger.info("Target Price {} Reached", alert.getTargetPrice());
            }
        });

    }
}
