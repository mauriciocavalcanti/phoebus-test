package com.phoebus.pandemicaid.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "exchanges")
@NamedQuery(name = "ExchangeEntity.findAll", query = "SELECT e FROM ExchangeEntity e")
public class ExchangeEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "EXCHANGES_ID_GENERATOR")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCHANGES_ID_GENERATOR")
  private Long id;

  @Column(name = "hospital_a_fullness")
  private BigDecimal hospitalAFullness;

  @Column(name = "hospital_b_fullness")
  private BigDecimal hospitalBFullness;

  // bi-directional many-to-one association to ExchangeResource
  @OneToMany(mappedBy = "exchange")
  private List<ExchangeResourceEntity> exchangeResourceEntities;

  // bi-directional many-to-one association to Hospital
  @ManyToOne
  @JoinColumn(name = "hospital_a_id")
  private HospitalEntity hospital1;

  // bi-directional many-to-one association to Hospital
  @ManyToOne
  @JoinColumn(name = "hospital_b_id")
  private HospitalEntity hospital2;

  public ExchangeEntity() {}

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public BigDecimal getHospitalAFullness() {
    return this.hospitalAFullness;
  }

  public void setHospitalAFullness(BigDecimal hospitalAFullness) {
    this.hospitalAFullness = hospitalAFullness;
  }

  public BigDecimal getHospitalBFullness() {
    return this.hospitalBFullness;
  }

  public void setHospitalBFullness(BigDecimal hospitalBFullness) {
    this.hospitalBFullness = hospitalBFullness;
  }

  public List<ExchangeResourceEntity> getExchangeResources() {
    return this.exchangeResourceEntities;
  }

  public void setExchangeResources(List<ExchangeResourceEntity> exchangeResourceEntities) {
    this.exchangeResourceEntities = exchangeResourceEntities;
  }

  public ExchangeResourceEntity addExchangeResource(ExchangeResourceEntity exchangeResourceEntity) {
    getExchangeResources().add(exchangeResourceEntity);
    exchangeResourceEntity.setExchange(this);

    return exchangeResourceEntity;
  }

  public ExchangeResourceEntity removeExchangeResource(
      ExchangeResourceEntity exchangeResourceEntity) {
    getExchangeResources().remove(exchangeResourceEntity);
    exchangeResourceEntity.setExchange(null);

    return exchangeResourceEntity;
  }

  public HospitalEntity getHospital1() {
    return this.hospital1;
  }

  public void setHospital1(HospitalEntity hospital1) {
    this.hospital1 = hospital1;
  }

  public HospitalEntity getHospital2() {
    return this.hospital2;
  }

  public void setHospital2(HospitalEntity hospital2) {
    this.hospital2 = hospital2;
  }

}
