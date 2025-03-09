package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.BenifitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BenifitRepository extends JpaRepository<BenifitEntity,Integer>, JpaSpecificationExecutor<BenifitEntity> {

}
