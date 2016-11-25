package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.service.BeanMappingServiceImpl;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.api.dto.CarAuditLogItemDTO;
import cz.fi.muni.pa165.model.entity.CarAuditLogItem;
import org.dozer.Mapper;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author jkuchar
 */
public class BeanMappingServiceTest
{
    private Mapper dozer;
    private BeanMappingService uut;

    @BeforeMethod
    public void setUp() throws Exception {
        uut = new BeanMappingServiceImpl(
                dozer = Mockito.mock(Mapper.class)
        );
    }

    @Test
    public void mapTo_list(){
        // Arrange
        List<CarAuditLogItem> collection = new LinkedList<>();
        CarAuditLogItem entity;
        collection.add(entity = Mockito.mock(CarAuditLogItem.class));
        CarAuditLogItemDTO dto = new CarAuditLogItemDTO();

        when(dozer.map(entity, CarAuditLogItemDTO.class)).thenReturn(dto);

        // Act
        uut.mapTo(collection, CarAuditLogItemDTO.class);

        // Assert
        verify(dozer, times(1)).map(entity, CarAuditLogItemDTO.class);
    }

    @Test
    public void mapTo_single(){
        // Arrange
        CarAuditLogItem entity = Mockito.mock(CarAuditLogItem.class);
        CarAuditLogItemDTO dto = new CarAuditLogItemDTO();

        when(dozer.map(entity, CarAuditLogItemDTO.class)).thenReturn(dto);

        // Act
        uut.mapTo(entity, CarAuditLogItemDTO.class);

        // Assert
        verify(dozer, times(1)).map(entity, CarAuditLogItemDTO.class);
    }

}
