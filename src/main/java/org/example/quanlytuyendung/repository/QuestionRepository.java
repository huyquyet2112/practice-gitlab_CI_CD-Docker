package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> , JpaSpecificationExecutor<QuestionEntity> {
}
