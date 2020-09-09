package com.phoebus.pandemicaid.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "fullnesses")
@NamedQuery(name = "FullnessEntity.findAll", query = "SELECT f FROM FullnessEntity f")
public class FullnessEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "FULLNESSES_ID_GENERATOR")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FULLNESSES_ID_GENERATOR")
  private Long id;

  private BigDecimal percentage;
  
  private LocalDateTime date;

  // bi-directional many-to-one association to Hospital
  @ManyToOne
  private HospitalEntity hospital;

  public FullnessEntity() {}

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public BigDecimal getPercentage() {
    return this.percentage;
  }

  public void setPercentage(BigDecimal percentage) {
    this.percentage = percentage;
  }

  public HospitalEntity getHospital() {
    return this.hospital;
  }

  public void setHospital(HospitalEntity hospitalEntity) {
    this.hospital = hospitalEntity;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

}
