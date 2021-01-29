package com.example.customer.entity;
/**
 * @author Raghavendra
 */

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements Comparable<Customer> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Column(name = "NAME")
  private String name = null;


  @Column(name = "CUSTOMER_TYPE")
  private String customerType = null;

  @Column(name = "PHONE", unique = true)
  private String phone = null;

  @Column(name = "ALT_PHONE")
  private String altPhone = null;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCustomerType() {
    return customerType;
  }

  public void setCustomerType(String customerType) {
    this.customerType = customerType;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAltPhone() {
    return altPhone;
  }

  public void setAltPhone(String altPhone) {
    this.altPhone = altPhone;
  }

  @Override
  public String toString() {
    return "Customer{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", customerType='" + customerType + '\'' +
      ", phone='" + phone + '\'' +
      ", altPhone=" + altPhone +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer that = (Customer) o;
    return id.equals(that.id) &&
      name.equals(that.name) &&
      phone.equals(that.phone) &&
      customerType.equals(that.customerType) &&
      altPhone.equals(that.altPhone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, customerType, phone, altPhone);
  }

  @Override
  public int compareTo(Customer o) {
    return this.getName().compareTo(o.getName());
  }
}
