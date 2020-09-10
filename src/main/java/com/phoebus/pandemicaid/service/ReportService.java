package com.phoebus.pandemicaid.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.phoebus.pandemicaid.entity.ExchangeEntity;
import com.phoebus.pandemicaid.entity.ExchangeResourceEntity;
import com.phoebus.pandemicaid.entity.FullnessEntity;
import com.phoebus.pandemicaid.entity.HospitalEntity;
import com.phoebus.pandemicaid.entity.ResourceEntity;
import com.phoebus.pandemicaid.model.Exchange;
import com.phoebus.pandemicaid.model.Report;
import com.phoebus.pandemicaid.model.Resource;
import com.phoebus.pandemicaid.repository.ExchangeRepository;
import com.phoebus.pandemicaid.repository.ExchangeResourceRepository;
import com.phoebus.pandemicaid.repository.HospitalRepository;
import com.phoebus.pandemicaid.repository.ReportRepository;
import com.phoebus.pandemicaid.repository.ResourceRepository;
import com.phoebus.pandemicaid.service.mapper.HospitalMapper;
import com.phoebus.pandemicaid.service.mapper.ReportMapper;

@Service
public class ReportService {

  private static final String COULD_NOT_BE_FOUND = "could not be found";

  private static final String REPORT_WITH_ID = "Report with id: ";

  @Autowired
  private ReportRepository reportRepository;

  @Autowired
  private HospitalRepository hospitalRepository;

  @Autowired
  private ResourceRepository resourceRepository;

  @Autowired
  private ExchangeRepository exchangeRepository;

  @Autowired
  private ExchangeResourceRepository exchangeResourceRepository;

  @Autowired
  private ReportMapper reportMapper;

  @Autowired
  private HospitalMapper hospitalMapper;

  public List<Report> getReports(Long... ids) {
    if (ids == null) {
      return reportMapper.toModel(reportRepository.findAll());
    }
    List<Report> reports = new ArrayList<>();
    List<HospitalEntity> hospitalEntities = hospitalRepository.findAll();
    for (Long id : ids) {
      Report report;
      switch (id.intValue()) {
        case 1:
          percentageOfFullness(reports, hospitalEntities, id);
          break;
        case 2:
          percentageOfFullness(reports, hospitalEntities, id);
          break;
        case 3:
          List<ResourceEntity> resourceEntities = resourceRepository.findAll();
          Map<String, BigDecimal> mapAverage = new HashMap<>();
          for (ResourceEntity resourceEntity : resourceEntities) {
            mapAverage.put(resourceEntity.getName(), new BigDecimal(0));
          }
          hospitalEntities.forEach(h -> h.getHospitalResources().forEach(hr -> {
            BigDecimal oldCounter = mapAverage.get(hr.getResource().getName());
            if (oldCounter != null) {
              mapAverage.put(hr.getResource().getName(), oldCounter.add(new BigDecimal(1)));
            }
          }));
          for (Map.Entry<String, BigDecimal> entry : mapAverage.entrySet()) {
            entry.setValue(entry.getValue().divide(new BigDecimal(hospitalEntities.size())));
          }
          report = reportMapper.toModel(reportRepository.findById(id).orElseThrow(
              () -> new UnsupportedOperationException(REPORT_WITH_ID + id + COULD_NOT_BE_FOUND)));
          report.setAverageResources(mapAverage);
          reports.add(report);
          break;
        case 4:
          getHospitalWithFullness(reports, hospitalEntities, id);
          break;
        case 5:
          getHospitalWithFullness(reports, hospitalEntities, id);
          break;
        case 6:
          List<ExchangeEntity> exchangeEntities = exchangeRepository.findAll();
          List<Exchange> exchanges = exchangeEntities.stream().map(e -> {
            Exchange exchange = new Exchange();
            exchange.setId(e.getId());
            exchange.setHospitalA(hospitalMapper.toModel(e.getHospital1()));
            exchange.setHospitalAFullness(e.getHospitalAFullness());
            List<ExchangeResourceEntity> exchangeResourceEntity =
                exchangeResourceRepository.findByExchange(e);
            List<Resource> resourcesA = new ArrayList<>();
            exchangeResourceEntity.forEach(ex -> {
              Resource resource = new Resource();
              resource.setName(ex.getName());
              resource.setWeight(ex.getWeight());
              resourcesA.add(resource);
            });
            exchange.getHospitalA().setResources(resourcesA);
            exchange.setHospitalB(hospitalMapper.toModel(e.getHospital2()));
            exchange.setHospitalBFullness(e.getHospitalBFullness());
            List<Resource> resourcesB = new ArrayList<>();
            exchangeResourceEntity.forEach(ex -> {
              Resource resource = new Resource();
              resource.setName(ex.getName());
              resource.setWeight(ex.getWeight());
              resourcesB.add(resource);
            });
            exchange.getHospitalB().setResources(resourcesB);
            return exchange;
          }).collect(Collectors.toList());
          report = reportMapper.toModel(reportRepository.findById(id).orElseThrow(
              () -> new UnsupportedOperationException(REPORT_WITH_ID + id + COULD_NOT_BE_FOUND)));
          report.setExchanges(exchanges);
          reports.add(report);
          break;
        default:
          break;
      }
    }
    return reports;
  }

  private void getHospitalWithFullness(List<Report> reports, List<HospitalEntity> hospitalEntities,
      Long id) {
    Report report;
    Map<HospitalEntity, LocalDateTime> mapHospital = new HashMap<>();
    hospitalEntities.forEach(h -> {
      h.getFullnesses().sort((f1, f2) -> f1.getDate().compareTo(f2.getDate()));
      Collections.reverse(h.getFullnesses());
      if (id.equals(4L)) {
        h.getFullnesses().forEach(f -> {
          if (f.getPercentage().compareTo(new BigDecimal(90)) >= 0) {
            mapHospital.put(h, f.getDate());
          } else {
            return;
          }
        });
      } else {
        h.getFullnesses().forEach(f -> {
          if (f.getPercentage().compareTo(new BigDecimal(90)) < 0) {
            mapHospital.put(h, f.getDate());
          } else {
            return;
          }
        });
      }
    });
    HospitalEntity hospitalEntity =
        mapHospital.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue))
            .orElseThrow(() -> new IllegalArgumentException("no hospital above fullness")).getKey();
    report = reportMapper.toModel(reportRepository.findById(id).orElseThrow(
        () -> new UnsupportedOperationException(REPORT_WITH_ID + id + COULD_NOT_BE_FOUND)));
    report.setHospital(hospitalMapper.toModel(hospitalEntity));
    reports.add(report);
  }

  private void percentageOfFullness(List<Report> reports, List<HospitalEntity> hospitalEntities,
      Long id) {
    List<BigDecimal> currentFullnesses = new ArrayList<>();
    hospitalEntities.forEach(h -> currentFullnesses
        .add(h.getFullnesses().stream().max(Comparator.comparing(FullnessEntity::getDate))
            .orElse(new FullnessEntity()).getPercentage()));
    BigDecimal percentage;
    if (id.equals(1L)) {
      percentage = new BigDecimal(100)
          .multiply(new BigDecimal(
              currentFullnesses.stream().filter(c -> c.compareTo(new BigDecimal(90)) >= 0).count()))
          .divide(new BigDecimal(currentFullnesses.size()));
    } else {
      percentage = new BigDecimal(100)
          .multiply(new BigDecimal(
              currentFullnesses.stream().filter(c -> c.compareTo(new BigDecimal(90)) < 0).count()))
          .divide(new BigDecimal(currentFullnesses.size()));
    }
    Report report = reportMapper.toModel(reportRepository.findById(id).orElseThrow(
        () -> new UnsupportedOperationException(REPORT_WITH_ID + id + COULD_NOT_BE_FOUND)));
    report.setPercentage(percentage);
    reports.add(report);
  }

}
