package com.iiht.StockMarket.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.exception.CompanyNotFoundException;
import com.iiht.StockMarket.model.CompanyDetails;
import com.iiht.StockMarket.repository.CompanyInfoRepository;
import com.iiht.StockMarket.utils.StockMarketUtility;


@Transactional
public class CompanyInfoServiceImpl implements CompanyInfoService {
	
	@Autowired
	private CompanyInfoRepository repository; 
	
	public CompanyDetailsDTO saveCompanyDetails(CompanyDetailsDTO companyDetailsDTO) {

		CompanyDetails newCompany = StockMarketUtility.convertToCompanyDetails(companyDetailsDTO);
		
		repository.save(newCompany);
		
		return companyDetailsDTO;
	};
	//----------------------------------------------------------------------------
	public CompanyDetailsDTO deleteCompany(Long companyCode) {
		
		Integer value = repository.deleteByCompanyCode(companyCode);
		
		if(value == null)
			return (CompanyDetailsDTO) getCompanyInfoById(companyCode);
		else
			throw new CompanyNotFoundException("No Company Found in the Database...");
	};
	//----------------------------------------------------------------------------
	public CompanyDetailsDTO getCompanyInfoById(Long companyCode) {
		
		CompanyDetails companyInfo = repository.findCompanyDetailsById(companyCode);

		return StockMarketUtility.convertToCompanyDetailsDTO(companyInfo);
	};
	
	//----------------------------------------------------------------------------
	public List<CompanyDetailsDTO> getAllCompanies() {

		List<CompanyDetails> companyInfo = repository.findAll();

		if(!CollectionUtils.isEmpty(companyInfo))
			return null;
		else
			return companyInfo.stream().map(StockMarketUtility::convertToCompanyDetailsDTO).collect(Collectors.toCollection(null));
	};
}