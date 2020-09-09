package com.phoebus.pandemicaid.entity;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "hospital_resources")
@NamedQuery(name = "HospitalResourceEntity.findAll", query = "SELECT h FROM HospitalResourceEntity h")
public class HospitalResourceEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "HOSPITAL_RESOURCES_ID_GENERATOR")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOSPITAL_RESOURCES_ID_GENERATOR")
  private Long id;

  // bi-directional many-to-one association to Hospital
  @ManyToOne
  private HospitalEntity hospital;

  // bi-directional many-to-one association to Resource
  @ManyToOne
  private ResourceEntity resource;

  public HospitalResourceEntity() {}

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public HospitalEntity getHospital() {
    return this.hospital;
  }

  public void setHospital(HospitalEntity hospitalEntity) {
    this.hospital = hospitalEntity;
  }

  public ResourceEntity getResource() {
    return this.resource;
  }

  public void setResource(ResourceEntity resourceEntity) {
    this.resource = resourceEntity;
  }

}
