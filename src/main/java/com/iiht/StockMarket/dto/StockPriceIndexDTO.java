package com.iiht.StockMarket.dto;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StockPriceIndexDTO {

	
	private CompanyDetailsDTO companyDto;

	
	private List<StockPriceDetailsDTO> stockPriceList;

	
	private Double maxStockPrice;

	
	private Double minStockPrice;

	
	private Double avgStockPrice;

	//--------------------------------------------------------------------------------------------------------------------------
	public StockPriceIndexDTO() {
		super();
	}
	public StockPriceIndexDTO(@NotNull CompanyDetailsDTO companyDto,
			@NotNull List<@NotBlank StockPriceDetailsDTO> stockPriceList,
			@NotNull @Digits(integer = 10, fraction = 2, message = "Stock Price must have precision 10 and factional part of 2 decimals") Double maxStockPrice,
			@NotNull @Digits(integer = 10, fraction = 2, message = "Stock Price must have precision 10 and factional part of 2 decimals") Double minStockPrice,
			@NotNull @Digits(integer = 10, fraction = 2, message = "Stock Price must have precision 10 and factional part of 2 decimals") Double avgStockPrice) {
		super();
		this.companyDto = companyDto;
		this.stockPriceList = stockPriceList;
		this.maxStockPrice = maxStockPrice;
		this.minStockPrice = minStockPrice;
		this.avgStockPrice = avgStockPrice;
	}
	//--------------------------------------------------------------------------------------------------------------------------
	public CompanyDetailsDTO getCompanyDto() {
		return companyDto;
	}
	public void setCompanyDto(CompanyDetailsDTO companyDto) {
		this.companyDto = companyDto;
	}
	//--------------------------------------------------------------------------------------------------------------------------
	public List<StockPriceDetailsDTO> getStockPriceList() {
		return stockPriceList;
	}
	public void setStockPriceList(List<StockPriceDetailsDTO> stockPriceList) {
		this.stockPriceList = stockPriceList;
	}
	//--------------------------------------------------------------------------------------------------------------------------
	public Double getMaxStockPrice() {
		return maxStockPrice;
	}
	public void setMaxStockPrice(Double maxStockPrice) {
		this.maxStockPrice = maxStockPrice;
	}
	//--------------------------------------------------------------------------------------------------------------------------
	public Double getMinStockPrice() {
		return minStockPrice;
	}
	public void setMinStockPrice(Double minStockPrice) {
		this.minStockPrice = minStockPrice;
	}
	//--------------------------------------------------------------------------------------------------------------------------
	public Double getAvgStockPrice() {
		return avgStockPrice;
	}
	public void setAvgStockPrice(Double avgStockPrice) {
		this.avgStockPrice = avgStockPrice;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avgStockPrice == null) ? 0 : avgStockPrice.hashCode());
		result = prime * result + ((companyDto == null) ? 0 : companyDto.hashCode());
		result = prime * result + ((maxStockPrice == null) ? 0 : maxStockPrice.hashCode());
		result = prime * result + ((minStockPrice == null) ? 0 : minStockPrice.hashCode());
		result = prime * result + ((stockPriceList == null) ? 0 : stockPriceList.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockPriceIndexDTO other = (StockPriceIndexDTO) obj;
		if (avgStockPrice == null) {
			if (other.avgStockPrice != null)
				return false;
		} else if (!avgStockPrice.equals(other.avgStockPrice))
			return false;
		if (companyDto == null) {
			if (other.companyDto != null)
				return false;
		} else if (!companyDto.equals(other.companyDto))
			return false;
		if (maxStockPrice == null) {
			if (other.maxStockPrice != null)
				return false;
		} else if (!maxStockPrice.equals(other.maxStockPrice))
			return false;
		if (minStockPrice == null) {
			if (other.minStockPrice != null)
				return false;
		} else if (!minStockPrice.equals(other.minStockPrice))
			return false;
		if (stockPriceList == null) {
			if (other.stockPriceList != null)
				return false;
		} else if (!stockPriceList.equals(other.stockPriceList))
			return false;
		return true;
	}
	
}