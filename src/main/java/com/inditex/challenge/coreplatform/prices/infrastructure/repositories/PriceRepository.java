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

    @Query(value =
            "SELECT * FROM prices p " +
                    "WHERE p.product_id = :productId AND p.brand_id = :brandId " +
                    "AND :checkDate BETWEEN p.start_date AND p.end_date " +
                    "ORDER BY p.priority DESC LIMIT 1", nativeQuery = true)
    Optional<Price> findTopByProductIdAndBrandAndDateRangeOrderByPriority(
            @Param("brandId") Integer brandId,
            @Param("productId") Integer productId,
            @Param("checkDate") LocalDateTime checkDate);
}
