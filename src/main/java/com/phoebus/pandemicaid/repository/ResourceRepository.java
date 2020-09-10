package com.phoebus.pandemicaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.phoebus.pandemicaid.entity.ResourceEntity;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {

  ResourceEntity findByNameIgnoreCase(String name);

}
