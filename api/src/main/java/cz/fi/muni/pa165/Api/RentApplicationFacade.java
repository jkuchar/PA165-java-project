package cz.fi.muni.pa165.Api;

import cz.fi.muni.pa165.Api.DTO.RentApplicationDTO;
import cz.fi.muni.pa165.Model.Entity.RentApplication;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * This file is part of PA165 school project.
 */
public interface RentApplicationFacade {

    List<RentApplicationDTO> findAll();

    RentApplicationDTO findById(UUID id);

    List<RentApplicationDTO> findByCar(UUID carId);

    List<RentApplicationDTO> findByUser(UUID userId);

    List<RentApplicationDTO> getRecordsCreatedBetween(Date from, Date to);

    UUID create(RentApplicationDTO r);

    void delete(UUID id);

}
