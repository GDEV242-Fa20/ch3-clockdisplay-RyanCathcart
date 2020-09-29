
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @author Updated by Ryan Cathcart
 * @version 2010.09.28
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private String meridiem;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 12:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(13);
        hours.setValue(12);
        minutes = new NumberDisplay(60);
        meridiem = "AM";
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute, String merid)
    {
        hours = new NumberDisplay(13);
        minutes = new NumberDisplay(60);
        setTime(hour, minute, merid);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
            if (hours.getValue() == 12) { //When the hours turns to 12, change the meridiem
                if (meridiem == "AM") {
                    meridiem = "PM";
                } else {
                    meridiem = "AM";
                }
            } else if (hours.getValue() == 0) { //hours just rolled over but we want to skip 0:00
                hours.setValue(1);
            }
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute, String merid)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        meridiem = merid;
        updateDisplay();
    }
    
    /**
     * Set the internal string that represents the meridiem.
     */
    public void setMeridiem(String merid)
    {
        if (merid == "AM" || merid == "am") {
            meridiem = "AM";
        } else if (merid == "PM" || merid == "pm") {
            meridiem = "PM";
        }
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Return the current meridiem.
     */
    public String getMeridiem()
    {
        return meridiem;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue();
    }
    
    /**
     * Return the current display in the format HH:MM, including
     * the meridiem at the end.
     */
    public String get12HourInternalDisplay() {
        return displayString + meridiem;
    }
}
