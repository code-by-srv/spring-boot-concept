package com.codingsrv.LearningJPA.repository;

import com.codingsrv.LearningJPA.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByTitle(String pepsi);

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime after);

    List<ProductEntity> findByQuantityAndPrice(Integer quantity, BigDecimal price);


    List<ProductEntity> findByQuantityGreaterThanOrPriceGreaterThan(int quantity, BigDecimal price);

    List<ProductEntity> findByTitleContaining(String title);

    @Query("select e from ProductEntity e where e.title=?1 and e.price=?2")
    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);


}

