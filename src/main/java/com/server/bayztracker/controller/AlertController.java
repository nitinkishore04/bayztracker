package com.server.bayztracker.controller;

import com.server.bayztracker.entity.Alert;
import com.server.bayztracker.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping("/set")
    @PreAuthorize("hasAuthority('USER')")
    public Alert addNewCurrency(@RequestBody Alert alert) {
        return alertService.createAlert(alert);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('USER')")
    public Iterable<Alert> getAllAlert() {
        return alertService.getAllAlertSetByUser();
    }

}
