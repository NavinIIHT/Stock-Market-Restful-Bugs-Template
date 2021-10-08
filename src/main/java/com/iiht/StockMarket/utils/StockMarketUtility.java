package com.iiht.StockMarket.utils;

import java.util.ArrayList;
import java.util.List;

import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.model.CompanyDetails;
import com.iiht.StockMarket.model.StockPriceDetails;

public class StockMarketUtility {

	//=================================================================================================================================
	//				1. Stock Price Conversion : Model to DTO - AND - DTO to Model
	//=================================================================================================================================
	public static StockPriceDetails convertToStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO)	{
		
		StockPriceDetails newStock = new StockPriceDetails();

		newStock.setId(stockPriceDetailsDTO.getId());
		newStock.setCompanyCode(stockPriceDetailsDTO.getCompanyCode());
		newStock.setCurrentStockPrice(stockPriceDetailsDTO.getCurrentStockPrice());
		newStock.setStockPriceDate(stockPriceDetailsDTO.getStockPriceDate());
		newStock.setStockPriceTime(stockPriceDetailsDTO.getStockPriceTime());

        return newStock;	
	};
	//---------------------------------------------------------------------------------------------------------------------------------
	public static StockPriceDetailsDTO convertToStockPriceDetailsDTO(StockPriceDetails stockPriceDetails)	{
		
		StockPriceDetailsDTO newStock = new StockPriceDetailsDTO();

		newStock.setId(stockPriceDetails.getId());
		newStock.setCompanyCode(stockPriceDetails.getCompanyCode());
		newStock.setCurrentStockPrice(stockPriceDetails.getCurrentStockPrice());
		newStock.setStockPriceDate(stockPriceDetails.getStockPriceDate());
		newStock.setStockPriceTime(stockPriceDetails.getStockPriceTime());

        return newStock;		
	};
	
	//=================================================================================================================================
	//				2. Company Details Conversion : Model to DTO - AND - DTO to Model
	//=================================================================================================================================
	public static CompanyDetailsDTO convertToCompanyDetailsDTO(CompanyDetails companyDetails)	{
		
		CompanyDetailsDTO newCompanyDTO = new CompanyDetailsDTO();
		
		newCompanyDTO.setCompanyCode(companyDetails.getCompanyCode());
		newCompanyDTO.setStockExchange(companyDetails.getStockExchange());
		newCompanyDTO.setCompanyName(companyDetails.getCompanyName());
		newCompanyDTO.setCompanyCEO(companyDetails.getCompanyCEO());
		newCompanyDTO.setTurnover(companyDetails.getTurnover());
		newCompanyDTO.setBoardOfDirectors(companyDetails.getBoardOfDirectors());
		newCompanyDTO.setCompanyProfile(companyDetails.getCompanyProfile());
		
		return newCompanyDTO;
	};	
	//---------------------------------------------------------------------------------------------------------------------------------
	public static CompanyDetails convertToCompanyDetails(CompanyDetailsDTO companyDetailsDTO)	{
		
		CompanyDetails newCompany = new CompanyDetails();
		
		newCompany.setCompanyCode(companyDetailsDTO.getCompanyCode());
		newCompany.setStockExchange(companyDetailsDTO.getStockExchange());
		newCompany.setCompanyName(companyDetailsDTO.getCompanyName());
		newCompany.setCompanyCEO(companyDetailsDTO.getCompanyCEO());
		newCompany.setTurnover(companyDetailsDTO.getTurnover());
		newCompany.setBoardOfDirectors(companyDetailsDTO.getBoardOfDirectors());
		newCompany.setCompanyProfile(companyDetailsDTO.getCompanyProfile());
		
		return newCompany;
	};	

	//---------------------------------------------------------------------------------------------------------------------------------
    public static List<CompanyDetailsDTO> convertToCompanyDetailsDtoList(List<CompanyDetails> companyDetailsList) {
    	
        List<CompanyDetailsDTO> list = new ArrayList<CompanyDetailsDTO>();
        
        for(CompanyDetails companyDto: companyDetailsList) {
            list.add(convertToCompanyDetailsDTO(companyDto));
        }       
        return list;
    }
	//---------------------------------------------------------------------------------------------------------------------------------
    public static List<StockPriceDetailsDTO> convertToStockPriceDetailsDtoList(List<StockPriceDetails> stockPriceDetailsList) {
    	
		List<StockPriceDetailsDTO> list = new ArrayList<StockPriceDetailsDTO>();
		
		for(StockPriceDetails stockDto : stockPriceDetailsList) {
			list.add(convertToStockPriceDetailsDTO(stockDto));
		}
		return list;
    }
}