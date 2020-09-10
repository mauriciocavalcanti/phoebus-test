package com.phoebus.pandemicaid.model;

public class Exchange {

  private Long id;
  private Hospital hospitalA;
  private Hospital hospitalB;

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


}
