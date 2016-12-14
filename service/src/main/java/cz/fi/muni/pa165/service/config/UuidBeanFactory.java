/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.config;

import java.util.UUID;
import org.dozer.BeanFactory;

/**
 *
 * @author charlliz
 */
public class UuidBeanFactory implements BeanFactory {
    
    @Override
    public Object createBean(Object sourceBean, Class<?> destinationType, String mapId) {
        if (sourceBean == null) {
            return null;
        }
        UUID source = (UUID) sourceBean;
        UUID destination = new UUID(source.getMostSignificantBits(), source.getLeastSignificantBits());
        return destination;
    }    
}
