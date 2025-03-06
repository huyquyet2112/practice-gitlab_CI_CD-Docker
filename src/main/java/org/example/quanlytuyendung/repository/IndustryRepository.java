package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.IndustryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface IndustryRepository extends JpaRepository<IndustryEntity, Integer>, JpaSpecificationExecutor<IndustryEntity> {
    Boolean existsByCode(String code);
}


