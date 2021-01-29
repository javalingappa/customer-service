package com.example.customer.repository;

import com.example.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Raghavendra
 */

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, String> {

  Customer findByIdOrName(String id, String name);

  List<Customer> findByName(String name);

  Customer findByPhone(String phone);

  Customer findByNameOrPhone(String name, String phone);
}
