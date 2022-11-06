package com.onth.driverservice;

import org.springframework.stereotype.Service;

@Service
public class DriverService implements IDriverService{

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
    @Override
    public DriverEntity getDriverById(int id) {
        return driverRepository.findById(id).get();
    }
    
}
