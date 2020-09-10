package com.phoebus.pandemicaid.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "resources")
@NamedQuery(name = "ResourceEntity.findAll", query = "SELECT r FROM ResourceEntity r")
public class ResourceEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "RESOURCES_ID_GENERATOR")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESOURCES_ID_GENERATOR")
  private Long id;

  private String name;

  private Integer weight;

  // bi-directional many-to-one association to HospitalResource
  @OneToMany(mappedBy = "resource")
  private List<HospitalResourceEntity> hospitalResources;

  public ResourceEntity() {}

  public ResourceEntity(Long id, String name, Integer weight) {
    super();
    this.id = id;
    this.name = name;
    this.weight = weight;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
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

  public List<HospitalResourceEntity> getHospitalResources() {
    return this.hospitalResources;
  }

  public void setHospitalResources(List<HospitalResourceEntity> hospitalResourceEntities) {
    this.hospitalResources = hospitalResourceEntities;
  }

  public HospitalResourceEntity addHospitalResource(HospitalResourceEntity hospitalResourceEntity) {
    getHospitalResources().add(hospitalResourceEntity);
    hospitalResourceEntity.setResource(this);

    return hospitalResourceEntity;
  }

  public HospitalResourceEntity removeHospitalResource(
      HospitalResourceEntity hospitalResourceEntity) {
    getHospitalResources().remove(hospitalResourceEntity);
    hospitalResourceEntity.setResource(null);

    return hospitalResourceEntity;
  }

}
