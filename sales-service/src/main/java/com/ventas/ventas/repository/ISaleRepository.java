package com.ventas.ventas.repository;

import com.ventas.ventas.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByUserId(String userId);
}
