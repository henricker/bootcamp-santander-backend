package com.project.bootcampsantanderbackend.repository;


import com.project.bootcampsantanderbackend.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
