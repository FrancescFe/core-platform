package com.inditex.challenge.coreplatform.prices.infrastructure.repositories;

import com.inditex.challenge.coreplatform.prices.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p " +
            "WHERE p.productId = :productId AND p.brandId = :brandId AND :checkDate " +
            "BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC")
    Optional<Price> findByProductIdAndBrandAndDateRangeOrderByPriority(
            @Param("brandId") Integer brandId,
            @Param("productId") Integer productId,
            @Param("checkDate") LocalDateTime checkDate);
}
