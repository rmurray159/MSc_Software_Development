package p2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRadiator {
	
	// test data 
	String stringValidLow, stringValidMid, stringValidHigh; 
	String stringInvalidLow, stringInvalidHigh;
	Room house, kitchen, bedroom, bathroom, lounge; 
	Powerstate on, off; 
	double tempValidLow, tempValidMid, tempValidHigh; 
	double tempInvalidLow, tempInvalidHigh; 
	double targetValidLow, targetValidMid, targetValidHigh; 
	double targetInvalidLow, targetInvalidHigh;
	Smartradiator radiator; 

	@BeforeEach
	void setUp() throws Exception {
		stringValidLow = "abc";
		stringValidMid = "a".repeat(10);
		stringValidHigh= "z".repeat(20);
		stringInvalidLow= "ab";
		stringInvalidHigh = "x".repeat(21);
		
		house = Room.HOUSE;
		kitchen = Room.KICTHEN;
		bedroom = Room.BEDROOM;
		bathroom = Room.BATHROOM;
		lounge = Room.LOUNGE;
		
		on = Powerstate.ON;
		off = Powerstate.OFF;
		
		tempValidLow = -10; 
		tempValidMid = 15.5;
		tempValidHigh = 30;
		tempInvalidLow = -10.1; 
		tempInvalidHigh = 30.1;
		targetValidLow = 5;
		targetValidMid= 13.5;
		targetValidHigh = 26;
		targetInvalidLow = 4.9;
		targetInvalidHigh = 26.1;
		
		radiator = new Smartradiator(stringValidMid, stringValidMid, bathroom, on, tempValidMid, targetValidMid);
	}

	@Test
	void testStatus() {
	Smartradiator temp = new Smartradiator("name", "man", Room.BATHROOM, Powerstate.OFF, 28.9, 30.87);
	String expected = "SR etc eetc ";
	assertEquals(expected, temp.status());
	}

	@Test
	void testSmartradiatorConstructorValid() {
		Smartradiator test = new Smartradiator(stringValidMid, stringValidMid, bathroom, on, tempValidMid, targetValidMid);
		assertEquals(stringValidMid, test.getName());
		assertEquals(stringValidMid, test.getManufacturer());
		assertEquals(bathroom, test.getRoom());
		assertEquals(on, test.getPowerstate());
		assertEquals(tempValidMid, test.getTempNow());
		assertEquals(targetValidMid, test.getTempTarget());
	
	}
	
	@Test
	void testSmartradiatorConstructorInvalid() {
		assertThrows(IllegalArgumentException.class, ()->{
			new Smartradiator(stringInvalidLow, stringValidMid, bathroom, on, tempValidMid, targetValidMid);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Smartradiator(stringValidMid, stringInvalidHigh, bathroom, on, tempValidMid, targetValidMid);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Smartradiator(stringValidMid, stringValidMid, null, on, tempValidMid, targetValidMid);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Smartradiator(stringValidMid, stringValidMid, bathroom, null, tempValidMid, targetValidMid);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Smartradiator(stringValidMid, stringValidMid, bathroom, on, tempInvalidLow, targetValidMid);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Smartradiator(stringValidMid, stringValidMid, bathroom, on, tempValidMid, targetInvalidHigh);
		});
		
	}

	@Test
	void testSetTempNowValid() {
		radiator.setTempNow(tempValidLow);
		assertEquals(tempValidLow, radiator.getTempNow());
		
		radiator.setTempNow(tempValidMid);
		assertEquals(tempValidMid, radiator.getTempNow());
		
		radiator.setTempNow(tempValidHigh);
		assertEquals(tempValidHigh, radiator.getTempNow());
	}

	@Test
	void testSetTempTarget() {
		radiator.setTempTarget(targetValidLow);
		assertEquals(targetValidLow, radiator.getTempTarget());
		
		radiator.setTempTarget(targetValidMid);
		assertEquals(targetValidMid, radiator.getTempTarget());
		
		radiator.setTempTarget(targetValidHigh);
		assertEquals(targetValidHigh, radiator.getTempTarget());
	}

	@Test
	void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	void testSetManufacturer() {
		fail("Not yet implemented");
	}

	@Test
	void testSetRoom() {
		fail("Not yet implemented");
	}
	
	@Test
	void testTempNowComplex() {
		radiator.setPowerstate(off);
		radiator.setTempTarget(20);
		radiator.setTempNow(14); // set lower than target 
		// should change to on 
		assertEquals(on, radiator.getPowerstate());
		
		radiator.setTempNow(25);
		// should change to off 
		assertEquals(off, radiator.getPowerstate());
		
		radiator.setTempTarget(23.3);
		radiator.setPowerstate(off); // set to same as target should turn off 
		assertEquals(off, radiator.getPowerstate());
	}

}
