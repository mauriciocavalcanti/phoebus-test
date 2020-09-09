package com.phoebus.pandemicaid.entity;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "exchange_resources")
@NamedQuery(name = "ExchangeResourceEntity.findAll", query = "SELECT e FROM ExchangeResourceEntity e")
public class ExchangeResourceEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "EXCHANGE_RESOURCES_ID_GENERATOR")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCHANGE_RESOURCES_ID_GENERATOR")
  private Long id;

  private String name;

  private Integer weight;

  // bi-directional many-to-one association to Exchange
  @ManyToOne
  private ExchangeEntity exchange;
  
  @ManyToOne
  private HospitalEntity hospital;

  public ExchangeResourceEntity() {}

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getWeight() {
    return this.weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public ExchangeEntity getExchange() {
    return this.exchange;
  }

  public void setExchange(ExchangeEntity exchangeEntity) {
    this.exchange = exchangeEntity;
  }

  public HospitalEntity getHospital() {
    return hospital;
  }

  public void setHospital(HospitalEntity hospital) {
    this.hospital = hospital;
  }

}
