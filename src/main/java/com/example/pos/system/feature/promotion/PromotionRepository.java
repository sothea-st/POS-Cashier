package com.example.pos.system.feature.promotion;

import com.example.pos.system.domain.promotion.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion,Integer> {
}
