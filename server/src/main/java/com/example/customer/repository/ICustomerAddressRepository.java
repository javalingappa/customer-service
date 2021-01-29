package com.example.customer.repository;

import com.example.customer.entity.Customer;
import com.example.customer.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Raghavendra
 */

@Repository
public interface ICustomerAddressRepository extends JpaRepository<CustomerAddress, String> {
  CustomerAddress findByIdOrCustomerId(String id, String customerId);
  CustomerAddress findByCustomerId(String customerId);
  void deleteByCustomerId(String customerId);
}
