package com.phoebus.pandemicaid.model;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Exchange {

  private Long id;
  private Hospital hospitalA;
  private Hospital hospitalB;
  private BigDecimal hospitalAFullness;
  private BigDecimal hospitalBFullness;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Hospital getHospitalA() {
    return hospitalA;
  }

  public void setHospitalA(Hospital hospitalA) {
    this.hospitalA = hospitalA;
  }

  public Hospital getHospitalB() {
    return hospitalB;
  }

  public void setHospitalB(Hospital hospitalB) {
    this.hospitalB = hospitalB;
  }

  public BigDecimal getHospitalAFullness() {
    return hospitalAFullness;
  }

  public void setHospitalAFullness(BigDecimal hospitalAFullness) {
    this.hospitalAFullness = hospitalAFullness;
  }

  public BigDecimal getHospitalBFullness() {
    return hospitalBFullness;
  }

  public void setHospitalBFullness(BigDecimal hospitalBFullness) {
    this.hospitalBFullness = hospitalBFullness;
  }

}
