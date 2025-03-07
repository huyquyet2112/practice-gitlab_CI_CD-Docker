package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.RecruitmentChanelEntity;
import org.example.quanlytuyendung.entity.RecruitmentRoundTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentRoundTypeRepository extends JpaRepository<RecruitmentRoundTypeEntity,Integer>, JpaSpecificationExecutor<RecruitmentRoundTypeEntity> {
}
