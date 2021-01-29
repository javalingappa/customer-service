package com.example.customer.service.impl;

import com.example.customer.entity.Customer;
import com.example.customer.entity.CustomerAddress;
import com.example.customer.entity.CustomerShippingService;
import com.example.customer.exception.CustomerServiceException;
import com.example.customer.model.*;
import com.example.customer.repository.ICustomerAddressRepository;
import com.example.customer.repository.ICustomerRepository;
import com.example.customer.repository.ICustomerShippingServiceRepository;
import com.example.customer.service.ICustomerShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerShippingServiceImpl implements ICustomerShippingService {

  @Autowired
  ICustomerRepository customerRepository;

  @Autowired
  ICustomerAddressRepository addressRepository;

  @Autowired
  ICustomerShippingServiceRepository serviceRepository;

  @Override
  public List<CustomerResponse> getAllCustomers() {

    return buildCustomerResponse(customerRepository.findAll());
  }

  private List<CustomerResponse> buildCustomerResponse(List<Customer> customers) {
    List<CustomerResponse> response = new ArrayList<>();
    for (Customer customer : customers) {
      response.add(getCustomerResponse(customer));
    }
    return response;

  }

  private CustomerResponse getCustomerResponse(Customer customer) {
    if (customer == null) {
      return null;
    }
    CustomerResponse res = new CustomerResponse();
    res.setCustomerId(customer.getId());
    res.setName(customer.getName());
    res.setPhone(customer.getPhone());
    res.setAltPhone(customer.getAltPhone());
    res.setType(customer.getCustomerType());
    CustomerAddress address = addressRepository.findByCustomerId(customer.getId());
    if (address != null) {
      res.setAddress(address.getAddress());
      res.setAddressType(address.getAddressType());
      res.setCityTownDistrict(address.getCityTownDistrict());
      res.setLandmark(address.getLandmark());
      res.setLocality(address.getLocality());
      res.setState(address.getState());
      res.setPostalCode(address.getPostalCode());
    }
    CustomerShippingService css = serviceRepository.findByCustomerId(customer.getId());
    if (css != null) {
      res.setService(css.getService());
      res.setOption(css.getOption());
    }

    return res;
  }


  private CustomerResponse getCustomerResponseById(String customerId) {
    return getCustomerResponse(customerRepository.findById(customerId).orElse(null));
  }

  @Override
  public List<CustomerResponse> getCustomersDetailsByName(String name) {
    return buildCustomerResponse(customerRepository.findByName(name));
  }

  @Override
  public CustomerResponse getCustomerDetailsById(String id) {
    return getCustomerResponse(customerRepository.findById(id).orElse(null));
  }

  @Override
  public CustomerResponse getCustomerDetailsByPhone(String phone) {
    return getCustomerResponse(customerRepository.findByPhone(phone));
  }

  @Override
  public CustomerResponse saveCustomerAddress(AddressRequest customerAddress, String customerId) {
    CustomerAddress address = addressRepository.findByCustomerId(customerId);
    if (address != null) {
      return updateCustomerAddress(customerAddress, customerId);
    } else {
      addressRepository.save(convertToAddressEntity(customerAddress, customerId));
      return getCustomerResponse(customerRepository.findById(customerId).orElse(null));
    }
  }

  private CustomerAddress convertToAddressEntity(AddressRequest customerAddress, String customerId) {
    CustomerAddress address = new CustomerAddress();
    address.setAddress(customerAddress.getAddress());
    address.setAddressType(customerAddress.getAddressType());
    address.setCityTownDistrict(customerAddress.getCityTownDistrict());
    address.setLandmark(customerAddress.getLandmark());
    address.setLocality(customerAddress.getLocality());
    address.setState(customerAddress.getState());
    address.setPostalCode(customerAddress.getPostalCode());
    address.setCustomerId(customerId);
    return address;
  }

  @Override
  public CustomerResponse saveCustomerShippingService(@Valid ShippingServiceRequest shippingService, String customerId) {
    CustomerShippingService css = serviceRepository.findByCustomerId(customerId);
    if (css != null) {
      return updateCustomerShippingService(shippingService, customerId);
    } else {
      serviceRepository.save(converToShippingServiceEntity(shippingService, customerId));
      return getCustomerResponse(customerRepository.findById(customerId).orElse(null));
    }
  }

  private CustomerShippingService converToShippingServiceEntity(ShippingServiceRequest shippingService, String customerId) {
    CustomerShippingService obj = new CustomerShippingService();
    obj.setCustomerId(customerId);
    obj.setService(shippingService.getService());
    obj.setOption(shippingService.getOption());
    return obj;
  }

  @Override
  public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
    CustomerResponse obj = getCustomerDetailsByPhone(customerRequest.getPhone());
    if (obj != null) {
      return updateCustomer(customerRequest);
    } else {
      return getCustomerResponse(customerRepository.save(convertToCustomerEntity(customerRequest)));
    }
  }

  @Transactional
  @Override
  public CustomerDeleteResponse deleteCustomerDetailsById(String customerId) {
    CustomerDeleteResponse reposne = new CustomerDeleteResponse();
    try {
      serviceRepository.deleteByCustomerId(customerId);
      addressRepository.deleteByCustomerId(customerId);
      customerRepository.deleteById(customerId);
    } catch (CustomerServiceException e) {
      reposne.setMessage("Delete Failed for the customerId: " + customerId);
      reposne.setStatus("FAILED");
      reposne.setStatusCode(500);
      return reposne;
    }
    reposne.setMessage("Deleted a record  for the customerId: " + customerId);
    reposne.setStatus("SUCCESS");
    reposne.setStatusCode(200);
    return reposne;
  }

  @Override
  public CustomerResponse updateCustomer(CustomerRequest customerRequest) {
    Customer obj = customerRepository.findByNameOrPhone(customerRequest.getName(), customerRequest.getPhone());
    if (obj != null) {
      Customer customer = convertToCustomerEntity(customerRequest);
      customer.setId(obj.getId());
      return getCustomerResponse(customerRepository.save(customer));
    }
    return null;
  }

  @Override
  public CustomerResponse updateCustomerAddress(AddressRequest customerAddress, String customerId) {
    CustomerAddress address = addressRepository.findByCustomerId(customerId);
    if (address != null) {
      CustomerAddress addressLocal = convertToAddressEntity(customerAddress, customerId);
      addressLocal.setId(address.getId());
      addressRepository.save(addressLocal);
      return getCustomerResponse(customerRepository.findById(customerId).orElse(null));
    }
    return null;
  }

  @Override
  public CustomerResponse updateCustomerShippingService(ShippingServiceRequest shippingService, String customerId) {
    CustomerShippingService css = serviceRepository.findByCustomerId(customerId);
    if (css != null) {
      CustomerShippingService obj = converToShippingServiceEntity(shippingService, customerId);
      obj.setId(css.getId());
      serviceRepository.save(obj);
      return getCustomerResponse(customerRepository.findById(customerId).orElse(null));
    }
    return null;
  }

  private Customer convertToCustomerEntity(CustomerRequest customerRequest) {
    Customer res = new Customer();
    res.setName(customerRequest.getName());
    res.setPhone(customerRequest.getPhone());
    res.setAltPhone(customerRequest.getAltPhone());
    res.setCustomerType(customerRequest.getCustomerType());
    return res;
  }

  @Transactional
  @Override
  public CustomerDeleteResponse deleteAddressDetailsByCustomerId(String customerId) {
    CustomerDeleteResponse reposne = new CustomerDeleteResponse();
    try {
      addressRepository.deleteByCustomerId(customerId);
    } catch (CustomerServiceException e) {
      reposne.setMessage("Delete Failed for the customerId: " + customerId);
      reposne.setStatus("FAILED");
      reposne.setStatusCode(500);
      return reposne;
    }
    reposne.setMessage("Deleted a record  for the customerId: " + customerId);
    reposne.setStatus("SUCCESS");
    reposne.setStatusCode(200);
    return reposne;
  }

  @Transactional
  @Override
  public CustomerDeleteResponse deleteCustomerShippingServiceByCustomerId(String customerId) {
    CustomerDeleteResponse reposne = new CustomerDeleteResponse();
    try {
      serviceRepository.deleteByCustomerId(customerId);
    } catch (CustomerServiceException e) {
      reposne.setMessage("Delete Failed for the customerId: " + customerId);
      reposne.setStatus("FAILED");
      reposne.setStatusCode(500);
      return reposne;
    }
    reposne.setMessage("Deleted a record  for the customerId: " + customerId);
    reposne.setStatus("SUCCESS");
    reposne.setStatusCode(200);
    return reposne;
  }
}
