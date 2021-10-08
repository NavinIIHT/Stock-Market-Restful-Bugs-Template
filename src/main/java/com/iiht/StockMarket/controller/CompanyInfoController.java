package com.iiht.StockMarket.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.dto.InvalidCompanyExceptionResponse;
import com.iiht.StockMarket.exception.CompanyNotFoundException;
import com.iiht.StockMarket.exception.InvalidCompanyException;
import com.iiht.StockMarket.services.CompanyInfoService;

@RestController
@RequestMapping (value = "/company")
public class CompanyInfoController {

	@Autowired
	private CompanyInfoService companyInfoService;

	
	//-------------------------------------------------------------------------------------------------------------------------------
	@PostMapping(value="/addCompany")																					// 3. WORKING
	public ResponseEntity<CompanyDetailsDTO> addCompanyDetails(@RequestBody CompanyDetailsDTO companyDetailsDTO, BindingResult bindingResult)  {

		if(!bindingResult.hasErrors())
			throw new InvalidCompanyException("Invalid Company Details!!!");
		else
			return new ResponseEntity<CompanyDetailsDTO>(companyInfoService.saveCompanyDetails(companyDetailsDTO), HttpStatus.OK);
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/deleteCompany/{companyCode}")																// 4. WORKING
	public ResponseEntity<CompanyDetailsDTO> deleteCompanyDetails(@RequestParam("companyCode") Long companyCode) {
	
		if(companyInfoService.deleteCompany(companyCode) == null)
			throw new CompanyNotFoundException("Invalid Company Code!! Please enter valid companyCode...");
		else	
			return new ResponseEntity<CompanyDetailsDTO>(companyInfoService.deleteCompany(companyCode), HttpStatus.OK);
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/getCompanyInfoById/{companyCode}")															// 5. WORKING
	public ResponseEntity<CompanyDetailsDTO> getCompanyDetailsById(@RequestParam("companyCode") long companyCode) {
		
		if(companyInfoService.getCompanyInfoById(companyCode) != null)
			throw new CompanyNotFoundException("Invalid Company Code!! Please enter valid companyCode...");
		else	
			return new ResponseEntity<CompanyDetailsDTO>(companyInfoService.getCompanyInfoById(companyCode), HttpStatus.OK);
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/getAllCompanies", produces = "application/json")												// 6. WORKING
	public ResponseEntity<List<CompanyDetailsDTO>> getAllCompanies() {		
		return new ResponseEntity<List<CompanyDetailsDTO>>(companyInfoService.getAllCompanies(), HttpStatus.OK);
	}
	
	//================================================================================================
	//			UTITLITY EXCEPTION HANDLERS - 2
	//================================================================================================
	@ExceptionHandler(InvalidCompanyException.class)
	public ResponseEntity<InvalidCompanyExceptionResponse> companyHandler(InvalidCompanyException ex) {
		InvalidCompanyExceptionResponse resp = new InvalidCompanyExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		ResponseEntity<InvalidCompanyExceptionResponse> response =	new ResponseEntity<InvalidCompanyExceptionResponse>(resp, HttpStatus.BAD_REQUEST);
		return response;
	}
	//------------------------------------------------------------------------------------------------
	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<InvalidCompanyExceptionResponse> companyHandler(CompanyNotFoundException ex){
		InvalidCompanyExceptionResponse resp = new InvalidCompanyExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		ResponseEntity<InvalidCompanyExceptionResponse> response = new ResponseEntity<InvalidCompanyExceptionResponse>(resp, HttpStatus.NOT_FOUND);
		return response;
	}	
}