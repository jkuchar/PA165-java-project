/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.config;

import cz.fi.muni.pa165.model.config.PersistenceApplicationContext;
import cz.fi.muni.pa165.service.CarServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author jkuchar
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses = {CarServiceImpl.class})
public class ServiceConfiguration {

}
