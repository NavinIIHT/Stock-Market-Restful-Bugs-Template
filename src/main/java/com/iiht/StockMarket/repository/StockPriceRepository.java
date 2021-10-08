package com.iiht.StockMarket.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

import com.iiht.StockMarket.model.StockPriceDetails;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPriceDetails, Long> {
	
	
}