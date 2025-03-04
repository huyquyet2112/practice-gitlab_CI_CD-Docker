package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.JobPositionEntity;
import org.example.quanlytuyendung.entity.JobPositionEntityMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPositiopMapRepository extends JpaRepository<JobPositionEntityMap, Integer> {

    void deleteByJobPosition(JobPositionEntity jobPositionEntity);

    List<JobPositionEntityMap> findByJobPosition(JobPositionEntity jobPositionEntity);
}
