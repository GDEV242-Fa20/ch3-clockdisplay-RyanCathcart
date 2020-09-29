
/**
 * The ClockDisplay class implements a digital clock display for a
 * American-style 12 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 12:00AM (midnight) to 11:59PM (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @author Updated by Ryan Cathcart
 * @version 2020.09.28
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private String meridiem;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 12:00AM.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        meridiem = "AM";
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
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
            if (hours.getValue() == 0) { //Clock is at midnight, change the meridiem
                meridiem = "AM";
            } else if (hours.getValue() == 12) { //Clock is at midday, change the meridiem
                meridiem = "PM";
            }
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        if (hour < 12) {
            meridiem = "AM";
        } else {
            meridiem = "PM";
        }
        updateDisplay();
    }
    
    /**
     * Set the time of the display to the specified hour and
     * minute.
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
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        displayString = "";
        if (hours.getValue() == 0) {
            displayString += "12";
        } else if (hours.getValue() > 12) {
            displayString += hours.getValue() - 12;
        } else {
            displayString += hours.getDisplayValue();
        }
        
        displayString += ":" + minutes.getDisplayValue();
    }
    
    /**
     * Return the current display in the format HH:MM, including
     * the meridiem.
     */
    public String get24HourInternalDisplay()
    {
        updateDisplay();
        return displayString + meridiem;
    }
}
