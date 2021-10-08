package com.iiht.StockMarket.functionalTestCases;

import static com.iiht.StockMarket.utilTestClass.TestUtils.businessTestFile;
import static com.iiht.StockMarket.utilTestClass.TestUtils.currentTest;
import static com.iiht.StockMarket.utilTestClass.TestUtils.yakshaAssert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.StockMarket.controller.CompanyInfoController;
import com.iiht.StockMarket.controller.StockPriceController;
import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.dto.StockPriceIndexDTO;
import com.iiht.StockMarket.services.CompanyInfoService;
import com.iiht.StockMarket.services.StockMarketService;
import com.iiht.StockMarket.utilTestClass.MasterData;

@WebMvcTest({CompanyInfoController.class, StockPriceController.class})
@AutoConfigureMockMvc
public class TestController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CompanyInfoService companyService;

	@MockBean
	private StockMarketService stockMarketService;

	

	// ------------------------------------------------------------------------------------------------------
	/*
	 * Description : This test is to perform setup for Mockito initiations
	 */
	
	
	//===========================================================================================================================
	//				I - Testing CompanyInfoController Rest End Points
	//===========================================================================================================================
	//				1. Testing Rest End Point - /company/addCompany
	//-- Test 1 : addCompany ----------------------------------------------------------------------------------------------------
	/*
	 * Description : This test is to perform add new company in the Stock Market Application
	 */
	@Test 
	public void testAddCompany() throws Exception 
	{ 
        CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
	
        Mockito.when(companyService.saveCompanyDetails(companyDto)).thenReturn(companyDto);
		
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/addCompany")
				.content(MasterData.asJsonString(companyDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
        
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(companyDto))? true : false, businessTestFile);
	}
	//-- BDD Test : addCompany --------------------------------------------------------------------------------------------------
	@SuppressWarnings("unused")
	@Test
	public void testAddCompanyBDD() throws Exception 
	{
		final int count[] = new int[1];
		
        CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		
		Mockito.when(companyService.saveCompanyDetails(companyDto)).then(new Answer<CompanyDetailsDTO>() {
			@Override
			public CompanyDetailsDTO answer(InvocationOnMock invocation) throws Throwable {
				
				count[0]++;
				return companyDto;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/addCompany")
				.content(MasterData.asJsonString(companyDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);	
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		
		yakshaAssert(currentTest(), count[0] > 0 ? true : false, businessTestFile);
	}

	//---------------------------------------------------------------------------------------------------------------------------
	//				2. Testing Rest End Point - /company/deleteCompany/{id}
	//-- Test 1 : deleteCompany -------------------------------------------------------------------------------------------------
	@Test
	public void testDeleteCompany() throws Exception
	{
        CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		Long companyCode = companyDto.getCompanyCode();
		
		Mockito.when(companyService.deleteCompany(companyCode)).thenReturn(companyDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company/deleteCompany/" + companyCode)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);				
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(companyDto))? true : false, businessTestFile);
	}
	//-- BDD Test : deleteCompany -----------------------------------------------------------------------------------------------
	@SuppressWarnings("unused")
	@Test
	public void testDeleteCompanyBDD() throws Exception 
	{
		final int count[] = new int[1];
	
        CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		Long companyCode = companyDto.getCompanyCode();
		
		Mockito.when(companyService.deleteCompany(companyCode)).then(new Answer<CompanyDetailsDTO>() {
			@Override
			public CompanyDetailsDTO answer(InvocationOnMock invocation) throws Throwable {
				
				count[0]++;
				return MasterData.getCompanyDetailsDTO();
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company/deleteCompany/" + companyCode)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		yakshaAssert(currentTest(), count[0] > 0 ? true : false, businessTestFile);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	//				3. Testing Rest End Point - /company/getCompanyInfoById/{companyCode}
	//-- Test 1 : getCompanyInfoById --------------------------------------------------------------------------------------------
	@Test
	public void testFindCompanyInfoById() throws Exception
	{
        CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		Long companyCode = companyDto.getCompanyCode();
		
		Mockito.when(companyService.getCompanyInfoById(companyCode)).thenReturn(companyDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company/getCompanyInfoById/" + companyCode)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(companyDto))? true : false, businessTestFile);		
	}
	//-- BDD Test : getCompanyInfoById ------------------------------------------------------------------------------------------
	@SuppressWarnings("unused")
	@Test
	public void testFindCompanyInfoByIdBDD() throws Exception 
	{
		final int count[] = new int[1];
		
        CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		Long companyCode = companyDto.getCompanyCode();
		
		Mockito.when(companyService.getCompanyInfoById(companyCode)).then(new Answer<CompanyDetailsDTO>() {
			@Override
			public CompanyDetailsDTO answer(InvocationOnMock invocation) throws Throwable {
				
				count[0]++;
				return MasterData.getCompanyDetailsDTO();
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company/getCompanyInfoById/" + companyCode)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		
		yakshaAssert(currentTest(), count[0] > 0 ? true : false, businessTestFile);			
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	//				4. Testing Rest End Point - /company/getAllCompanies
	//-- Test 1 : getAllCompanies -----------------------------------------------------------------------------------------------
	/*
	 * Description : This test is to perform view all the companies from database
	 */
	@Test 
	public void testFindAllCompanies() throws Exception 
	{ 
		List<CompanyDetailsDTO> list = new ArrayList<CompanyDetailsDTO>();
		list.add(MasterData.getCompanyDetailsDTO());
		
		Mockito.when(companyService.getAllCompanies()).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company/getAllCompanies")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(list))? true : false),	businessTestFile);
	}
	//-- BDD Test : getAllCompaniesBDD ------------------------------------------------------------------------------------------
	@SuppressWarnings("unused")
	@Test
	public void testFindAllCompaniesBDD() throws Exception 
	{
		final int count[] = new int[1];
		
		List<CompanyDetailsDTO> list = new ArrayList<CompanyDetailsDTO>();
		list.add(MasterData.getCompanyDetailsDTO());		
		
		Mockito.when(companyService.getAllCompanies()).then(new Answer<List<CompanyDetailsDTO>>() {
			@Override
			public List<CompanyDetailsDTO> answer(InvocationOnMock invocation) throws Throwable {
				
				count[0]++;
				return list;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company/getAllCompanies")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		
		yakshaAssert(currentTest(), count[0] > 0 ? true : false, businessTestFile);
	}
	
	//===========================================================================================================================
	//				II - Testing StockPriceController Rest End Points
	//===========================================================================================================================
	//				1. Testing Rest End Point - /stock/addStock
	
	//---------------------------------------------------------------------------------------------------------------------------
	//				2. Testing Rest End Point - /stock/deleteStock/{id}
	//-- Test 1 : deleteStock ---------------------------------------------------------------------------------------------------
	@Test
	public void testDeleteStock() throws Exception
	{
        StockPriceDetailsDTO stockDto = MasterData.getStockPriceDetailsDTO();
        Long companyCode = stockDto.getCompanyCode();

		List<StockPriceDetailsDTO> stockList = new ArrayList<StockPriceDetailsDTO>();
		stockList.add(stockDto);
        
		Mockito.when(stockMarketService.deleteStock(companyCode)).thenReturn(stockList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/stock/deleteStock/" + companyCode)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);				
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		// changed 'true : false' to 'false : true' - 29-09-21
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contains("\"currentStockPrice\":55.76")? true : false, businessTestFile);
	}
	//-- BDD Test : deleteStock --------------------------------------------------------------------------------------------------
	@SuppressWarnings("unused")
	@Test
	public void testDeleteStockBDD() throws Exception 
	{
		final int count[] = new int[1];
	
        StockPriceDetailsDTO stockDto = MasterData.getStockPriceDetailsDTO();
        Long companyCode = stockDto.getCompanyCode();

		List<StockPriceDetailsDTO> stockList = new ArrayList<StockPriceDetailsDTO>();
		stockList.add(stockDto);
		
		Mockito.when(stockMarketService.deleteStock(companyCode)).then(new Answer<List<StockPriceDetailsDTO>>() {
			@Override
			public List<StockPriceDetailsDTO> answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return stockList;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/stock/deleteStock/" + companyCode)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		yakshaAssert(currentTest(), count[0] > 0 ? true : false, businessTestFile);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	//				3. Testing Rest End Point - /stock/getStockInfoById/{companyCode}
	//-- Test 1 : getStockInfoById ----------------------------------------------------------------------------------------------
	@Test
	public void testFindStockByCompanyCode() throws Exception
	{
        StockPriceDetailsDTO stockDto = MasterData.getStockPriceDetailsDTO();
        Long companyCode = stockDto.getCompanyCode();
        
		List<StockPriceDetailsDTO> stockList = new ArrayList<StockPriceDetailsDTO>();
		stockList.add(stockDto);

		Mockito.when(stockMarketService.getStockByCode(companyCode)).thenReturn(stockList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/getStockByCompanyCode/" + companyCode)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		// changed 'true : false' to 'false : true' - 29-09-21
		
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contains("\"currentStockPrice\":55.76")? true : false, businessTestFile);		
	}
	//-- BDD Test : getStockByCompanyCode ---------------------------------------------------------------------------------------
	@SuppressWarnings("unused")
	@Test
	public void testFindStockByCompanyCodeBDD() throws Exception 
	{
		final int count[] = new int[1];
		
        StockPriceDetailsDTO stockDto = MasterData.getStockPriceDetailsDTO();
        Long companyCode = stockDto.getCompanyCode();

		List<StockPriceDetailsDTO> stockList = new ArrayList<StockPriceDetailsDTO>();
		stockList.add(stockDto);
		Mockito.when(stockMarketService.getStockByCode(companyCode)).then(new Answer<List<StockPriceDetailsDTO>>() {
			@Override
			public List<StockPriceDetailsDTO> answer(InvocationOnMock invocation) throws Throwable {
				
				count[0]++;
				return stockList;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/getStockByCompanyCode/" + companyCode)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		
		yakshaAssert(currentTest(), count[0] > 0 ? true : false, businessTestFile);
	}	
	
	//---------------------------------------------------------------------------------------------------------------------------
	//				5. Testing Rest End Point - /stock/getStockPriceIndex
	//-- Test 1 : getStockPriceIndex --------------------------------------------------------------------------------------------
	/*
	 * Description : This test is to perform view the StockPriceIndex from database
	 */
	@Test 
	public void testStockPriceIndex() throws Exception 
	{ 
        StockPriceIndexDTO stockPriceIndexDto = MasterData.getStockPriceIndexDTO();
        
        CompanyDetailsDTO companyDetailDTO = stockPriceIndexDto.getCompanyDto();
        Long companyCode = companyDetailDTO.getCompanyCode();
        
        List<StockPriceDetailsDTO> stockPDDTOList = stockPriceIndexDto.getStockPriceList();
        StockPriceDetailsDTO spDetails1 = stockPDDTOList.get(0);
        StockPriceDetailsDTO spDetails2 = stockPDDTOList.get(1);
        
        LocalDate startDate = spDetails1.getStockPriceDate();
        LocalDate endDate   = spDetails2.getStockPriceDate();

        //StockPriceIndexDTO stockPriceIndexDTO = new StockPriceIndexDTO();
        
		Mockito.when(stockMarketService.getStockPriceIndex(companyCode, startDate, endDate)).thenReturn(stockPriceIndexDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/getStockPriceIndex/"+companyCode+"/"+startDate+"/"+endDate)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contains("\"companyCode\":1001")? true : false, businessTestFile);		
	}
	//-- BDD Test : getStockPriceIndex ------------------------------------------------------------------------------------------
	@SuppressWarnings("unused")
	@Test
	public void testStockPriceIndexBDD() throws Exception 
	{
		final int count[] = new int[1];
	
        StockPriceIndexDTO stockPriceIndexDTO = new StockPriceIndexDTO();
        
        StockPriceIndexDTO stockPriceDto = MasterData.getStockPriceIndexDTO();
        
        CompanyDetailsDTO companyDetailDTO = stockPriceDto.getCompanyDto();
        Long companyCode = companyDetailDTO.getCompanyCode();
        
        List<StockPriceDetailsDTO> stockPDDTOList = stockPriceDto.getStockPriceList();
        
        StockPriceDetailsDTO spDetails1 = stockPDDTOList.get(0);
        StockPriceDetailsDTO spDetails2 = stockPDDTOList.get(1);
        
        LocalDate startDate = spDetails1.getStockPriceDate();
        LocalDate endDate   = spDetails2.getStockPriceDate();

		Mockito.when(stockMarketService.getStockPriceIndex(companyCode, startDate, endDate)).then(new Answer<StockPriceIndexDTO>() {
			@Override
			public StockPriceIndexDTO answer(InvocationOnMock invocation) throws Throwable {
				
				count[0]++;
				return stockPriceIndexDTO;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/getStockPriceIndex/"+companyCode+"/"+startDate+"/"+endDate)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	
		yakshaAssert(currentTest(), count[0] >0 ? true : false, businessTestFile);
	}	
}