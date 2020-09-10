package com.phoebus.pandemicaid.service.mapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.phoebus.pandemicaid.entity.FullnessEntity;
import com.phoebus.pandemicaid.entity.HospitalEntity;
import com.phoebus.pandemicaid.model.Address;
import com.phoebus.pandemicaid.model.Hospital;

@Component
public class HospitalMapper {

  public HospitalEntity toEntity(Hospital hospital) {
    HospitalEntity entity = new HospitalEntity();
    entity.setId(hospital.getId());
    entity.setCep(hospital.getAddress().getCep());
    entity.setCity(hospital.getAddress().getCity());
    entity.setCnpj(hospital.getCnpj());
    entity.setFullnesses(Arrays.asList(new FullnessEntity(hospital.getCurrentFullness(), LocalDateTime.now())));
    entity.setLatitude(hospital.getAddress().getLatitude());
    entity.setLongitude(hospital.getAddress().getLongitude());
    entity.setName(hospital.getName());
    entity.setNeighborhood(hospital.getAddress().getNeighborhood());
    entity.setNumber(hospital.getAddress().getNumber());
    entity.setState(hospital.getAddress().getState());
    entity.setStreet(hospital.getAddress().getStreet());
    return entity;
  }

  public Hospital toModel(HospitalEntity entity) {
    Hospital hospital = new Hospital();
    hospital.setCnpj(entity.getCnpj());
    hospital.setCurrentFullness(
        entity.getFullnesses().stream().max(Comparator.comparing(FullnessEntity::getDate))
            .orElse(new FullnessEntity()).getPercentage());
    hospital.setId(entity.getId());
    hospital.setName(entity.getName());
    Address address = new Address();
    address.setCep(entity.getCep());
    address.setCity(entity.getCity());
    address.setLatitude(entity.getLatitude());
    address.setLongitude(entity.getLongitude());
    address.setNeighborhood(entity.getNeighborhood());
    address.setNumber(entity.getNumber());
    address.setState(entity.getState());
    address.setStreet(entity.getStreet());
    hospital.setAddress(address);
    return hospital;
  }

  public List<Hospital> toModel(List<HospitalEntity> entities) {
    return entities.stream().map(this::toModel).collect(Collectors.toList());
  }

}
