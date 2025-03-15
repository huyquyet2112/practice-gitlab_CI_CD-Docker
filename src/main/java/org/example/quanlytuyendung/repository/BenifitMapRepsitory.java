package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.BenifitEntity;
import org.example.quanlytuyendung.entity.BenifitMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BenifitMapRepsitory extends JpaRepository<BenifitMapEntity, Integer> , JpaSpecificationExecutor<BenifitMapEntity> {
    List<BenifitMapEntity> findByBenifit(BenifitEntity benifit);

    List<BenifitMapEntity> findAllByBenifit(BenifitEntity benifit);

    Optional<BenifitMapEntity> findByBenifitAndDepartment(BenifitEntity benifit, int departmentId);

    void deleteByBenifit(BenifitEntity benifit);
}
