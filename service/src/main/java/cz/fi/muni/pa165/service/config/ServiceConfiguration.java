/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.config;

import cz.fi.muni.pa165.model.config.PersistenceApplicationContext;
import cz.fi.muni.pa165.model.dao.CarAuditLogItemDao;
import cz.fi.muni.pa165.service.CarAuditLogItemServiceImpl;
import cz.fi.muni.pa165.service.facade.CarAuditLogItemFacadeImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author jkuchar
 */
@Configuration
@Import({
        PersistenceApplicationContext.class,
        BeanMappingConfiguration.class
})
@ComponentScan(basePackageClasses={
        CarAuditLogItemDao.class,
        CarAuditLogItemFacadeImpl.class,
        CarAuditLogItemServiceImpl.class
})
public class ServiceConfiguration {

}
