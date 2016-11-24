package cz.fi.muni.pa165.service.facade;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @author jkuchar
 */
interface BeanMappingService {
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);

    Mapper getMapper();
}
