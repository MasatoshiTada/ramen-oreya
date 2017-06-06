package com.example.repository;

import com.example.entity.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderSummaryRepository extends JpaRepository<OrderSummary, Integer> {

    @Query("SELECT DISTINCT os FROM OrderSummary os JOIN FETCH os.orderDetails WHERE os.shopId = :shopId AND os.provided = FALSE ORDER BY os.orderTimestamp ASC")
    List findAllByShopIdNotProvided(@Param("shopId") String shopId);

    @Transactional(propagation = Propagation.REQUIRED)
    @Modifying
    @Query("UPDATE OrderSummary os SET os.provided = TRUE WHERE os.summaryId = :summaryId")
    void updateProvided(@Param("summaryId") Integer summaryId);
}
