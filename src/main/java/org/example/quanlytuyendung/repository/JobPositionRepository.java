package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.JobPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPositionEntity, Integer>, JpaSpecificationExecutor<JobPositionEntity> {
    Boolean existsByCode(String code);
}
