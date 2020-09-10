package com.phoebus.pandemicaid.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.phoebus.pandemicaid.entity.FullnessEntity;
import com.phoebus.pandemicaid.entity.HospitalEntity;

@Repository
public interface FullnessRepository extends JpaRepository<FullnessEntity, Long> {

  List<FullnessEntity> findByHospital(HospitalEntity hospitalEntity);

}
