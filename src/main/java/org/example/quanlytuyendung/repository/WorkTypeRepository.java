package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.WorkTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkTypeRepository extends JpaRepository<WorkTypeEntity,Integer>, JpaSpecificationExecutor<WorkTypeEntity> {
}
