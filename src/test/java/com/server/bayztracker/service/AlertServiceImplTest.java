package com.server.bayztracker.service;

import com.server.bayztracker.dao.AlertRepository;
import com.server.bayztracker.dao.CurrencyRepository;
import com.server.bayztracker.entity.Alert;
import com.server.bayztracker.entity.Currency;
import com.server.bayztracker.entity.Status;
import com.server.bayztracker.util.Util;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class AlertServiceImplTest {

    @Mock
    private AlertRepository alertRepository;

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private AlertServiceImpl target;

    @Test
    void whenAlertForCoinIsCreated_ItShouldHappenSuccessfully() {
        Mockito.when(currencyRepository.findBySymbolAndActive(anyString(), anyBoolean())).thenReturn(Optional.of(getCurrency()));

        try (MockedStatic<Util> utilities = Mockito.mockStatic(Util.class)) {
            utilities.when(Util::getUserName).thenReturn("user");
            target.createAlert(getAlert());
            Mockito.verify(alertRepository, Mockito.times(1)).save(any(Alert.class));
        }
    }

    @Test
    void whenAlertWithSameDetailIsCreatedTwice_ItShouldThrowException() {
        Mockito.when(alertRepository.existsByCurrencyAndTargetPriceAndCreatedByAndStatus(anyString(), anyFloat(), anyString(), any(Status.class))).thenReturn(Boolean.TRUE);
        try (MockedStatic<Util> utilities = Mockito.mockStatic(Util.class)) {
            utilities.when(Util::getUserName).thenReturn("user");
            assertThatThrownBy(() -> target.createAlert(getAlert()))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Another alert for same detail already exist");
        }
    }

    @Test
    void whenAlertForUnknownCoinIsCreated_ItShouldThrowException() {
        Mockito.when(currencyRepository.findBySymbolAndActive(anyString(), anyBoolean())).thenReturn(Optional.empty());

        try (MockedStatic<Util> utilities = Mockito.mockStatic(Util.class)) {
            utilities.when(Util::getUserName).thenReturn("user");
            assertThatThrownBy(() -> target.createAlert(getAlert()))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Invalid Coin Symbol");
            Mockito.verify(alertRepository, Mockito.times(0)).save(any(Alert.class));
            Mockito.verify(currencyRepository, Mockito.times(1)).findBySymbolAndActive(anyString(), anyBoolean());
        }
    }

    @Test
    void whenWeRetrieveListOfAllAlert_ThenItShouldHappenSuccessfully() {
        try (MockedStatic<Util> utilities = Mockito.mockStatic(Util.class)) {
            utilities.when(Util::getUserName).thenReturn("user");
            target.getAllAlertSetByUser();
            Mockito.verify(alertRepository, Mockito.times(1)).findAllByCreatedBy(anyString());
        }
    }

    @Test
    void whenWeRetrieveListOfAllAlertForACoin_ThenItShouldHappenSuccessfully() {
        try (MockedStatic<Util> utilities = Mockito.mockStatic(Util.class)) {
            utilities.when(Util::getUserName).thenReturn("user");
            target.getAllAlertForACoin("TEST");
            Mockito.verify(alertRepository, Mockito.times(1)).findAllByCurrencyAndCreatedBy(anyString(), anyString());
        }
    }

    @Test
    void whenSpecificCoinAlertIsRetrieved_ThenItShouldHappenSuccessfully() {
        Mockito.when(alertRepository.findById(anyInt())).thenReturn(Optional.of(getAlert()));
        target.getAlertById(1);
        Mockito.verify(alertRepository, Mockito.times(1)).findById(anyInt());
    }

    @Test
    void whenCoinAlertIsRetrievedWithWrongId_ThenItShouldThrowException() {
        Mockito.when(alertRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> target.getAlertById(1))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Id = 1 is invalid");
        Mockito.verify(alertRepository, Mockito.times(1)).findById(anyInt());
    }

    @Test
    void whenUserDeleteUnTriggeredAlert_ThenItShouldHappenSuccessfully() {
        Mockito.when(alertRepository.findById(anyInt())).thenReturn(Optional.of(getAlert()));
        target.deleteAlertIfNotTriggered(1);
        Mockito.verify(alertRepository, Mockito.times(1)).findById(anyInt());
        Mockito.verify(alertRepository, Mockito.times(1)).save(any(Alert.class));

    }

    @Test
    void whenUserDeleteTriggeredAlert_ThenItShouldThrowException() {
        Alert req = getAlert();
        req.setStatus(Status.TRIGGERRED);
        Mockito.when(alertRepository.findById(anyInt())).thenReturn(Optional.of(req));
        assertThatThrownBy(() -> target.deleteAlertIfNotTriggered(1))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Alert cannot be changed from alert = TRIGGERRED state.");
        Mockito.verify(alertRepository, Mockito.times(1)).findById(anyInt());
        Mockito.verify(alertRepository, Mockito.times(0)).save(any(Alert.class));
    }

    @Test
    void whenUserAcknowledgeAlert_ThenItShouldHappenSuccessfully() {
        Mockito.when(alertRepository.findById(anyInt())).thenReturn(Optional.of(getAlert()));
        target.acknowledgeAlert(1);
        Mockito.verify(alertRepository, Mockito.times(1)).findById(anyInt());
        Mockito.verify(alertRepository, Mockito.times(1)).save(any(Alert.class));

    }

    @Test
    void whenUserTryToAcknowledgeTriggeredAlert_ThenItShouldThrowException() {
        Alert req = getAlert();
        req.setStatus(Status.ACKED);
        Mockito.when(alertRepository.findById(anyInt())).thenReturn(Optional.of(req));
        assertThatThrownBy(() -> target.acknowledgeAlert(1))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Alert cannot be changed from alert = ACKED state.");
        Mockito.verify(alertRepository, Mockito.times(1)).findById(anyInt());
        Mockito.verify(alertRepository, Mockito.times(0)).save(any(Alert.class));
    }

    private static Currency getCurrency() {
        Currency req = new Currency();
        req.setSymbol("ABC");
        req.setActive(true);
        req.setName("TEST");
        req.setEnabled(true);
        req.setCreatedTime(new Date().toString());
        return req;
    }

    private static Alert getAlert() {
        Alert req = new Alert();
        req.setCurrency("ABC");
        req.setTargetPrice(12.0F);
        req.setStatus(Status.NEW);
        req.setCreatedBy("user");
        return req;
    }
}