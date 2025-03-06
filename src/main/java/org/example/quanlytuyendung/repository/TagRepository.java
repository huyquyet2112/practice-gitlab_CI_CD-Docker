package org.example.quanlytuyendung.repository;

import io.micrometer.core.instrument.Tag;
import org.example.quanlytuyendung.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer>, JpaSpecificationExecutor<TagEntity> {
    Boolean existsByName(String name);
}
