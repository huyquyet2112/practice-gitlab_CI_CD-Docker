package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.RequirementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepository extends JpaRepository<RequirementEntity,Integer>, JpaSpecificationExecutor<RequirementEntity> {
}
