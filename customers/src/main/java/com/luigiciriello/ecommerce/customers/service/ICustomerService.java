package com.luigiciriello.ecommerce.customers.service;

import com.luigiciriello.ecommerce.customers.dto.CustomerDto;
import com.luigiciriello.ecommerce.customers.entity.Customer;

import java.util.List;

public interface ICustomerService {

    CustomerDto getCustomerByEmail(final String email);
    void registerCustomer(final CustomerDto customerDto);
}
