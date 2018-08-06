package com.ripplex.unixtime;

/**
 * UnixTime library gets the UTC Time of its UNIX Time.
 * This instance returns UTC year, month and day.
 *
 * @author hosoya
 * @version 1.0
 */
public class UnixTime {

    private int unixTime;

    public static final int SEC_MINUTE = 60;
    public static final int SEC_HOUR = SEC_MINUTE * 60;
    public static final int SEC_DAY = SEC_HOUR * 24;
    public static final int SEC_YEAR = SEC_DAY * 365;
    public static final int SEC_YEAR_LEAP = SEC_YEAR + SEC_DAY;

    /**
     * Default constructor
     */
    public UnixTime() {

        this.unixTime = 0;
    }

    /**
     * Constructor with UNIX Time
     *
     * @param utime UNIX Time
     */
    public UnixTime(int utime) {

        this();
        this.setUnixTime(utime);
    }

    /**
     * UNIX Time
     *
     * @param utime UNIX Time
     */
    public void setUnixTime(int utime) {

        this.unixTime = utime;
    }

    /**
     * UNIX Time
     *
     * @return UNIX Time
     */
    public int getUnixTime() {

        return this.unixTime;
    }

    /**
     * Return year number of UNIX Time
     *
     * @return Year number
     */
    public int getYear() {
        return 0;
    }

    /**
     * Return month number of UNIX Time
     *
     * @return Month number
     */
    public int getMonth() {
        return 0;
    }

    /**
     * Return day number of UNIX Time
     *
     * @return Day number
     */
    public int getDay() {
        return 0;
    }

    /**
     * Return day count of February.
     *
     *
     * @return Day count of February
     */
    protected int getDayCountOfFebruary() {
        return 28;
    }
}