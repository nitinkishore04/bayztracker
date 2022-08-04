package com.server.bayztracker.service;

import com.server.bayztracker.dao.CurrencyRepository;
import com.server.bayztracker.entity.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyServiceImpl target;

    @Test
    void whenNewCoinIsAdded_ThenItShouldBeAddedSuccessFully() {
        target.addNewCurrency(getCurrency());
        Mockito.verify(currencyRepository, Mockito.times(1)).save(any(Currency.class));
    }

    @Test
    void whenNewCoinIsAddedWithForbiddenName_ThenItShouldThrowException() {
        Currency req = getCurrency();
        req.setSymbol("ETH");
        assertThatThrownBy(() -> target.addNewCurrency(req))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Currency with symbol = ETH isn't Supported");
        Mockito.verify(currencyRepository, Mockito.times(0)).save(any(Currency.class));
    }

    @Test
    void whenWeRetrieveListOfAllCoin_ThenItShouldHappenSuccessfully() {
        target.fetchAllCurrencyDetail();
        Mockito.verify(currencyRepository, Mockito.times(1)).findAllByActive(anyBoolean());
    }

    @Test
    void whenCoinIsRetrievedByName_ThenItShouldHappenSuccessfully() {
        Mockito.when(currencyRepository.findBySymbolAndActive(anyString(), anyBoolean())).thenReturn(Optional.of(getCurrency()));
        target.getByName("TEST");
        Mockito.verify(currencyRepository, Mockito.times(1)).findBySymbolAndActive(anyString(), anyBoolean());
    }

    @Test
    void whenCoinIsRetrievedByName_ThenItShouldThrowException() {
        Mockito.when(currencyRepository.findBySymbolAndActive(anyString(), anyBoolean())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> target.getByName("TEST"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("There is no coin with symbol = TEST");
        Mockito.verify(currencyRepository, Mockito.times(1)).findBySymbolAndActive(anyString(), anyBoolean());
    }

    @Test
    void whenCoinIsDeleted_ThenItShouldHappenSuccessfully() {
        Mockito.when(currencyRepository.findBySymbolAndActive(anyString(), anyBoolean())).thenReturn(Optional.of(getCurrency()));
        target.deleteCurrencyBySymbol("TEST");
        Mockito.verify(currencyRepository, Mockito.times(1)).findBySymbolAndActive(anyString(), anyBoolean());
        Mockito.verify(currencyRepository, Mockito.times(1)).save(any(Currency.class));
    }

    @Test
    void whenUnknownCoinIsDeleted_ThenItShouldThrowException() {
        Mockito.when(currencyRepository.findBySymbolAndActive(anyString(), anyBoolean())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> target.deleteCurrencyBySymbol("TEST"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("There is no coin with symbol = TEST");
        Mockito.verify(currencyRepository, Mockito.times(1)).findBySymbolAndActive(anyString(), anyBoolean());
        Mockito.verify(currencyRepository, Mockito.never()).save(any(Currency.class));
    }

    @Test
    void whenWeRetrieveListOfArchivedCoin_ThenItShouldHappenSuccessfully() {
        target.getArchivedCoin();
        Mockito.verify(currencyRepository, Mockito.times(1)).findAllByActive(anyBoolean());
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

}