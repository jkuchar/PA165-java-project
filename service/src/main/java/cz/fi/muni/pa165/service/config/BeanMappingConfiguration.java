/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.config;

import cz.fi.muni.pa165.api.dto.CarDTO;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.service.facade.BeanMappingService;
import cz.fi.muni.pa165.service.facade.BeanMappingServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author charlizz
 */
@Configuration
public class BeanMappingConfiguration {

    @Bean
    public BeanMappingService beanMappingService() {
        return new BeanMappingServiceImpl(dozer());
    }

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    /**
     * Custom config for Dozer if needed
     *
     * @author nguyen
     */
    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Car.class, CarDTO.class);
        }
    }

}
