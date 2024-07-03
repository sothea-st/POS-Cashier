package com.example.pos.connection1.feature.attribute;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pos.connection1.entity.Attribute;
@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer>{

    Optional<Attribute> findById(Integer id);

    Optional<Attribute> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

    Page<Attribute> findByStatusTrueAndIsDeletedFalse(PageRequest pageable);

}
