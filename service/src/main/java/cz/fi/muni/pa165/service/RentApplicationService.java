package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.model.entity.RentApplication;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * This file is part of PA165 school project.
 */
public interface RentApplicationService {
    List<RentApplication> findAll();

    RentApplication findById(UUID id);

    List<RentApplication> findByCar(UUID carId);

    List<RentApplication> findByUser(UUID userId);

    List<RentApplication> getRecordsCreatedBetween(Date from, Date to);

    void create(RentApplication r);

    void delete(RentApplication r);

    void delete(UUID id);
}
