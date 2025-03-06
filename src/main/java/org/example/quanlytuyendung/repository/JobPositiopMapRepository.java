package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.JobPositionEntity;
import org.example.quanlytuyendung.entity.JobPositionEntityMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPositiopMapRepository extends JpaRepository<JobPositionEntityMap, Integer>, JpaSpecificationExecutor<JobPositionEntityMap> {



    List<JobPositionEntityMap> findByJobPosition(JobPositionEntity jobPositionEntity);
}
