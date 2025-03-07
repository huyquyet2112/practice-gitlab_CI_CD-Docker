package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.RecruitmentRoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentRoundRepository extends JpaRepository<RecruitmentRoundEntity,Integer> , JpaSpecificationExecutor<RecruitmentRoundEntity> {
}
