/**
 * 
 */
package p3;

import java.util.Objects;

/**
*@author RachelMurray 15419045
*/
/**
 * @author rache Class for Accommodation object
 */
public class Accommodation {

	// constants for business rules
	private static int MIN_LENGTH = 1;
	private static int MIN_STARS = 1;
	private static int MAX_STARS = 5;

	// instance variables
	private String name;
	private Type type;
	private int stars;
	private String city;
	private double price;
	private int rooms;

	// Constructor
	/**
	 * @param name
	 * @param type
	 * @param stars
	 * @param city
	 * @param price
	 * @param rooms
	 */
	public Accommodation(String name, Type type, int stars, String city, double price, int rooms) throws IllegalAccessError{
		this.setName(name);
		this.setType(type);
		this.setStars(stars);
		this.setCity(city);
		this.setPrice(price);
		this.setRooms(rooms);
	}

	// getters/ setters
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
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null");
		} else if (name.length() < MIN_LENGTH) {
			throw new IllegalArgumentException("Name must be at least 1 character");
		} else {
			this.name = name;
		}
	}
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Type type) throws IllegalArgumentException {
		if (type == null) {
			throw new IllegalArgumentException();
		} else {
			this.type = type;
		}
	}
	/**
	 * @return the stars
	 */
	public int getStars() {
		return stars;
	}
	/**
	 * @param stars the stars to set
	 */
	public void setStars(int stars) {
		if (stars >= MIN_STARS && stars <= MAX_STARS) {
			this.stars = stars;
		} else {
			throw new IllegalArgumentException("Stars value must be between 1-5 inclusive");
		}
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) throws IllegalArgumentException {
		if (city == null) {
			throw new IllegalArgumentException("City cannot be null");
		} else if (city.length() < MIN_LENGTH) {
			throw new IllegalArgumentException("City must be at least 1 character");
		} else {
			this.city = city;
		}
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		if (price < 0) {
			throw new IllegalArgumentException("Price cannot be a negative value");
		} else {
			this.price = price;
		}
	}

	/**
	 * @return the rooms
	 */
	public int getRooms() {
		return rooms;
	}

	/**
	 * @param rooms the rooms to set
	 */
	public void setRooms(int rooms) {
		if (rooms < 0) {
			throw new IllegalArgumentException("Rooms cannot be a negative value");
		} else {
			this.rooms = rooms;
		}
	}

	// helper methods
	
	public void printDetails() {
		System.out.println("Name:     \t"+ this.getName());
		System.out.println("City:     \t"+ this.getCity());
		System.out.println("Type:     \t" + this.getType());
		System.out.printf("Capacity: \t %d Rooms @ £%.2f", this.getRooms(), this.getPrice());
		System.out.println("Rating: " + getStars() + "stars"); // come back to make it ****
		System.out.println();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(city, name, price, rooms, stars, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accommodation other = (Accommodation) obj;
		return Objects.equals(city, other.city) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && rooms == other.rooms
				&& stars == other.stars && type == other.type;
	}

	@Override
	public String toString() {
		return "Accommodation [name=" + name + ", type=" + type + ", stars=" + stars + ", city=" + city + ", price="
				+ price + ", rooms=" + rooms + "]";
	}

}
