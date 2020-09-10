package com.phoebus.pandemicaid.service.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.phoebus.pandemicaid.entity.ReportEntity;
import com.phoebus.pandemicaid.model.Report;

@Component
public class ReportMapper {

  public List<Report> toModel(List<ReportEntity> entities) {
    return entities.stream().map(this::toModel).collect(Collectors.toList());
  }
  
  public Report toModel(ReportEntity entity) {
    return new Report(entity.getId(), entity.getDescription());
  }

}
