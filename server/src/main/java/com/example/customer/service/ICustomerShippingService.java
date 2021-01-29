package com.example.customer.service;

import com.example.customer.model.*;

import java.util.List;

/**
 * @author Raghavendra
 */
public interface ICustomerShippingService {

  List<CustomerResponse> getAllCustomers();

  List<CustomerResponse> getCustomersDetailsByName(String name);

  CustomerResponse getCustomerDetailsById(String id);

  CustomerResponse getCustomerDetailsByPhone(String phone);

  CustomerResponse saveCustomer(CustomerRequest customerRequest);

  CustomerResponse saveCustomerAddress(AddressRequest customerAddress, String userId);

  CustomerResponse saveCustomerShippingService(ShippingServiceRequest shippingService, String userId);

  CustomerDeleteResponse deleteCustomerDetailsById(String id);

  CustomerResponse updateCustomer(CustomerRequest customerRequest);

  CustomerResponse updateCustomerAddress(AddressRequest customerAddress, String userId);

  CustomerResponse updateCustomerShippingService(ShippingServiceRequest shippingService, String userId);

  CustomerDeleteResponse deleteAddressDetailsByCustomerId(String customerId);

  CustomerDeleteResponse deleteCustomerShippingServiceByCustomerId(String customerId);


}
