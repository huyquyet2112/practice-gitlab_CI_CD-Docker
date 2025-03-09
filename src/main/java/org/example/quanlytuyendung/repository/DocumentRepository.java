package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity,Integer>, JpaSpecificationExecutor<DocumentEntity> {
}
