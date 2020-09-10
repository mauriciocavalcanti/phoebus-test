package com.phoebus.pandemicaid.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hospitals")
@NamedQuery(name = "HospitalEntity.findAll", query = "SELECT h FROM HospitalEntity h")
public class HospitalEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "HOSPITALS_ID_GENERATOR")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOSPITALS_ID_GENERATOR")
  private Long id;

  private String cep;

  private String city;

  private String cnpj;

  private double latitude;

  private double longitude;

  private String name;

  private String neighborhood;

  private Integer number;

  private String state;

  private String street;

  // bi-directional many-to-one association to Exchange
  @OneToMany(mappedBy = "hospital1")
  private List<ExchangeEntity> exchanges1;

  // bi-directional many-to-one association to Exchange
  @OneToMany(mappedBy = "hospital2")
  private List<ExchangeEntity> exchanges2;

  // bi-directional many-to-one association to Fullness
  @OneToMany(mappedBy = "hospital")
  private List<FullnessEntity> fullnesses;

  // bi-directional many-to-one association to HospitalResource
  @OneToMany(mappedBy = "hospital")
  private List<HospitalResourceEntity> hospitalResources;

  @OneToMany(mappedBy = "hospital")
  private List<ExchangeResourceEntity> exchangeResources;

  public HospitalEntity() {}

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCep() {
    return this.cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCnpj() {
    return this.cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public double getLatitude() {
    return this.latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return this.longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNeighborhood() {
    return this.neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public Integer getNumber() {
    return this.number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getStreet() {
    return this.street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public List<ExchangeEntity> getExchanges1() {
    return this.exchanges1;
  }

  public void setExchanges1(List<ExchangeEntity> exchanges1) {
    this.exchanges1 = exchanges1;
  }

  public ExchangeEntity addExchanges1(ExchangeEntity exchanges1) {
    getExchanges1().add(exchanges1);
    exchanges1.setHospital1(this);

    return exchanges1;
  }

  public ExchangeEntity removeExchanges1(ExchangeEntity exchanges1) {
    getExchanges1().remove(exchanges1);
    exchanges1.setHospital1(null);

    return exchanges1;
  }

  public List<ExchangeEntity> getExchanges2() {
    return this.exchanges2;
  }

  public void setExchanges2(List<ExchangeEntity> exchanges2) {
    this.exchanges2 = exchanges2;
  }

  public ExchangeEntity addExchanges2(ExchangeEntity exchanges2) {
    getExchanges2().add(exchanges2);
    exchanges2.setHospital2(this);

    return exchanges2;
  }

  public ExchangeEntity removeExchanges2(ExchangeEntity exchanges2) {
    getExchanges2().remove(exchanges2);
    exchanges2.setHospital2(null);

    return exchanges2;
  }

  public List<FullnessEntity> getFullnesses() {
    return this.fullnesses;
  }

  public void setFullnesses(List<FullnessEntity> fullnesses) {
    this.fullnesses = fullnesses;
  }

  public FullnessEntity addFullness(FullnessEntity fullnessEntity) {
    getFullnesses().add(fullnessEntity);
    fullnessEntity.setHospital(this);

    return fullnessEntity;
  }

  public FullnessEntity removeFullness(FullnessEntity fullnessEntity) {
    getFullnesses().remove(fullnessEntity);
    fullnessEntity.setHospital(null);

    return fullnessEntity;
  }

  public List<HospitalResourceEntity> getHospitalResources() {
    return this.hospitalResources;
  }

  public void setHospitalResources(List<HospitalResourceEntity> hospitalResourceEntities) {
    this.hospitalResources = hospitalResourceEntities;
  }

  public HospitalResourceEntity addHospitalResource(HospitalResourceEntity hospitalResourceEntity) {
    getHospitalResources().add(hospitalResourceEntity);
    hospitalResourceEntity.setHospital(this);

    return hospitalResourceEntity;
  }

  public HospitalResourceEntity removeHospitalResource(
      HospitalResourceEntity hospitalResourceEntity) {
    getHospitalResources().remove(hospitalResourceEntity);
    hospitalResourceEntity.setHospital(null);

    return hospitalResourceEntity;
  }

  public List<ExchangeResourceEntity> getExchangeResources() {
    return exchangeResources;
  }

  public void setExchangeResources(List<ExchangeResourceEntity> exchangeResources) {
    this.exchangeResources = exchangeResources;
  }

  public ExchangeResourceEntity addExchangeResource(ExchangeResourceEntity exchangeResourceEntity) {
    getExchangeResources().add(exchangeResourceEntity);
    exchangeResourceEntity.setHospital(this);

    return exchangeResourceEntity;
  }

  public ExchangeResourceEntity removeExchangeResource(
      ExchangeResourceEntity exchangeResourceEntity) {
    getExchangeResources().remove(exchangeResourceEntity);
    exchangeResourceEntity.setHospital(null);

    return exchangeResourceEntity;
  }
}
