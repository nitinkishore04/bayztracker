package com.server.bayztracker.dao;

import com.server.bayztracker.entity.Alert;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface AlertRepository  extends CrudRepository<Alert, Integer> {
    Collection<Alert> findAllByCreatedBy(String createdBy);
}
