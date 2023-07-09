/**
 * 
 */
package p2;

/**
 * @author rache name 7 student number
 */
public abstract class Device {

	private static int MIN_LENGTH = 3;
	private static int MAX_LENGTH = 20;

	private String name;
	private String manufacturer;
	private Room room;
	private Powerstate powerstate;

	/**
	 * @param name
	 * @param manufacturer
	 * @param room
	 * @param powerstate
	 */
	public Device(String name, String manufacturer, Room room, Powerstate powerstate) {
		this.setName(name);
		this.setManufacturer(manufacturer);
		this.setPowerstate(powerstate);
		this.setRoom(room);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) throws IllegalArgumentException {
		if (validateString(name)) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("Invalid name set, out of range or null");
		}
	}

	/**
	 * Private method to check if string is within allowable parameters
	 * 
	 * @param input
	 * @return
	 */
	private boolean validateString(String input) {
		if (input == null) {
			return false; // not valid
		} else if (input.length() < MIN_LENGTH || input.length() > MAX_LENGTH) {
			return false; // not valid wrong length
		} else {
			return true;
		}
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) throws IllegalArgumentException{
		if (validateString(manufacturer)) {
			this.manufacturer = manufacturer;
		} else {
			throw new IllegalArgumentException("Invalid manufacturer set, out of range or null");
		}
	}

	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(Room room) throws IllegalArgumentException{
		if (room == null) {
			throw new IllegalArgumentException("Can't set room to null");
		} else {
			this.room = room;
		}

	}

	/**
	 * @return the powerstate
	 */
	public Powerstate getPowerstate() {
		return powerstate;
	}

	/**
	 * @param powerstate the powerstate to set
	 */
	public void setPowerstate(Powerstate powerstate) throws IllegalArgumentException{
		if (powerstate == null) {
			throw new IllegalArgumentException("Can't set powerstate to null");
		} else {
			this.powerstate = powerstate;
		}
	}

	/**
	 * print details to console
	 */
	public void showAll() {
		System.out.println("NAME       :" + getName());
		System.out.println("MANUFACTURER       :" + getManufacturer());
		System.out.println("POWERSTATE       :" + getPowerstate());
		System.out.println("ROOM       :" + getRoom());
	}

	public abstract String status();

}
