/**
 * 
 */
package p2;

/**
 * @author rache
 *
 */
public class Smartradiator extends Device {
	
	private static int TEMPNOW_MIN = -10;
	private static int TEMPNOW_MAX = 30;
	private static int TEMPTARGET_MIN = 5;
	private static int TEMPTARGET_MAX = 26;
	
	private double tempNow;
	private double tempTarget; 

	/**
	 * @param name
	 * @param manufacturer
	 * @param room
	 * @param powerstate
	 */
	public Smartradiator(String name, String manufacturer, Room room, Powerstate powerstate, double tempNow, double tempTarget) {
		super(name, manufacturer, room, powerstate);
		Powerstate currentPowerState = powerstate;
		this.setTempNow(tempNow); // may change powerstate (based on defaults)
		this.setTempTarget(tempTarget);
		this.setPowerstate(currentPowerState); //  set powerstate back to passed in value 
	}

	/**
	 * @return the tempNow
	 */
	public double getTempNow() {
		return tempNow;
	}

	/**
	 * @param tempNow the tempNow to set
	 */
	public void setTempNow(double tempNow) throws IllegalArgumentException{
		if(tempNow<TEMPNOW_MIN || tempNow> TEMPNOW_MAX) {
			throw new IllegalArgumentException("Can't set temp out of range");
		} else {
			this.tempNow = tempNow;
			
			if(this.tempTarget > tempNow) {
				this.setPowerstate(Powerstate.ON);
			} else {
				this.setPowerstate(Powerstate.OFF);
			}
		}
	}

	/**
	 * @return the tempTarget
	 */
	public double getTempTarget() {
		return tempTarget;
	}

	/**
	 * @param tempTarget the tempTarget to set
	 */
	public void setTempTarget(double tempTarget) throws IllegalArgumentException {
		if(tempTarget>= TEMPTARGET_MIN && tempTarget<= TEMPTARGET_MAX) {
			this.tempTarget = tempTarget;
		} else {
			throw new IllegalArgumentException("Target temp cannot be outside range");
		}
		
	}
	/**
	 * Returns string representing device radiator status 
	 */
	@Override
	public String status() {
		String status = String.format("SR-%s-&s-NOW:%.1f-TARGET:%.1f-%s",this.getName(), this.getRoom(), this.getTempNow(), this.getTempTarget(), this.getPowerstate());
		status = status.toUpperCase();
		status = status.replace(" ", ""); 
		return status;
	}
	/**
	 * print details of radiator to console 
	 */
	@Override
	public void showAll() {
		super.showAll();
		System.out.printf("TEMPNOW     :%.1f%n",getTempNow());
		System.out.printf("TEMPTARGET     :%.1f%n",getTempTarget());

	}
	
	

}
