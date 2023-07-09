package challengeSuperhero;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TopTrumpCard {

	// constants for business rules
	private static int MIN_LENGTH = 1;
	private static int MIN_LENGTH_IMG = 5;
	private static int MAX_LENGTH = 30;
	private static int MIN_ATT = 0;
	private static int MAX_ATT = 100;

	// instance variable
	private String name;
	private String realname;
	private String imageName;
	private Category category;
	private int speed;
	private int strength;
	private int agility;
	private int intelligence;
	private String bio;

	// constructor
	/**
	 * @param name
	 * @param realname
	 * @param imageName
	 * @param category
	 * @param speed
	 * @param strength
	 * @param agility
	 * @param intelligence
	 * @param bio
	 */
	public TopTrumpCard(String name, String realname, String imageName, Category category, int speed, int strength,
			int agility, int intelligence, String bio) {
		this.setName(name);
		this.setRealname(realname);
		this.setName(imageName);
		this.setCategory(category);
		this.setSpeed(speed);
		this.setStrength(strength);
		this.setAgility(agility);
		this.setIntelligence(intelligence);
		this.setBio(bio);
	}
	// getters & setters

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
			throw new IllegalArgumentException("Name can't be null");
			// apply business rules
		} else if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
			throw new IllegalArgumentException("Invalid name length");
		} else {
			this.name = name;
		}
	}

	/**
	 * @return the realname
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * @param realname the realname to set
	 */
	public void setRealname(String realname) throws IllegalArgumentException  {
		if (realname == null) {
			throw new IllegalArgumentException("Name can't be null");
			// apply business rules
		} else if (realname.length() < MIN_LENGTH || realname.length() > MAX_LENGTH) {
			throw new IllegalArgumentException("Invalid name length");
		} else {
			this.realname = realname;
		}
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) throws IllegalArgumentException  {
		if (imageName.length() < MIN_LENGTH_IMG || imageName.length() > MAX_LENGTH) {
			throw new IllegalArgumentException("Name lenght has to be between 5-30 characters");
		} else if (imageName.contains(" ")) {
			throw new IllegalArgumentException("File name cannot contain spaces");
		} else if (!imageName.endsWith(".jpg")) {
			throw new IllegalArgumentException("File must not end with .jpg");
		} else {
			this.imageName = imageName;
		}
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) throws IllegalArgumentException  {
		if (category == null) {
			throw new IllegalArgumentException("Can't set room to null");
		} else {
			this.category = category;
		}
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) throws IllegalArgumentException  {
		if (speed >= MIN_ATT && speed <= MAX_ATT)  {
			this.speed = speed;
		} else {
			throw new IllegalArgumentException("Attribute value cannot be outside range 0-100");
		}
	}

	/**
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * @param strength the strength to set
	 */
	public void setStrength(int strength) throws IllegalArgumentException  {
		if (strength >= MIN_ATT && strength <= MAX_ATT) {
			this.strength = strength;
		} else {
			throw new IllegalArgumentException("Attribute value cannot be outside range 0-100");
		}
	}

	/**
	 * @return the agility
	 */
	public int getAgility() {
		return agility;
	}

	/**
	 * @param agility the agility to set
	 */
	public void setAgility(int agility) throws IllegalArgumentException  {
		if (agility >= MIN_ATT && agility <= MAX_ATT) {
			this.agility = agility;
		} else {
			throw new IllegalArgumentException("Attribute value cannot be outside range 0-100");
		}
	}

	/**
	 * @return the intelligence
	 */
	public int getIntelligence() {
		return intelligence;
	}

	/**
	 * @param intelligence the intelligence to set
	 */
	public void setIntelligence(int intelligence)  throws IllegalArgumentException {
		if (intelligence >= MIN_ATT && intelligence <= MAX_ATT) {
			this.intelligence = intelligence;
		} else {
			throw new IllegalArgumentException("Attribute value cannot be outside range 0-100");
		}
	}

	/**
	 * @return the bio
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * @param bio the bio to set
	 */
	public void setBio(String bio) throws IllegalArgumentException  {
		if (bio == null) {
			throw new IllegalArgumentException("Bio cannot be null");
		} else {
			this.bio = bio;
		}
	}

	private double calculateAvg() {
		double total = this.getAgility() + this.getIntelligence() + this.getSpeed() + this.getStrength();
		return total / 4;
	}

	/**
	 * this method will return a 0,1,2,3 depending on whether Speed, Strength,
	 * Agility of intelligence have the highest value respectively. if multiple
	 * values are the same the method will return the first value.
	 * 
	 * @return 0, 1,2 or 3
	 */
	// consider putting this back to an array
	public int getMaxStatID() {
		List<Integer> stats =    (Arrays.asList(this.getSpeed(), this.getStrength(), this.getAgility(), 	this.getIntelligence()));
		int maxVal = Collections.max(stats);
		return stats.indexOf(maxVal);
	}

	/**
	 * method to take input 01,2,3 and display the attribute speed, strength,
	 * agility or intelligence respectively
	 * 
	 * @param statChoice
	 * @return
	 */
	public int getStatScore(int statChoice) {
		int[] stats = new int[] { this.getSpeed(), this.getStrength(), this.getAgility(), this.getIntelligence() };
		return stats[statChoice];

//		int attribute = 0;
//		if (statChoice == 0) {
//			attribute = this.getSpeed();
//		} else if (statChoice == 1) {
//			attribute = this.getStrength();
//		} else if (statChoice == 2) {
//			attribute = this.getAgility();
//		} else if (statChoice == 3) {
//			attribute = this.getIntelligence();
//		} 
//		return attribute;
	}

	/**
	 * display the full details of each card
	 */
	public void printDetails() {
		System.out.println("name: \t" + this.getName());
		System.out.println("realname: \t" + this.getRealname());
		System.out.println("filename: \t" + this.getImageName());
		System.out.println("category: \t" + this.getCategory());
		System.out.println("speed: \t" + this.getSpeed());
		System.out.println("strength: \t" + this.getStrength());
		System.out.println("agility: \t" + this.getAgility());
		System.out.println("intelligence: \t" + this.getIntelligence());
		System.out.println("bio: \t" + this.getBio());
		System.out.println();
	}

	/**
	 * display the summary details of each card
	 */
	public void summaryDetails() {
		String name = String.format(" %s / %s (%s)", this.getName(), this.getRealname(), this.getCategory());
		String stats = String.format("sp: %d | st: %d | ag: %d | in: %d", this.getSpeed(), this.getStrength(),
				this.getAgility(), this.getIntelligence());
		System.out.println(name);
		System.out.println(stats);
	}

	public void summaryDetailsAvgStats() {
		String name = String.format("%s / %s (%s)", this.getName(), this.getRealname(), this.getCategory());
		String stats = String.format("sp: %d | st: %d | ag: %d | in: %d", this.getSpeed(), this.getStrength(),
				this.getAgility(), this.getIntelligence());
		String avg = String.format("Average Stat: %.2f", calculateAvg());
		System.out.println(name);
		System.out.println(stats);
		System.out.println(avg);
	}

	public void bioDetails() {
		System.out.println(this.getName());
		System.out.println(this.getBio());
	}

	@Override
	public int hashCode() {
		return Objects.hash(agility, bio, category, imageName, intelligence, name, realname, speed, strength);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TopTrumpCard other = (TopTrumpCard) obj;
		return agility == other.agility && Objects.equals(bio, other.bio) && category == other.category
				&& Objects.equals(imageName, other.imageName) && intelligence == other.intelligence
				&& Objects.equals(name, other.name) && Objects.equals(realname, other.realname) && speed == other.speed
				&& strength == other.strength;
	}

	@Override
	public String toString() {
		return "TopTrumpCard [name=" + name + ", realname=" + realname + ", imageName=" + imageName + ", category="
				+ category + ", speed=" + speed + ", strength=" + strength + ", agility=" + agility + ", intelligence="
				+ intelligence + ", bio=" + bio + "]";
	}

}
