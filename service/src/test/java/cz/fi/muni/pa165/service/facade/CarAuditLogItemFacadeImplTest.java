package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.CarAuditLogItemDTO;
import cz.fi.muni.pa165.api.facade.CarAuditLogItemFacade;
import cz.fi.muni.pa165.model.entity.CarAuditLogItem;
import cz.fi.muni.pa165.service.CarAuditLogItemService;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import cz.fi.muni.pa165.service.config.ServiceTestsConfiguration;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @author jkuchar
 */
@ContextConfiguration(classes = ServiceTestsConfiguration.class)
public class CarAuditLogItemFacadeImplTest extends AbstractTestNGSpringContextTests {

    private CarAuditLogItemFacade uut;

    private CarAuditLogItemService service;

    @Inject
    private BeanMappingService beanMappingService;

    private void prepare() {
        // workaround for @BeforeMethod does not have wired dependencies
        uut = new CarAuditLogItemFacadeImpl(
                service = Mockito.mock(CarAuditLogItemService.class),
                beanMappingService
        );
    }

    @Test
    public void testFindAll() throws Exception {
        // Arrange
        prepare();
        List<CarAuditLogItem> source = new LinkedList<>();
        source.add(Mockito.mock(CarAuditLogItem.class));

        when(service.findAll()).thenReturn(source);

        // Act
        final List<CarAuditLogItemDTO> collection = uut.findAll();

        // Assert
        Assert.assertEquals(collection.size(), 1);
        Assert.assertEquals(collection.get(0), new CarAuditLogItemDTO());
    }

    @Test
    public void testFindById() throws Exception {

    }

    @Test
    public void testFindByCar() throws Exception {

    }

    @Test
    public void testFindByUser() throws Exception {

    }

    @Test
    public void testGetRecordsCreatedBetween() throws Exception {

    }

}