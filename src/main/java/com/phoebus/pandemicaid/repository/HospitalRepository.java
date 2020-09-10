package com.phoebus.pandemicaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.phoebus.pandemicaid.entity.HospitalEntity;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {

}
