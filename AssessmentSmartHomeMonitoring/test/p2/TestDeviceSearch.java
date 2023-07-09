package p2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDeviceSearch {
	
	// test data 
	ArrayList<Device> devicesList; 
	ArrayList<Smartradiator> radiatorsList;
	
	Smartradiator r1, r2, r3, r4, r5, r6, r7;
	Room house, kitchen, bedroom, bathroom, lounge; 

	@BeforeEach
	void setUp() throws Exception {
		devicesList = new ArrayList<Device>();
		radiatorsList = new ArrayList<Smartradiator>();
		house = Room.HOUSE;
		kitchen = Room.KICTHEN;
		bedroom = Room.BEDROOM;
		bathroom = Room.BATHROOM;
		lounge = Room.LOUNGE;
		
		r1 = new Smartradiator("name", "man", house, Powerstate.ON, 12, 15);
		r2 = new Smartradiator("name", "man", kitchen, Powerstate.ON, 15.6, 15);
		r3 = new Smartradiator("name", "man", bedroom, Powerstate.ON, 21.7, 15);
		r4 = new Smartradiator("name", "man", house, Powerstate.ON, 22, 15);
		r5 = new Smartradiator("name", "man", bedroom, Powerstate.ON, 22.1, 15);
		r6 = new Smartradiator("name", "man", house, Powerstate.ON, 14.9, 15);
		r7 = new Smartradiator("name", "man", kitchen, Powerstate.ON, 15.0, 15);
		
		devicesList.add(r1);
		devicesList.add(r2);
		devicesList.add(r3);
		devicesList.add(r4);
		devicesList.add(r5);
		devicesList.add(r6);
		devicesList.add(r7);
		
		radiatorsList.add(r1);
		radiatorsList.add(r2);
		radiatorsList.add(r3);
		radiatorsList.add(r4);
		radiatorsList.add(r5);
		radiatorsList.add(r6);
		radiatorsList.add(r7);
		
	}

	@Test
	void testSearchByRoomValid() {
		ArrayList<Device> results = DeviceSearch.searchByRoom(devicesList, kitchen);
		assertTrue(results.contains(r2));
		assertTrue(results.contains(r7));
		assertTrue(results.size()==2);
		
		results = DeviceSearch.searchByRoom(devicesList, house);
		assertTrue(results.contains(r1));
		assertTrue(results.contains(r4));
		assertTrue(results.contains(r6));
		assertTrue(results.size()==3);
		
		results = DeviceSearch.searchByRoom(devicesList, lounge);
		assertTrue(results.size()==0);

	}
	
	@Test 
	void searchByRoomInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, ()->{
			ArrayList<Device> results = DeviceSearch.searchByRoom(null, kitchen);
			});
		String expected = "Input list cannor be null";
		assertEquals(expected, exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, ()->{
			ArrayList<Device> results = DeviceSearch.searchByRoom(devicesList, null);
			});
		expected = "Cannot search for null";
		assertEquals(expected, exp.getMessage());
	}

	@Test
	void testSearchByTemp() {
		ArrayList<Smartradiator> results = DeviceSearch.searchByTemp(radiatorsList, 15, 22);
		assertTrue(results.contains(r2));
		assertTrue(results.contains(r3));
		assertTrue(results.contains(r4));
		assertTrue(results.contains(r7));
		assertTrue(results.size()==4);
		
		results = DeviceSearch.searchByTemp(radiatorsList, 12, 16.5);
		assertTrue(results.contains(r1));
		assertTrue(results.contains(r2));
		assertTrue(results.contains(r6));
		assertTrue(results.contains(r7));
		assertTrue(results.size()==4);
		
		results = DeviceSearch.searchByTemp(radiatorsList, 5, 10);
		assertTrue(results.size()==0);
	}
	
	@Test 
	void testSearchByTempInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, ()->{
			ArrayList<Smartradiator> results = DeviceSearch.searchByTemp(radiatorsList, 22, 15);
			});
		String expected = "Low target must be smaller than high or search";
		assertEquals(expected, exp.getMessage());
		
		
		
	}

}
