package com.server.bayztracker.service;

import com.server.bayztracker.entity.Alert;

public interface AlertService {

    Alert createAlert(Alert alert);

    Iterable<Alert> getAllAlertSetByUser();
}
