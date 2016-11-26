/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.ApplicationRejectedRecordDTO;
import cz.fi.muni.pa165.api.dto.RentApplicationDTO;
import cz.fi.muni.pa165.api.facade.ApplicationRejectedRecordFacade;
import cz.fi.muni.pa165.api.facade.RentApplicationFacade;
import cz.fi.muni.pa165.model.entity.ApplicationRejectedRecord;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.model.entity.RentApplication;
import cz.fi.muni.pa165.model.entity.User;
import cz.fi.muni.pa165.service.ApplicationRejectedRecordService;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.CarService;
import cz.fi.muni.pa165.service.RentApplicationService;
import cz.fi.muni.pa165.service.UserService;
import cz.fi.muni.pa165.service.config.BeanMappingConfiguration;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author charlliz
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
public class ApplicationRejectedRecordFacadeTest extends AbstractTestNGSpringContextTests  {

    private ApplicationRejectedRecordFacade applicationFacade;
    
    private ApplicationRejectedRecordService applicationService;
    
    private UserService userService;
    
    private CarService carService;
    
    private RentApplicationService rentService;
    
    private final UUID someUUID = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");

    @Inject
    private BeanMappingService beanMappingService;

    @BeforeMethod
    private void init() {
        applicationFacade = new ApplicationRejectedRecordFacadeImpl(
                beanMappingService,
                applicationService = Mockito.mock(ApplicationRejectedRecordService.class),
                userService = Mockito.mock(UserService.class),
                carService = Mockito.mock(CarService.class),
                rentService = Mockito.mock(RentApplicationService.class)
        );
    }

    @Test
    public void testFindAll() throws Exception {
        List<ApplicationRejectedRecord> source = new LinkedList<>();
        source.add(Mockito.mock(ApplicationRejectedRecord.class));

        when(applicationService.findAllRecords()).thenReturn(source);
        final List<ApplicationRejectedRecordDTO> collection = applicationFacade.findAllRecords();

        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new ApplicationRejectedRecordDTO());
    }

    @Test
    public void testFindById() throws Exception {
        ApplicationRejectedRecord entity = Mockito.mock(ApplicationRejectedRecord.class);
        when(applicationService.findRecordById(someUUID)).thenReturn(entity);

        final ApplicationRejectedRecordDTO dto = applicationFacade.findRecordById(someUUID);

        Assert.assertEquals(dto, new ApplicationRejectedRecordDTO());
    }

    @Test
    public void testFindByCar() throws Exception {
        List<ApplicationRejectedRecord> source = new LinkedList<>();
        source.add(Mockito.mock(ApplicationRejectedRecord.class));
        Car c = carService.findCarById(someUUID);
        
        when(applicationService.getAllRecordsByCar(c)).thenReturn(source);

        final List<ApplicationRejectedRecordDTO> collection = applicationFacade.getAllRecordsByCar(someUUID);

        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new ApplicationRejectedRecordDTO());
    }

    @Test
    public void testFindByUser() throws Exception {

        List<ApplicationRejectedRecord> source = new LinkedList<>();
        source.add(Mockito.mock(ApplicationRejectedRecord.class));

        User u  = userService.findById(someUUID);
        when(applicationService.getAllRecordsByUser(u)).thenReturn(source);

        final List<ApplicationRejectedRecordDTO> collection = applicationFacade.getAllRecordsByUser(someUUID);

        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new ApplicationRejectedRecordDTO());
    }

    @Test
    public void testGetRecordsCreatedBetween() throws Exception {
        List<ApplicationRejectedRecord> source = new LinkedList<>();
        source.add(Mockito.mock(ApplicationRejectedRecord.class));

        Date from = new Date("01/01/2016");
        Date to = new Date("01/02/2016");

        when(applicationService.getAllRecordsCreatedBetween(from, to)).thenReturn(source);

        final List<ApplicationRejectedRecordDTO> collection = applicationFacade.getAllRecordsCreatedBetween(from, to);

        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new ApplicationRejectedRecordDTO());
    }


    @Test(enabled = false)
    public void testCreate() throws Exception {

        ApplicationRejectedRecordDTO dto = Mockito.mock(ApplicationRejectedRecordDTO.class);
        ApplicationRejectedRecord entity = Mockito.mock(ApplicationRejectedRecord.class);
        when(entity.getId()).thenReturn(someUUID);

        UUID id = applicationFacade.create(dto);

        verify(applicationService, times(1)).create(entity);
        Assert.assertEquals(id, someUUID);

    }
}
