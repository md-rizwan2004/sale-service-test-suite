package dk.semler.ws.vehiclesalecalculator.v201412.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import dk.semler.ws.vehiclesalecalculator.v201412.testcases.CalculateNewVehicleTestCases;

//=====================================================
//=    test suite to run both test classes			  =
//=====================================================

@RunWith(Suite.class)
@SuiteClasses({ 
	CalculateNewVehicleTestCases.class
})
public class VehicleSaleCalculatorSuite {

}
