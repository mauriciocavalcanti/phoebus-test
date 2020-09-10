package com.phoebus.pandemicaid.model;

import java.math.BigDecimal;
import java.util.List;

public class Hospital {

  private Long id;
  private String name;
  private String cnpj;
  private Address address;
  private BigDecimal currentFullness;
  private List<Resource> resources;
  private List<Fullness> fullnesses;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public List<Resource> getResources() {
    return resources;
  }

  public void setResources(List<Resource> resources) {
    this.resources = resources;
  }

  public List<Fullness> getFullnesses() {
    return fullnesses;
  }

  public void setFullnesses(List<Fullness> fullnesses) {
    this.fullnesses = fullnesses;
  }

  public BigDecimal getCurrentFullness() {
    return currentFullness;
  }

  public void setCurrentFullness(BigDecimal currentFullness) {
    this.currentFullness = currentFullness;
  }

}
