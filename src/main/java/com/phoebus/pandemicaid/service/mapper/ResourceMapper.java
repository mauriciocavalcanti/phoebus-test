package com.phoebus.pandemicaid.service.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.phoebus.pandemicaid.entity.HospitalResourceEntity;
import com.phoebus.pandemicaid.model.Resource;
import com.phoebus.pandemicaid.repository.ResourceRepository;

@Component
public class ResourceMapper {

  @Autowired
  private ResourceRepository resourceRepository;

  public List<HospitalResourceEntity> toEntity(List<Resource> resources) {
    return resources.stream().map(this::toEntity).collect(Collectors.toList());
  }

  public HospitalResourceEntity toEntity(Resource resource) {
    HospitalResourceEntity entity = new HospitalResourceEntity();
    entity.setResource(resourceRepository.findByNameIgnoreCase(resource.getName()));
    return entity;
  }

  public Resource toModel(HospitalResourceEntity entity) {
    Resource resource = new Resource();
    resource.setId(entity.getId());
    resource.setName(entity.getResource().getName());
    resource.setWeight(entity.getResource().getWeight());
    return resource;
  }

  public List<Resource> toModel(List<HospitalResourceEntity> entities) {
    return entities.stream().map(this::toModel).collect(Collectors.toList());
  }

}
