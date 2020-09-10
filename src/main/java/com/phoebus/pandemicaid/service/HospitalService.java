package com.phoebus.pandemicaid.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.phoebus.pandemicaid.entity.FullnessEntity;
import com.phoebus.pandemicaid.entity.HospitalEntity;
import com.phoebus.pandemicaid.entity.HospitalResourceEntity;
import com.phoebus.pandemicaid.model.Fullness;
import com.phoebus.pandemicaid.model.Hospital;
import com.phoebus.pandemicaid.model.Resource;
import com.phoebus.pandemicaid.repository.FullnessRepository;
import com.phoebus.pandemicaid.repository.HospitalRepository;
import com.phoebus.pandemicaid.repository.HospitalResourceRepository;
import com.phoebus.pandemicaid.service.mapper.FullnessMapper;
import com.phoebus.pandemicaid.service.mapper.HospitalMapper;
import com.phoebus.pandemicaid.service.mapper.ResourceMapper;

@Service
public class HospitalService {

  @Autowired
  private HospitalMapper hospitalMapper;

  @Autowired
  private ResourceMapper resourceMapper;

  @Autowired
  private HospitalRepository hospitalRepository;
  
  @Autowired
  private FullnessRepository fullnessRepository;
  
  @Autowired
  private HospitalResourceRepository hospitalResourceRepository;
  
  @Autowired
  private FullnessMapper fullnessMapper;

  @Transactional
  public Hospital postHospital(Hospital hospital) {
    HospitalEntity hospitalEntity = hospitalMapper.toEntity(hospital);
    hospital.setId(hospitalRepository.save(hospitalEntity).getId());
    List<HospitalResourceEntity> hospitalResourceEntity = resourceMapper.toEntity(hospital.getResources());
    hospitalResourceEntity.forEach(h -> h.setHospital(new HospitalEntity(hospital.getId())));
    hospitalResourceRepository.saveAll(hospitalResourceEntity);
    hospitalEntity.getFullnesses().forEach(f -> f.setHospital(new HospitalEntity(hospital.getId())));
    fullnessRepository.saveAll(hospitalEntity.getFullnesses());
    return hospital;
  }

  public Fullness postFullness(Long hospitalId, Fullness fullness) {
    FullnessEntity fullnessEntity = new FullnessEntity();
    fullnessEntity.setHospital(new HospitalEntity(hospitalId));
    fullnessEntity.setPercentage(fullness.getPercentage());
    fullnessEntity.setDate(LocalDateTime.now());
    fullness.setId(fullnessRepository.save(fullnessEntity).getId());
    fullness.setDate(fullnessEntity.getDate());
    return fullness;
  }

  public List<Fullness> getFullnesses(Long hospitalId) {
    List<FullnessEntity> entities = fullnessRepository.findByHospital(new HospitalEntity(hospitalId));
    return fullnessMapper.toModel(entities);
  }

  public List<Hospital> getHospitals() {
    List<HospitalEntity> entities = hospitalRepository.findAll();
    List<Hospital> hospitals = new ArrayList<>();
    entities.forEach(p -> {
      List<Resource> resources = resourceMapper.toModel(p.getHospitalResources());
      Hospital hospital = hospitalMapper.toModel(p);
      hospital.setResources(resources);
      hospitals.add(hospital);
    });
    return hospitals;
  }

}
