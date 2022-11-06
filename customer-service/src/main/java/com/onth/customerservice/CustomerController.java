package com.onth.customerservice;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/customers")
public class CustomerController {
    
    private final ICutomerService customerService;

    public CustomerController(ICutomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public CustomerEntity getCustomerById(@PathVariable int id) {
        return customerService.findCustomerById(id);
    }
}
