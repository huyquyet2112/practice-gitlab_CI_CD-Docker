package org.example.quanlytuyendung.repository;

import org.example.quanlytuyendung.entity.EmailTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplateEntity,Integer>, JpaSpecificationExecutor<EmailTemplateEntity> {
}
