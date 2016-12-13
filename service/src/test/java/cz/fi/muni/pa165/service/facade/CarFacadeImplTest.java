/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.CarDTO;
import cz.fi.muni.pa165.api.facade.CarFacade;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.CarService;
import cz.fi.muni.pa165.service.config.BeanMappingConfiguration;
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
public class CarFacadeTest extends AbstractTestNGSpringContextTests {
    
    private CarFacade carFacade;
    
    private CarService carService;

    private final UUID someUUID = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");

    @Inject
    private BeanMappingService beanMappingService;

    @BeforeMethod
    private void init() {
        carFacade = new CarFacadeImpl(
                carService = Mockito.mock(CarService.class),
                beanMappingService);
    }

    @Test
    public void testFindAll()  {
        List<Car> source = new LinkedList<>();
        source.add(Mockito.mock(Car.class));

        when(carService.findAllCars()).thenReturn(source);
        final List<CarDTO> collection = carFacade.findAllCars();

        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new CarDTO());
    }

    @Test
    public void testFindById() {
        Car entity = Mockito.mock(Car.class);
        when(carService.findCarById(someUUID)).thenReturn(entity);

        final CarDTO dto = carFacade.findCarById(someUUID);

        Assert.assertEquals(dto, new CarDTO());
    }

    @Test(enabled = false)
    public void testCreate() throws Exception {

        CarDTO dto = Mockito.mock(CarDTO.class);
        Car entity = Mockito.mock(Car.class);
        when(entity.getId()).thenReturn(someUUID);

        UUID id = carFacade.createCar(dto);

        verify(carService, times(1)).createCar(entity);
        Assert.assertEquals(id, someUUID);

    }        
}
