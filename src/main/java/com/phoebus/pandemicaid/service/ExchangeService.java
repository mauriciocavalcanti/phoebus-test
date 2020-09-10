package com.phoebus.pandemicaid.service;

import java.math.BigDecimal;
import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.phoebus.pandemicaid.entity.ExchangeEntity;
import com.phoebus.pandemicaid.entity.ExchangeResourceEntity;
import com.phoebus.pandemicaid.entity.FullnessEntity;
import com.phoebus.pandemicaid.entity.HospitalEntity;
import com.phoebus.pandemicaid.entity.HospitalResourceEntity;
import com.phoebus.pandemicaid.model.Exchange;
import com.phoebus.pandemicaid.model.Hospital;
import com.phoebus.pandemicaid.model.Resource;
import com.phoebus.pandemicaid.repository.ExchangeRepository;
import com.phoebus.pandemicaid.repository.ExchangeResourceRepository;
import com.phoebus.pandemicaid.repository.FullnessRepository;
import com.phoebus.pandemicaid.repository.HospitalResourceRepository;
import com.phoebus.pandemicaid.service.mapper.ExchangeMapper;

@Service
public class ExchangeService {

  @Autowired
  private ExchangeRepository exchangeRepository;

  @Autowired
  private ExchangeResourceRepository exchangeResourceRepository;

  @Autowired
  private HospitalResourceRepository hospitalResourceRepository;

  @Autowired
  private ExchangeMapper exchangeMapper;

  @Autowired
  private FullnessRepository fullnessRepository;

  @Transactional
  public Exchange postExchange(Exchange exchange) {
    Integer sumResourcesA =
        exchange.getHospitalA().getResources().stream().mapToInt(Resource::getWeight).sum();
    Integer sumResourcesB =
        exchange.getHospitalB().getResources().stream().mapToInt(Resource::getWeight).sum();
    BigDecimal currentFullnessA = getCurrentFullness(exchange.getHospitalA());
    BigDecimal currentFullnessB = getCurrentFullness(exchange.getHospitalB());
    if (isExchangeable(sumResourcesA, sumResourcesB, currentFullnessA, currentFullnessB)) {
      ExchangeEntity exchangeEntity = exchangeMapper.toEntity(exchange);
      exchange.setId(exchangeRepository.save(exchangeEntity).getId());
      exchangeEntity.setId(exchange.getId());
      exchange(exchange.getHospitalA(), exchange.getHospitalB(), exchangeEntity);
      exchange(exchange.getHospitalB(), exchange.getHospitalA(), exchangeEntity);
    } else {
      throw new IllegalArgumentException("Hospitals can not exchange resources");
    }
    return exchange;
  }

  private BigDecimal getCurrentFullness(Hospital hospital) {
    return fullnessRepository.findByHospital(new HospitalEntity(hospital.getId())).stream()
        .max(Comparator.comparing(FullnessEntity::getDate))
        .orElseThrow(() -> new IllegalArgumentException("hospital does not have fullness saved"))
        .getPercentage();
  }

  private boolean isExchangeable(Integer sumResourcesA, Integer sumResourcesB,
      BigDecimal currentFullnessA, BigDecimal currentFullnessB) {
    return (sumResourcesA.equals(sumResourcesB)
        && currentFullnessA.compareTo(new BigDecimal(90)) < 0
        && currentFullnessB.compareTo(new BigDecimal(90)) < 0)
        || (currentFullnessA.compareTo(new BigDecimal(90)) > 0 && sumResourcesA <= sumResourcesB
            && currentFullnessB.compareTo(new BigDecimal(90)) < 0)
        || (currentFullnessB.compareTo(new BigDecimal(90)) > 0 && sumResourcesB <= sumResourcesA
            && currentFullnessA.compareTo(new BigDecimal(90)) < 0);
  }

  private void exchange(Hospital hospitalA, Hospital hospitalB, ExchangeEntity exchangeEntity) {
    hospitalA.getResources().forEach(r -> {
      ExchangeResourceEntity exchangeResourceEntity = new ExchangeResourceEntity();
      exchangeResourceEntity.setExchange(exchangeEntity);
      exchangeResourceEntity.setHospital(new HospitalEntity(hospitalA.getId()));
      exchangeResourceEntity.setName(r.getName());
      exchangeResourceEntity.setWeight(r.getWeight());
      exchangeResourceRepository.save(exchangeResourceEntity);
      HospitalResourceEntity hospitalResourceEntity = hospitalResourceRepository
          .findByIdAndHospital(r.getId(), new HospitalEntity(hospitalA.getId()))
          .orElseThrow(() -> new IllegalArgumentException("resource does not exist"));
      hospitalResourceEntity.setHospital(new HospitalEntity(hospitalB.getId()));
      hospitalResourceRepository.save(hospitalResourceEntity);
    });
  }

}
