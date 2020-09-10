package com.phoebus.pandemicaid.service.mapper;

import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.phoebus.pandemicaid.entity.ExchangeEntity;
import com.phoebus.pandemicaid.entity.FullnessEntity;
import com.phoebus.pandemicaid.model.Exchange;
import com.phoebus.pandemicaid.repository.HospitalRepository;

@Component
public class ExchangeMapper {

  @Autowired
  private HospitalRepository hospitalRepository;

  public ExchangeEntity toEntity(Exchange exchange) {
    ExchangeEntity entity = new ExchangeEntity();
    entity.setHospital1(hospitalRepository.findById(exchange.getHospitalA().getId())
        .orElseThrow(() -> new IllegalArgumentException("hospital does not exist")));
    entity.setHospital2(hospitalRepository.findById(exchange.getHospitalB().getId())
        .orElseThrow(() -> new IllegalArgumentException("hospital does not exist")));
    entity.setHospitalAFullness(entity.getHospital1().getFullnesses().stream()
        .max(Comparator.comparing(FullnessEntity::getDate))
        .orElseThrow(() -> new IllegalArgumentException("hospital does not have fullness saved"))
        .getPercentage());
    entity.setHospitalBFullness(entity.getHospital2().getFullnesses().stream()
        .max(Comparator.comparing(FullnessEntity::getDate))
        .orElseThrow(() -> new IllegalArgumentException("hospital does not have fullness saved"))
        .getPercentage());
    return entity;
  }

}
