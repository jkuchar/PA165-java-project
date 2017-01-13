package cz.fi.muni.pa165.sampledata;


import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.model.entity.*;
import cz.fi.muni.pa165.service.ApplicationApprovedRecordService;
import cz.fi.muni.pa165.service.ApplicationRejectedRecordService;
import cz.fi.muni.pa165.service.CarAuditLogItemService;
import cz.fi.muni.pa165.service.CarService;
import cz.fi.muni.pa165.service.RentApplicationService;
import cz.fi.muni.pa165.service.RentRecordService;
import cz.fi.muni.pa165.service.ReturnRecordService;
import cz.fi.muni.pa165.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;

/**
 * @author
 */
@Component
@Transactional 
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationApprovedRecordService approvedService;
    @Autowired
    private ApplicationRejectedRecordService rejectedService;
    @Autowired
    private RentApplicationService rentApplicationService;
    @Autowired
    private RentRecordService rentRecordService;
    @Autowired
    private ReturnRecordService returnRecordService;
    @Autowired
    private CarAuditLogItemService logItemService;
    
    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        
        User emma = User.create("Emma", "Johnson", Role.USER, "emma.johnson@gmail.com");
        User david = User.create("David", "Smith", Role.USER, "smith.david@outlook.com");
        User olivia = User.create("Olivia", "Jones", Role.MANAGER, "jones.olivia@gmail.com");
        User sophia = User.create("Sophia", "Williams", Role.ADMIN, "sophieee456@mail.com");
        
        userService.register(emma, "password");
        userService.register(david, "password");
        userService.register(olivia, "password");
        userService.register(sophia, "password");        
        
        Car mazda = new Car("PB0698-146678", "5B54567", "Mazda", "3", 5 );
        Car octavia = new Car("AM0748-124978", "1P44123", "Škoda", "Octavia", 5 );
        Car nissan = new Car("PO6614-951753", "SV335AJ", "Nissan", "Sentra", 5 );
        Car fiat = new Car("L789L-446789", "BA678CC", "Fiat", "Dobl", 7 );
        
        carService.createCar(mazda);
        carService.createCar(octavia);
        carService.createCar(nissan);
        carService.createCar(fiat);
        
        RentApplication rent1 = null;
        RentApplication rent2 = null;
        RentApplication rent3 = null;
        RentApplication rent4 = null;
        RentApplication rent5 = null;
        
        try {
            rent1 = new RentApplication(octavia,emma,"Bussines trip to Prague", parseDate("2016-12-15"), parseDate("2016-12-16"));
            rent2 = new RentApplication(mazda,olivia,"Workshop in Olomouc", parseDate("2016-12-05"), parseDate("2016-12-07"));
            rent3 = new RentApplication(fiat,david,"Delivery of office equipmnet to Brno", parseDate("2016-12-14"), parseDate("2016-12-17"));
            rent4 = new RentApplication(octavia,sophia,"Bussines trip to Wien", parseDate("2017-01-05"), parseDate("2017-01-08"));
            rent5 = new RentApplication(nissan,david,"trip", parseDate("2016-12-20"), parseDate("2016-12-22"));

        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(SampleDataLoadingFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        rentApplicationService.create(rent1);
        rentApplicationService.create(rent2);
        rentApplicationService.create(rent3);
        rentApplicationService.create(rent4);
        rentApplicationService.create(rent5);
        
        ApplicationRejectedRecord rejected1 = new ApplicationRejectedRecord("Car will be serviced in that time", rent2);
        ApplicationRejectedRecord rejected2 = new ApplicationRejectedRecord("Please provide more information about purpose of trip", rent5);
        
        rejectedService.create(rejected1);
        rejectedService.create(rejected2);
        
        ApplicationApprovedRecord approved1 = new ApplicationApprovedRecord("Car rental allowed", rent2);
        ApplicationApprovedRecord approved2 = new ApplicationApprovedRecord("Car rental allowed", rent3);
        ApplicationApprovedRecord approved3 = new ApplicationApprovedRecord("Car rental allowed", rent4);
        
        approvedService.create(approved1);
        approvedService.create(approved2);
        approvedService.create(approved3);       
        
        RentRecord rentRecord1 = new RentRecord("Car was successfully rented", approved1, 26, 75896);
        RentRecord rentRecord2 = new RentRecord("Car was successfully rented", approved2, 44, 120567);
        
        rentRecordService.create(rentRecord1);
        rentRecordService.create(rentRecord2);
        
        ReturnRecord return1 = new ReturnRecord("Car was returned in ok state", rentRecord1, 15, 76016);
        
        returnRecordService.create(return1);
    }
    
    private static Date parseDate(String date) throws ParseException{       
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return(formatter.parse(date));
    }
}