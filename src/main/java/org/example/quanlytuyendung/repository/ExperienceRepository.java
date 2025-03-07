package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.ExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<ExperienceEntity, Integer>, JpaSpecificationExecutor<ExperienceEntity> {
}
