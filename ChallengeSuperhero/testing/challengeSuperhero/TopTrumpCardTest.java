package challengeSuperhero;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TopTrumpCardTest {
 // test data 
	TopTrumpCard card1;

	String nameVLow, nameVMid, nameVHigh;
	String nameIVLow, nameIVHigh, nameIVNull;

	String fileNameVLow, fileNameVMid, fileNameVHigh;
	String fileNameIVLow, fileNameIVHigh, fileNameIVSuffix, fileNameIVSpace;

	Category hero, villain;

	int valueVLow, valueVMid, valueVHigh;
	int valueIVLow, valueIVHigh;

	String bioVLow, bioVMid, bioVHigh;
	String bioIV;

	@BeforeEach
	void setUp() throws Exception {

		// variables for both name and real name are the same as they have the same
		// business rules
		nameVLow = "a";
		nameVMid = "b".repeat(15);
		nameVHigh = "c".repeat(30);
		nameIVLow = "";
		nameIVHigh = "x".repeat(31);
		nameIVNull = null; // can just put null in the constructor, don't need to have a variable for it

		fileNameVLow = "a.jpg";
		fileNameVMid = ("bbbbbbbbbbb.jpg"); // b*11
		fileNameVHigh = ("ccccccccccccccccccccccccc.jpg"); // c*26
		fileNameIVLow = ".jpg";
		fileNameIVHigh = ("xxxxxxxxxxxxxxxxxxxxxxxxxxx.jpg"); // x*27
		fileNameIVSuffix = "a".repeat(5); // does not have .jpg suffix
		fileNameIVSpace = "a .jpg";

		hero = Category.HERO;
		villain = Category.VILLAIN;

		valueVLow = 0;
		valueVMid = 50;
		valueVHigh = 100;
		valueIVLow = -1;
		valueIVHigh = 101;

		bioVLow = "";
		bioVMid = "a";
		bioVHigh = "a".repeat(20);
		bioIV = null;

		card1 = new TopTrumpCard(nameVMid, nameVMid, fileNameVMid, hero, valueVMid, valueVMid, valueVMid, valueVMid,
				bioVMid);
	}

	@Test
	void testTopTrumpCardConstructorWithArgsValid() {
		TopTrumpCard test = new TopTrumpCard(nameVMid, nameVMid, fileNameVMid, hero, valueVMid, valueVMid, valueVMid, valueVMid,
				bioVMid); 
		assertEquals(nameVMid, test.getName());
		assertEquals(nameVMid, test.getRealname());
		assertEquals(fileNameVMid, test.getImageName());
		assertEquals(hero, test.getCategory());
		assertEquals(valueVMid, test.getSpeed());
		assertEquals(valueVMid, test.getStrength());
		assertEquals(valueVMid, test.getAgility());
		assertEquals(valueVMid, test.getIntelligence());
		assertEquals(bioVMid, test.getBio());

	}

	@Test
	void testTopTrumpCardConstructorWithArgsInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			new TopTrumpCard(nameIVLow, nameVMid, fileNameVMid, hero, valueVMid, valueVMid, valueVMid,
					valueVMid, bioVMid);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new TopTrumpCard(nameVMid, nameIVLow, fileNameVMid, hero, valueVMid, valueVMid, valueVMid,
					valueVMid, bioVMid);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new TopTrumpCard(nameVMid, nameVMid, fileNameIVLow, hero, valueVMid, valueVMid, valueVMid,
					valueVMid, bioVMid);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new TopTrumpCard(nameVMid, nameVMid, fileNameVMid, null, valueVMid, valueVMid, valueVMid, valueVMid,
					bioVMid);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new TopTrumpCard(nameVMid, nameVMid, fileNameVMid, hero, valueIVLow, valueVMid, valueVMid,
					valueVMid, bioVMid);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			 new TopTrumpCard(nameVMid, nameVMid, fileNameVMid, hero, valueVMid, valueIVLow, valueVMid,
					valueVMid, bioVMid);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new TopTrumpCard(nameVMid, nameVMid, fileNameVMid, hero, valueVMid, valueVMid, valueIVLow,
					valueVMid, bioVMid);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new TopTrumpCard(nameVMid, nameVMid, fileNameVMid, hero, valueVMid, valueVMid, valueVMid,
					valueIVLow, bioVMid);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new TopTrumpCard(nameVMid, nameVMid, fileNameVMid, hero, valueVMid, valueVMid, valueVMid, valueVMid,
					null);
		});
	}

	@Test
	void testSetNameValid() {
		card1.setName(nameVLow);
		assertEquals(nameVLow, card1.getName());
		card1.setName(nameVMid);
		assertEquals(nameVMid, card1.getName());
		card1.setName(nameVHigh);
		assertEquals(nameVHigh, card1.getName());
	}

	@Test
	void testSetNameInvalid() {

		assertThrows(IllegalArgumentException.class, () -> {
			card1.setName(nameIVLow);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			card1.setName(nameIVHigh);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			card1.setName(null);
		});

	}

	@Test
	void testSetRealNameValid() {
		card1.setRealname(nameVLow);
		assertEquals(nameVLow, card1.getRealname());
		card1.setRealname(nameVMid);
		assertEquals(nameVMid, card1.getRealname());
		card1.setRealname(nameVHigh);
		assertEquals(nameVHigh, card1.getRealname());
	}

	@Test
	void testSetRealNameInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			card1.setRealname(nameIVLow);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			card1.setRealname(nameIVHigh);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			card1.setRealname(null);
		});
	}

	@Test
	void testSetFileNameValid() {
		card1.setImageName(fileNameVLow);
		assertEquals(fileNameVLow, card1.getImageName());
		card1.setImageName(fileNameVMid);
		assertEquals(fileNameVMid, card1.getImageName());
		card1.setImageName(fileNameVHigh);
		assertEquals(fileNameVHigh, card1.getImageName());

	}

	@Test
	void testGetSetFileNameInvalid() {

		assertThrows(IllegalArgumentException.class, () -> {
			card1.setImageName(fileNameIVLow);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			card1.setImageName(fileNameIVHigh);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			card1.setImageName(fileNameIVSuffix);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			card1.setImageName(fileNameIVSpace);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			card1.setImageName(null);
		});
	}

	@Test
	void testGetSetCategoryValid() {
		card1.setCategory(hero);
		assertEquals(hero, card1.getCategory());
		card1.setCategory(villain);
		assertEquals(villain, card1.getCategory());
	}

	@Test
	void testCategoryInvalid() {

		assertThrows(IllegalArgumentException.class, () -> {
			card1.setCategory(null);
		});

	}

	@Test
	void testGetSetSpeedValid() {
		card1.setSpeed(valueVLow);
		assertEquals(valueVLow, card1.getSpeed());
		card1.setSpeed(valueVMid);
		assertEquals(valueVMid, card1.getSpeed());
		card1.setSpeed(valueVHigh);
		assertEquals(valueVHigh, card1.getSpeed());

	}

	@Test
	void testSetSpeedInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			card1.setSpeed(valueIVLow);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			card1.setSpeed(valueIVHigh);
		});

	}
//DONT NEED TO TEST THE REST OF THE ATTRIBUTE INTS FOR TIME PURPOSES
//	@Test
//	void testSetStrength() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSetAgility() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSetIntelligence() {
//		fail("Not yet implemented");
//	}

	@Test
	void testSetBioValid() {
		card1.setBio(bioVLow);
		assertEquals(bioVLow, card1.getBio());
		card1.setBio(bioVMid);
		assertEquals(bioVMid, card1.getBio());
		card1.setBio(bioVHigh);
		assertEquals(bioVHigh, card1.getBio());
	}

	@Test
	void testSetBioInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			card1.setBio(null);
		});
	}

	@Test
	void testToString() {

		String expected = "TopTrumpCard [name=Helen, realName=Helenmc, fileName=helen.jpg, category=HERO, speed=10, strength=20, agility=30, intelligence=40, bio=slim shady]";
		card1 = new TopTrumpCard("Helen", "Helenmc", "helen.jpg", hero, 10, 20, 30, 40, "slim shady");
		assertEquals(expected, card1.toString());
	}

	@Test
	void testGetMaxStatID() {

		card1.setSpeed(50);
		card1.setStrength(30);
		card1.setAgility(40);
		card1.setIntelligence(20);
		assertEquals(0, card1.getMaxStatID());
		
		card1.setSpeed(20);
		card1.setStrength(50);
		card1.setAgility(40);
		card1.setIntelligence(30);
		assertEquals(1, card1.getMaxStatID());
		
		card1.setSpeed(20);
		card1.setStrength(30);
		card1.setAgility(40);
		card1.setIntelligence(10);
		assertEquals(2, card1.getMaxStatID());
		
		card1.setSpeed(20);
		card1.setStrength(30);
		card1.setAgility(40);
		card1.setIntelligence(50);
		assertEquals(3, card1.getMaxStatID());
	}

	@Test
	void testGetStatScore() {

		assertEquals(card1.getSpeed(), card1.getStatScore(0));
		assertEquals(card1.getStrength(), card1.getStatScore(1));
		assertEquals(card1.getAgility(), card1.getStatScore(2));
		assertEquals(card1.getIntelligence(), card1.getStatScore(3));
	}

}
