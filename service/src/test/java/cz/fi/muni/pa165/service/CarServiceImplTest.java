/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.enums.CarState;
import cz.fi.muni.pa165.model.dao.CarDao;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.service.config.BeanMappingConfiguration;
import java.util.Date;
import org.hibernate.service.spi.ServiceException;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author charlliz
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
public class CarServiceTest extends AbstractTestNGSpringContextTests
{
    private CarDao dao;
    
    private CarService carService;
    
    private static int buildCar_i = 0;
    
    private Car car;
    
    @BeforeMethod
    public void setUp() {
        dao = Mockito.mock(CarDao.class);
        carService = new CarServiceImpl(dao);

        buildCar_i++;
        car =  new Car(
            "5348534251" + buildCar_i, // need uniqueness
            "LM258896" + buildCar_i,
            "Ford",
            "Focus",
            5,
            new Date()
        );
    }
    
    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
       
    @Test
    public void service(){
    	carService.serviceCar(car);
    	Assert.assertEquals(car.getCarState(), CarState.SERVICING);
    }
    
    @Test
    public void discard(){
    	carService.discardCar(car);
    	Assert.assertEquals(car.getCarState(), CarState.DISCARDED);
    }
}

