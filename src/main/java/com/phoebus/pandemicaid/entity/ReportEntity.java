package com.phoebus.pandemicaid.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "reports")
@NamedQuery(name = "ReportEntity.findAll", query = "SELECT r FROM ReportEntity r")
public class ReportEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "REPORTS_ID_GENERATOR")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPORTS_ID_GENERATOR")
  private Long id;

  private String description;


  public ReportEntity() {}

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
