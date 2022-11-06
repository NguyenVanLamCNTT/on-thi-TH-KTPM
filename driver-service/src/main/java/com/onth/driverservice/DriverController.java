package com.onth.driverservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@CrossOrigin
@RequestMapping("/api/drivers")
public class DriverController {
    
    //resilience4j
    private static final String Service_Driver = "serviceDriver";

    private final RestServiceUtil customRestServiceUtil;
    private final IDriverService driverService;

    @Autowired
    public DriverController(RestServiceUtil restServiceUtil, IDriverService driverService) {
        this.customRestServiceUtil = restServiceUtil;
        this.driverService = driverService;
    }


    @GetMapping("/{driverId}")
    @CircuitBreaker(name = Service_Driver, fallbackMethod = "serviceError")
    public ResponseEntity<?> getDriver(@PathVariable int driverId) {
        DriverEntity entity = driverService.getDriverById(driverId);
        CustomerDto res = customRestServiceUtil.getCustomer(entity.getCustomerId());
        DriverDto result = new DriverDto();
        result.setDriverId(entity.getDriverId());
        result.setCustomer(res);
        result.setKm(entity.getKm());
        result.setName(entity.getName());
        result.setPrice(entity.getPrice());
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity<String> serviceError(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.toString());
    }
}
