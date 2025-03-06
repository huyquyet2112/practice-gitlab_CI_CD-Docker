package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.CandicateSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CandicateSourceRepository extends JpaRepository<CandicateSourceEntity, Integer>, JpaSpecificationExecutor<CandicateSourceEntity> {
}
