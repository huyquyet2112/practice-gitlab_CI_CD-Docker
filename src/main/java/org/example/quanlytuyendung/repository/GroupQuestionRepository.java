package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.GroupQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupQuestionRepository extends JpaRepository<GroupQuestionEntity,Integer> , JpaSpecificationExecutor<GroupQuestionEntity> {
}
