package com.phoebus.pandemicaid.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.phoebus.pandemicaid.entity.ExchangeEntity;
import com.phoebus.pandemicaid.entity.ExchangeResourceEntity;

@Repository
public interface ExchangeResourceRepository extends JpaRepository<ExchangeResourceEntity, Long> {
  
  List<ExchangeResourceEntity> findByExchange(ExchangeEntity exchangeEntity);

}
