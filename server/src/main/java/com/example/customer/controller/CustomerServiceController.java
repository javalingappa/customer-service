package com.example.customer.controller;

import com.example.customer.api.CustomerApi;
import com.example.customer.model.*;
import com.example.customer.service.ICustomerShippingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * This class exposes APIs for end-users to perform different operations on Customer Service
 *
 * @author Raghavendra
 */
@Log
@RestController
@RequestMapping("/v1/api")
public class CustomerServiceController implements CustomerApi {

  @Autowired
  ICustomerShippingService customerShippingService;

  @Override
  public Optional<ObjectMapper> getObjectMapper() {
    return Optional.empty();
  }

  @Override
  public Optional<HttpServletRequest> getRequest() {
    return Optional.empty();
  }

  @Override
  public Optional<String> getAcceptHeader() {
    return Optional.empty();
  }

  @Override
  public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
    return new ResponseEntity(customerShippingService.getAllCustomers(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CustomerResponse> getCustomerDetailsById(String customerId) {
    return new ResponseEntity(customerShippingService.getCustomerDetailsById(customerId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CustomerResponse> updateCustomer(@Valid CustomerRequest customer) {
    return new ResponseEntity(customerShippingService.updateCustomer(customer), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CustomerResponse> updateCustomerAddress(String customerId, @Valid AddressRequest customerAddress) {
    return new ResponseEntity(customerShippingService.updateCustomerAddress(customerAddress, customerId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CustomerResponse> updateCustomerShippingService(String customerId, @Valid ShippingServiceRequest shippingService) {
    return new ResponseEntity<>(customerShippingService.updateCustomerShippingService(shippingService, customerId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CustomerResponse> saveCustomer(@Valid CustomerRequest customer) {
    return new ResponseEntity(customerShippingService.saveCustomer(customer), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CustomerResponse> saveCustomerAddress(String customerId, @Valid AddressRequest customerAddress) {
    return new ResponseEntity(customerShippingService.saveCustomerAddress(customerAddress, customerId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CustomerResponse> saveCustomerShippingService(String customerId, @Valid ShippingServiceRequest shippingService) {
    return new ResponseEntity<>(customerShippingService.saveCustomerShippingService(shippingService, customerId), HttpStatus.OK);
  }


  @Override
  public ResponseEntity<CustomerDeleteResponse> deleteCustomerDetailsById(String customerId) {
    return new ResponseEntity<>(customerShippingService.deleteCustomerDetailsById(customerId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CustomerDeleteResponse> deleteAddressDetailsByCustomerId(@NotNull @Valid String customerId) {
    return new ResponseEntity<>(customerShippingService.deleteAddressDetailsByCustomerId(customerId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CustomerDeleteResponse> deleteCustomerShippingServiceByCustomerId(@NotNull @Valid String customerId) {
    return new ResponseEntity<>(customerShippingService.deleteCustomerShippingServiceByCustomerId(customerId), HttpStatus.OK);
  }
}
