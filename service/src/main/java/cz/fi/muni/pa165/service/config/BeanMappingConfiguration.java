/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.config;

import cz.fi.muni.pa165.api.dto.CarDTO;
import cz.fi.muni.pa165.model.config.PersistenceApplicationContext;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.BeanMappingServiceImpl;
import cz.fi.muni.pa165.service.CarServiceImpl;
import cz.fi.muni.pa165.service.facade.CarFacadeImpl;
import java.util.Collections;
import java.util.UUID;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author charlizz
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses={CarServiceImpl.class, CarFacadeImpl.class})
public class BeanMappingConfiguration {

    @Bean
    public BeanMappingService beanMappingService() {
        return new BeanMappingServiceImpl(dozer());
    }

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.setMappingFiles(Collections.singletonList("mappings.xml"));
        return dozer;
    }

    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Car.class, CarDTO.class);         
        }
    }

}
