package p3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author RachelMurray 15419045
 */
class AccommodationTest {
	// test data
	Accommodation accom;

	String nameValidLow, nameValidHigh;
	String nameInvalidLow, nameInvalidNull;

	Type hotel, hostel, bnb;

	int starsValidLow, starsValidMid, starsValidHigh;
	int starsInvalidLow, starsInvalidHigh;

	String cityValidLow, cityValidHigh;
	String cityInvalidLow, cityInvalidNull;

	double priceValidZero, priceValidLow, priceValidHigh;
	double priceInvalidLow, priceInvalidHigh;

	int roomsValidZero, roomsValidLow, roomsValidHigh;
	int roomsInvalidLow, roomsInvalidHigh;

	@BeforeEach
	void setUp() throws Exception {
		nameValidLow = "a";
		nameValidHigh = "b".repeat(5);
		nameInvalidLow = "";
		nameInvalidNull = null;

		hotel = Type.HOTEL;
		hostel = Type.HOSTEL;
		bnb = Type.BNB;

		starsValidLow = 1;
		starsValidMid = 3;
		starsValidHigh = 5;
		starsInvalidLow = 0;
		starsInvalidHigh = 6;

		cityValidLow = "a";
		cityValidHigh = "b".repeat(5);
		cityInvalidLow = "";
		cityInvalidNull = null;

		priceValidZero = 0.00;
		priceValidLow = 1.00;
		priceValidHigh = 100.00;
		priceInvalidLow = -1.00;
		priceInvalidHigh = -100.00;

		roomsValidZero = 0;
		roomsValidLow = 1;
		roomsValidHigh = 100;
		roomsInvalidLow = -1;
		priceInvalidHigh = -100;

		accom = new Accommodation(nameValidHigh, hotel, starsValidMid, cityValidHigh, priceValidHigh, roomsValidHigh);
	}

	@Test
	void testAccommodationConstructorWithArgsValid() {
		Accommodation test = new Accommodation(nameValidHigh, hotel, starsValidMid, cityValidHigh, priceValidHigh,
				roomsValidHigh);
		assertEquals(nameValidHigh, test.getName());
		assertEquals(hotel, test.getType());
		assertEquals(starsValidMid, test.getStars());
		assertEquals(cityValidHigh, test.getCity());
		assertEquals(priceValidHigh, test.getPrice());
		assertEquals(roomsValidHigh, test.getRooms());
	}

	@Test
	void testAccommodationConstructorWithArgsInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Accommodation(nameInvalidLow, hotel, starsValidMid, cityValidHigh, priceValidHigh, roomsValidHigh);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			new Accommodation(nameValidHigh, null, starsValidMid, cityValidHigh, priceValidHigh, roomsValidHigh);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			new Accommodation(nameValidHigh, hotel, starsInvalidLow, cityValidHigh, priceValidHigh, roomsValidHigh);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			new Accommodation(nameValidHigh, hotel, starsValidMid, cityInvalidLow, priceValidHigh, roomsValidHigh);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			new Accommodation(nameValidHigh, hotel, starsValidMid, cityValidHigh, priceInvalidLow, roomsValidHigh);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			new Accommodation(nameValidHigh, hotel, starsValidMid, cityValidHigh, priceValidHigh, roomsInvalidLow);
		});
	}

	@Test
	void testGetSetStarsValid() {
		accom.setStars(starsValidLow);
		assertEquals(starsValidLow, accom.getStars());

		accom.setStars(starsValidMid);
		assertEquals(starsValidMid, accom.getStars());

		accom.setStars(starsValidHigh);
		assertEquals(starsValidHigh, accom.getStars());
	}

	@Test
	void testGetSetStarsInvalid() {
		// try to remember how how to add in the code for the error message. e. 
		assertThrows(IllegalArgumentException.class, () -> {
			accom.setStars(starsInvalidLow);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			accom.setStars(starsInvalidHigh);
		});
	}

}
