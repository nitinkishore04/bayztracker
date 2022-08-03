package com.server.bayztracker.service;

import com.server.bayztracker.dao.AlertRepository;
import com.server.bayztracker.entity.Alert;
import com.server.bayztracker.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Override
    public Alert createAlert(Alert alert) {
        alert.setCreatedBy(Util.getUserName());
        return alertRepository.save(alert);
    }
}
