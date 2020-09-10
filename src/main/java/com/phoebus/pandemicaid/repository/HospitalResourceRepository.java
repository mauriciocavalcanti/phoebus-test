package com.phoebus.pandemicaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.phoebus.pandemicaid.entity.HospitalResourceEntity;

@Repository
public interface HospitalResourceRepository extends JpaRepository<HospitalResourceEntity, Long> {

}
