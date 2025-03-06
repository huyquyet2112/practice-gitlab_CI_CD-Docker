package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.RecruitmentChanelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentChanelRepository extends JpaRepository<RecruitmentChanelEntity,Integer>, JpaSpecificationExecutor<RecruitmentChanelEntity> {
}
