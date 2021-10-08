package com.iiht.StockMarket.utilTestClass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.dto.StockPriceDetailsDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class JSONUtils 
{
    public static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    public static CompanyDetailsDTO createCompanyDetailaDTO(Long companyCode, String stockExchange, String companyName, String companyCEO, Double turnover, String boardOfDirectors, String companyProfile) 
    {
    	CompanyDetailsDTO companyDetails = new CompanyDetailsDTO();

    	companyDetails.setCompanyCode(companyCode);
    	companyDetails.setStockExchange(stockExchange);
    	companyDetails.setCompanyName(companyName);
    	companyDetails.setCompanyCEO(companyCEO);
    	companyDetails.setTurnover(turnover);
    	companyDetails.setBoardOfDirectors(boardOfDirectors);
    	companyDetails.setCompanyProfile(companyProfile);

    	return companyDetails;
    }
    
    public static StockPriceDetailsDTO createStockPriceDetailsDTO(Long id, Long companyCode, Double currentStockPrice, LocalDate stockPriceDate, LocalTime stockPriceTime) 
    {
    	StockPriceDetailsDTO stockPrice = new StockPriceDetailsDTO();

    	stockPrice.setId(id);
    	stockPrice.setCompanyCode(companyCode);
    	stockPrice.setCurrentStockPrice(currentStockPrice);
    	stockPrice.setStockPriceDate(stockPriceDate);
    	stockPrice.setStockPriceTime(stockPriceTime);
  	 	
    	return stockPrice;
    }
}