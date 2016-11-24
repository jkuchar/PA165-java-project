/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.config;

import cz.fi.muni.pa165.api.dto.CarDTO;
import cz.fi.muni.pa165.api.facade.CarAuditLogItemFacade;
import cz.fi.muni.pa165.model.config.PersistenceApplicationContext;
import cz.fi.muni.pa165.model.dao.CarAuditLogItemDao;
import cz.fi.muni.pa165.model.dao.CarAuditLogItemDaoImpl;
import cz.fi.muni.pa165.model.entity.Car;
import cz.fi.muni.pa165.service.CarAuditLogItemServiceImpl;
import cz.fi.muni.pa165.service.facade.CarAuditLogItemFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author charlliz
 */
@Configuration
@Import({PersistenceApplicationContext.class})
@ComponentScan(basePackageClasses={
        CarAuditLogItemDao.class,
        CarAuditLogItemFacadeImpl.class,
        CarAuditLogItemServiceImpl.class
})
public class ServiceConfiguration {

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
