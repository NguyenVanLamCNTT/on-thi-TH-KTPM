package com.onth.driverservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/drivers")
public class DriverController {
    
    private final RestServiceUtil customRestServiceUtil;
    private final IDriverService driverService;

    @Autowired
    public DriverController(RestServiceUtil restServiceUtil, IDriverService driverService) {
        this.customRestServiceUtil = restServiceUtil;
        this.driverService = driverService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable int id) {
        DriverEntity entity = driverService.getDriverById(id);
        CustomerDto res = customRestServiceUtil.getCustomer(entity.getCustomerId());
        DriverDto result = new DriverDto();
        result.setDriverId(entity.getDriverId());
        result.setCustomer(res);
        result.setKm(entity.getKm());
        result.setName(entity.getName());
        result.setPrice(entity.getPrice());
        return ResponseEntity.ok().body(result);
    }
}
