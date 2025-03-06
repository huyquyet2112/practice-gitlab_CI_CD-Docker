package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.ReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonRepository extends JpaRepository<ReasonEntity, Integer>, JpaSpecificationExecutor<ReasonEntity> {
}
