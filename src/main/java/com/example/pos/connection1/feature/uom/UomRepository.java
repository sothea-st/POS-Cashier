package com.example.pos.connection1.feature.uom;

import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pos.connection1.entity.Uom;

@Repository
public interface UomRepository extends JpaRepository<Uom,Integer>{

    Optional<Uom> findById (Integer id); 

    Optional<Uom> findByIdAndStatusTrueAndIsDeletedFalse (Integer id);

    Page<Uom> findByStatusTrueAndIsDeletedFalse (PageRequest pageable);

}
