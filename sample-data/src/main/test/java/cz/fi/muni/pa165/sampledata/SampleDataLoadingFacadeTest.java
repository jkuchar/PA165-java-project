package cz.fi.muni.pa165.sampledata;

//todo: add imports
/*import cz.fi.muni.pa165.dao.OrderDao;
import cz.fi.muni.pa165.dao.ProductDao;
import cz.fi.muni.pa165.entity.Price;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.service.UserService;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.List;

/**
 * Test data loading.
 *
 * @author rtrembecky
 */
@ContextConfiguration(classes = {CarParkWithSampleDataConfiguration.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SampleDataLoadingFacadeTest extends AbstractTestNGSpringContextTests {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeTest.class);

    @Test
    public void createSampleData() throws IOException {
        log.debug("starting test");
        //todo: create test
/*
        Assert.assertTrue(productDao.findAll().size() > 0, "no products");

        List<Price> priceHistory = productDao.findById(1l).getPriceHistory();
        Assert.assertTrue(priceHistory.size() > 0, "no prices for product 1");

        Assert.assertTrue(orderDao.findAll().size() > 0, "no orders");

        User admin = userService.getAllUsers().stream().filter(userService::isAdmin).findFirst().get();
        Assert.assertEquals(true, userService.authenticate(admin,"admin"));
        */
    }

}