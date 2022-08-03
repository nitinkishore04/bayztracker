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

    @GetMapping("/get/coin")
    @PreAuthorize("hasAuthority('USER')")
    public Iterable<Alert> getAllAlertForACoin(@RequestParam String coinSymbol) {
        return alertService.getAllAlertForACoin(coinSymbol);
    }

    @GetMapping("/get/trigger")
    @PreAuthorize("hasAuthority('USER')")
    public Alert getAlertById(@RequestParam Integer id) {
        return alertService.getAlertById(id);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('USER')")
    public Alert deleteTrigger(@RequestParam Integer id) {
        return alertService.deleteAlertIfNotTriggered(id);
    }

}
