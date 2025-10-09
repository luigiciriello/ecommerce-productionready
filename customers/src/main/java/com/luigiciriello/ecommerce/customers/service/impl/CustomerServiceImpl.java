package com.luigiciriello.ecommerce.customers.service.impl;

import com.luigiciriello.ecommerce.customers.dto.CustomerDto;
import com.luigiciriello.ecommerce.customers.entity.Customer;
import com.luigiciriello.ecommerce.customers.exception.ResourceAlreadyExistsException;
import com.luigiciriello.ecommerce.customers.exception.ResourceNotFoundException;
import com.luigiciriello.ecommerce.customers.mapper.CustomerMapper;
import com.luigiciriello.ecommerce.customers.repository.CustomerRepository;
import com.luigiciriello.ecommerce.customers.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto getCustomerByEmail(final String email) {
        final Customer customerByCode = customerRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Customer", "email", email));
        return CustomerMapper.mapCustomerEntityToDto(customerByCode, new CustomerDto());
    }

    @Override
    public void registerCustomer(final CustomerDto customerDto) {
        final Optional<Customer> byEmail = customerRepository.findByEmail(customerDto.getEmail());
        if (byEmail.isPresent()) {
            throw new ResourceAlreadyExistsException("Customer", "email", customerDto.getEmail());
        }

        final Customer customer = CustomerMapper.mapCustomerDtoToEntity(customerDto, new Customer());
        customerRepository.save(customer);
    }

}
