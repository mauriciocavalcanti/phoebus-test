package com.phoebus.pandemicaid.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Report {

  private Long id;
  private String description;
  private BigDecimal percentage;
  private Map<String, BigDecimal> averageResources;
  private Hospital hospital;
  private List<Exchange> exchanges;

  public Report() {}

  public Report(Long id, String description) {
    super();
    this.id = id;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPercentage() {
    return percentage;
  }

  public void setPercentage(BigDecimal percentage) {
    this.percentage = percentage;
  }

  public Map<String, BigDecimal> getAverageResources() {
    return averageResources;
  }

  public void setAverageResources(Map<String, BigDecimal> averageResources) {
    this.averageResources = averageResources;
  }

  public Hospital getHospital() {
    return hospital;
  }

  public void setHospital(Hospital hospital) {
    this.hospital = hospital;
  }

  public List<Exchange> getExchanges() {
    return exchanges;
  }

  public void setExchanges(List<Exchange> exchanges) {
    this.exchanges = exchanges;
  }

}
