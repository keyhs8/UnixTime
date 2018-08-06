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
    private int year;
    private int month;
    private int day;

    public static final int SEC_MINUTE = 60;
    public static final int SEC_HOUR = SEC_MINUTE * 60;
    public static final int SEC_DAY = SEC_HOUR * 24;
    public static final int SEC_YEAR = SEC_DAY * 365;
    public static final long SEC_YEAR_FOUR = SEC_YEAR * 4 + SEC_DAY;

    /**
     * Default constructor
     */
    public UnixTime() {

        this.unixTime = 0;
        this.year = 1970;
        this.month = 0;
        this.day = 0;
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

        long l_utime_offset = utime + SEC_YEAR * 2;     // 閏年計算のため２年分オフセット

        this.year += (l_utime_offset / SEC_YEAR_FOUR) * 4;
        this.year += (l_utime_offset % SEC_YEAR_FOUR) / SEC_YEAR;
        this.year -= 2;                                 // オフセットを戻す

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
    public int getYear() { return this.year; }

    /**
     * Return month number of UNIX Time
     *
     * @return Month number
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Return day number of UNIX Time
     *
     * @return Day number
     */
    public int getDay() {
        return this.day;
    }
}