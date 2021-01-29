package com.example.customer.entity;
/**
 * @author Raghavendra
 */

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CUSTOMER_SHIPPING_SERVICE")
public class CustomerShippingService implements Comparable<CustomerShippingService> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "CUSTOMER_ID", unique = true)
    private String customerId = null;

    @Column(name = "SERVICE")
    private String service = null;

    @Column(name = "OPTION")
    private String option = null;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public String getOption() {
    return option;
  }

  public void setOption(String option) {
    this.option = option;
  }

  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
      CustomerShippingService that = (CustomerShippingService) o;
        return id.equals(that.id) &&
                id.equals(that.id) &&
                customerId.equals(that.customerId) &&
                service.equals(that.service) &&
                option.equals(that.option);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, service, option);
    }

    @Override
    public int compareTo(CustomerShippingService o) {
        return this.getService().compareTo(o.getService());
    }
}
