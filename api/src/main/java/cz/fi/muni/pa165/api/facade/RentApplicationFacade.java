package cz.fi.muni.pa165.api.facade;


import cz.fi.muni.pa165.api.dto.RentApplicationDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *  @author jkuchar
 */
public interface RentApplicationFacade {

    List<RentApplicationDTO> findAll();

    RentApplicationDTO findById(UUID id);

    List<RentApplicationDTO> findByCar(UUID carId);

    List<RentApplicationDTO> findByUser(UUID userId);

    List<RentApplicationDTO> getRecordsCreatedBetween(Date from, Date to);

    UUID create(RentApplicationDTO r);
}
