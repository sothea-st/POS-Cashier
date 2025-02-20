package com.example.pos.system.feature.promotion;

import com.example.pos.system.domain.promotion.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion,Integer> {

    Page<Promotion> findByStatusTrueAndIsDeletedFalse(PageRequest pageRequest);

    @Query("SELECT p FROM Promotion p " +
            "INNER JOIN p.createdBy u " +
            "WHERE p.status = true " +
            "AND p.isDeleted = false " +
            "AND LOWER(u.fullName) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Promotion> findByUserName(PageRequest pageRequest,@Param("name") String name);

    Optional<Promotion> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

}
