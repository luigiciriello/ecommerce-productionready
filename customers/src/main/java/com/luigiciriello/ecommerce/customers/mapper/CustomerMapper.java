package com.luigiciriello.ecommerce.customers.mapper;

import com.luigiciriello.ecommerce.customers.dto.CustomerDto;
import com.luigiciriello.ecommerce.customers.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapCustomerEntityToDto(final Customer source, final CustomerDto target)
    {
        target.setEmail(source.getEmail());
        target.setAddress(source.getAddress());
        target.setSurname(source.getSurname());
        target.setName(source.getName());
        return target;
    }

    public static Customer mapCustomerDtoToEntity(final CustomerDto source, final Customer target)
    {
        target.setEmail(source.getEmail());
        target.setAddress(source.getAddress());
        target.setSurname(source.getSurname());
        target.setName(source.getName());
        return target;
    }
}
