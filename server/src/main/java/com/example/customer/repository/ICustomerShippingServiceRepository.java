package com.example.customer.repository;

import com.example.customer.entity.Customer;
import com.example.customer.entity.CustomerShippingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Raghavendra
 */

@Repository
public interface ICustomerShippingServiceRepository extends JpaRepository<CustomerShippingService, String> {

  CustomerShippingService findByIdOrCustomerId(String id, String customerId);

  CustomerShippingService findByCustomerId(String customerId);

  void deleteByCustomerId(String customerId);
}
