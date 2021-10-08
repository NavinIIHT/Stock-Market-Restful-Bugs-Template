package com.iiht.StockMarket.services;

import java.util.List;

import com.iiht.StockMarket.dto.CompanyDetailsDTO;

public interface CompanyInfoService {

	public CompanyDetailsDTO saveCompanyDetails(CompanyDetailsDTO companyDetailsDTO);
	public CompanyDetailsDTO deleteCompany(Long companyCode);
	//----------------------------------------------------------------------------
	public CompanyDetailsDTO getCompanyInfoById(Long companyCode);
	public List<CompanyDetailsDTO> getAllCompanies();
}