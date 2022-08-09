package com.server.bayztracker.dao;

import com.server.bayztracker.entity.Alert;
import com.server.bayztracker.entity.Status;
import org.springframework.data.repository.CrudRepository;
import java.util.Collection;

public interface AlertRepository  extends CrudRepository<Alert, Integer> {
    Collection<Alert> findAllByCreatedBy(String createdBy);

    Collection<Alert> findAllByCurrencyAndCreatedBy(String currency, String createdBy);

    Collection<Alert> findAllByStatus(Status status);

    Boolean existsByCurrencyAndTargetPriceAndCreatedByAndStatus(String currency, Float targetPrice, String createdBy, Status status);
}
