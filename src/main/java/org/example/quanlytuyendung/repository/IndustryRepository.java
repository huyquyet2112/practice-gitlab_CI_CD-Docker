package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.IndustryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends JpaRepository<IndustryEntity, Integer> {
    boolean existsByCode(String code);
    Page<IndustryEntity> findAll(Pageable pageable);
}
