package com.example.customer.entity;
/**
 * @author Raghavendra
 */

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CUSTOMER_ADDRESS")
public class CustomerAddress implements Comparable<CustomerAddress> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Column(name = "CUSTOMER_ID", unique = true)
  private String customerId = null;

  @Column(name = "LOCALITY")
  private String locality = null;

  @Column(name = "ADDRESS")
  private String address = null;

  @Column(name = "CITY_TOWN_DISTRICT")
  private String cityTownDistrict = null;

  @Column(name = "LANDMARK")
  private String landmark = null;

  @Column(name = "ADDRESS_TYPE")
  private String addressType = null;

  @Column(name = "STATE")
  private String state = null;

  @Column(name = "POSTAL_CODE")
  private String postalCode = null;

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

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCityTownDistrict() {
    return cityTownDistrict;
  }

  public void setCityTownDistrict(String cityTownDistrict) {
    this.cityTownDistrict = cityTownDistrict;
  }

  public String getLandmark() {
    return landmark;
  }

  public void setLandmark(String landmark) {
    this.landmark = landmark;
  }

  public String getAddressType() {
    return addressType;
  }

  public void setAddressType(String addressType) {
    this.addressType = addressType;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  @Override
  public String toString() {
    return "CustomerAddress{" +
      "id='" + id + '\'' +
      ", customerId='" + customerId + '\'' +
      ", locality='" + locality + '\'' +
      ", address='" + address + '\'' +
      ", cityTownDistrict='" + cityTownDistrict + '\'' +
      ", landmark='" + landmark + '\'' +
      ", addressType='" + addressType + '\'' +
      ", state='" + state + '\'' +
      ", postalCode=" + postalCode +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerAddress that = (CustomerAddress) o;
    return id.equals(that.id) &&
      customerId.equals(that.customerId) &&
      locality.equals(that.locality) &&
      state.equals(that.state) &&
      cityTownDistrict.equals(that.cityTownDistrict) &&
      postalCode.equals(that.postalCode) &&
      landmark.equals(that.landmark) &&
      addressType.equals(that.addressType) &&
      address.equals(that.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customerId, addressType, locality, state, address, landmark, cityTownDistrict);
  }

  @Override
  public int compareTo(CustomerAddress o) {
    return this.getAddressType().compareTo(o.getAddressType());
  }
}
