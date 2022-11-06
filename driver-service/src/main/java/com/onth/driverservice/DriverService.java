package com.onth.driverservice;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DriverService implements IDriverService{

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
    
    @Override
    @Cacheable(value = "driver", key = "#driverId")
    public DriverEntity getDriverById(int driverId) {
        return driverRepository.findById(driverId).get();
    }
    
}
