package com.example.pos.connection1.feature.status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pos.connection1.entity.Status;
import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status,Integer>{
    Optional<Status> findById(Integer id);

    Optional<Status> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

    Page<Status> findByStatusTrueAndIsDeletedFalse (PageRequest pageable);
}
