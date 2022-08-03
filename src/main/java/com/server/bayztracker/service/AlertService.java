package com.server.bayztracker.service;

import com.server.bayztracker.entity.Alert;

public interface AlertService {

    Alert createAlert(Alert alert);

    Iterable<Alert> getAllAlertSetByUser();

    Iterable<Alert> getAllAlertForACoin(String coinSymbol);

    Alert getAlertById(Integer id);

    Alert deleteAlertIfNotTriggered(Integer id);

    Alert acknowledgeAlert(Integer id);
}
