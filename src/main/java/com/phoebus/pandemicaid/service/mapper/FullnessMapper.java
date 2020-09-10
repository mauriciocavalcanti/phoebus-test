package com.phoebus.pandemicaid.service.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.phoebus.pandemicaid.entity.FullnessEntity;
import com.phoebus.pandemicaid.model.Fullness;

@Component
public class FullnessMapper {

  public List<Fullness> toModel(List<FullnessEntity> entities) {
    return entities.stream().map(this::toModel).collect(Collectors.toList());
  }

  public Fullness toModel(FullnessEntity entity) {
    Fullness fullness = new Fullness();
    fullness.setDate(entity.getDate());
    fullness.setId(entity.getId());
    fullness.setPercentage(entity.getPercentage());
    return fullness;
  }

}
