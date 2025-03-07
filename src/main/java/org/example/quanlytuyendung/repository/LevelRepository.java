package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.LevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<LevelEntity, Integer>, JpaSpecificationExecutor<LevelEntity> {
}
