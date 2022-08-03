package com.server.bayztracker.dao;

import com.server.bayztracker.entity.Alert;
import org.springframework.data.repository.CrudRepository;

public interface AlertRepository  extends CrudRepository<Alert, Integer> {
}
