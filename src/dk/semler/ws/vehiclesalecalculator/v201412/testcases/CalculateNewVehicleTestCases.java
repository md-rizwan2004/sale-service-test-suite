package dk.semler.ws.vehiclesalecalculator.v201412.testcases;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

//import dk.semler.ws.infrastructure.service.util.ServiceEnvironment;
import dk.semler.ws.vehiclesalecalculator.v201412.calculatenewvehicle.request.CALCULATIONTYPEType;
import dk.semler.ws.vehiclesalecalculator.v201412.calculatenewvehicle.request.ECONOMYType;
import dk.semler.ws.vehiclesalecalculator.v201412.calculatenewvehicle.request.VEHICLEType;
import dk.semler.ws.vehiclesalecalculator.v201412.stubs.ExecuteServiceRequest;
import dk.semler.ws.vehiclesalecalculator.v201412.stubs.XMLService;

@RunWith(Parameterized.class)
public class CalculateNewVehicleTestCases {
	
	private static XMLService vehicleSaleCalculatorService;	
	private static JAXBContext jaxbContextInput;	
	private static JAXBContext jaxbContextOutput;
	
	private String inputmodelcode;
	private String inputmodelyear;
	private String inputfactoryequipment;
	private String expectedtotalpriceexcltaxexclvat;
	private String totaltax;
	private String totalvat;
	
	private dk.semler.ws.vehiclesalecalculator.v201412.calculatenewvehicle.request.MESSAGE requestMessage;	
	private dk.semler.ws.vehiclesalecalculator.v201412.calculatenewvehicle.response.MESSAGE responseMessage;
	
	//=====================================================
	//=    constructor reflecting data from csv file	  =
	//=====================================================
	
	public CalculateNewVehicleTestCases(String inputmodelcode, String inputmodelyear, String inputfactoryequipment, 
			String expectedtotalpriceexcltaxexclvat, String totaltax, String totalvat){
				this.inputmodelcode = inputmodelcode;
				this.inputmodelyear = inputmodelyear;
				this.inputfactoryequipment = inputfactoryequipment;
				this.expectedtotalpriceexcltaxexclvat = expectedtotalpriceexcltaxexclvat;
				this.totaltax = totaltax;
				this.totalvat = totalvat;
	}
	
	//=====================================================
	//=    scan csv file for input and expected data      =
	//=====================================================
	
	@Parameters
	public static List<Object[]> data(){
		List<Object[]> content = new ArrayList<>();
		File file = new File(CalculateNewVehicleTestCases.class.getResource("/calculatenewvehicleinput.csv").getFile());
		try(Scanner scanner = new Scanner(file)){
			scanner.nextLine();
	        while (scanner.hasNextLine()) {
	            content.add(scanner.nextLine().split(","));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	
	//=====================================================
	//=    resolve dependencies for service to be test    =
	//=====================================================

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QName qname = new QName("http://www.semlernet.dk/xmlns/xmlservice/200903/", "XMLService200903");
		Service service = Service.create(CalculateNewVehicleTestCases.class.getResource("/client/VehicleSaleCalculator.v201412.wsdl"), qname);
		vehicleSaleCalculatorService = service.getPort(XMLService.class);
		BindingProvider bp = (BindingProvider) vehicleSaleCalculatorService;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://testesb.semlernet.dk/xs/201412/VehicleSaleCalculator");
		/*if (ServiceEnvironment.isProduction()) {
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://prodesb.semlernet.dk/xs/201412/VehicleSaleCalculator");
		} else if (ServiceEnvironment.isTest()) {
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://testesb.semlernet.dk/xs/201412/VehicleSaleCalculator");			
		} else if (ServiceEnvironment.isDevelopment()) {
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://devesb.semlernet.dk/xs/201412/VehicleSaleCalculator");					
		} else if (ServiceEnvironment.isLocal()) {
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://testesb.semlernet.dk/xs/201412/VehicleSaleCalculator");	
		} else {
			throw new Exception("Could not identify environment");
		}*/

		jaxbContextInput = JAXBContext.newInstance(dk.semler.ws.vehiclesalecalculator.v201412.calculatenewvehicle.request.MESSAGE.class);
		jaxbContextOutput = JAXBContext.newInstance(dk.semler.ws.vehiclesalecalculator.v201412.calculatenewvehicle.response.MESSAGE.class);
	}
	
	//===============================================================================
	//=    make a call to service and get response to be available for each test    =
	//===============================================================================
	
	@Before
	public void setUp() throws Exception {
		dk.semler.ws.vehiclesalecalculator.v201412.calculatenewvehicle.request.MESSAGE.REQUEST request = 
				new dk.semler.ws.vehiclesalecalculator.v201412.calculatenewvehicle.request.MESSAGE.REQUEST();

		request.withDTD(request.getDTD());
		request.withNAME(request.getNAME());
		request.withDEALER("00001");
		request.withUSERID("Priceadapter");

		VEHICLEType vehicleType = new VEHICLEType();
		vehicleType.setMODELCODE(inputmodelcode);
		vehicleType.setMODELYEAR(Integer.parseInt(inputmodelyear));			
		vehicleType.setPRICECODE("J01");

		String[] factoryEquipments = inputfactoryequipment.split(Pattern.quote("|"));
		if(factoryEquipments.length > 0){
			for(String equipment : factoryEquipments){
				vehicleType.withFACTORYEQUIPMENT(equipment);					
			}
		}			
		request.withVEHICLE(vehicleType);

		ECONOMYType economyType = new ECONOMYType();
		economyType.setDELIVERYEXPENSES(new BigDecimal("2700.00"));
		economyType.setLICENSETAGFEE(new BigDecimal("1180.00"));
		request.withECONOMY(economyType);

		CALCULATIONTYPEType calculationType = new CALCULATIONTYPEType();
		calculationType.setCALCULATIONTYPE("CAR_INCL_VAT_INCL_TAX");
		calculationType.setEXCLUDEAUTOMATICSEARCHOFSTANDARDEQUIPMENT(true);
		calculationType.setEXCLUDEAUTOMATICSEARCHOFPLUSPACKAGES(true);
		request.withCALCULATIONTYPE(calculationType);

		requestMessage = new dk.semler.ws.vehiclesalecalculator.v201412.calculatenewvehicle.request.MESSAGE();
		requestMessage.withREQUEST(request);				

		StringWriter requestMessageXML = new StringWriter();			
		try {
			Marshaller marshaller = jaxbContextInput.createMarshaller();
			marshaller.marshal(requestMessage, requestMessageXML);
		} catch (JAXBException e) {
			throw e;
		}

		ExecuteServiceRequest vehiclePriceRequest = new ExecuteServiceRequest();
		vehiclePriceRequest.setConsumerId("TESTCONSUMER ");			
		vehiclePriceRequest.setInputMessage(requestMessageXML.toString());

		Unmarshaller unmarshal = jaxbContextOutput.createUnmarshaller();
		responseMessage = (dk.semler.ws.vehiclesalecalculator.v201412.calculatenewvehicle.response.MESSAGE) 
				unmarshal.unmarshal(new StringReader(vehicleSaleCalculatorService.executeService(vehiclePriceRequest).getOutputMessage()));
	}
	
	//=====================================================
	//=    				series of tests    				  =
	//=====================================================

	@Test
	public void VehicleGrandTotalPriceShouldMatch() {
		assertEquals(new BigDecimal(expectedtotalpriceexcltaxexclvat).add(new BigDecimal(totaltax)).add(new BigDecimal(totalvat)).setScale(2), 
				responseMessage.getRESPONSE().getCALCULATION().getGRANDTOTALPRICE().getINCLTAXINCLVAT().setScale(2));
	}

	@Test
	public void VehicleGrandTotalTaxShouldMatch() {
		assertEquals(new BigDecimal(totaltax).setScale(2), 
				responseMessage.getRESPONSE().getCALCULATION().getGRANDTOTALPRICE().getTAX().setScale(2));
	}

	@Test
	public void VehicleGrandTotalVATShouldMatch() {
		assertEquals(new BigDecimal(totalvat).setScale(2), 
				responseMessage.getRESPONSE().getCALCULATION().getGRANDTOTALPRICE().getVAT().setScale(2));
	}
	
	//=====================================================
	//=    reset response after each test case		      =
	//=====================================================
	
	@After
	public void tearDown(){
		responseMessage = null;
	}

}
