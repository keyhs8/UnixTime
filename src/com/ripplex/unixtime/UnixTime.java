package com.ripplex.unixtime;

import java.util.Map;
import java.util.LinkedHashMap;

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
    private int[] daysOfMonth;
    private LinkedHashMap<Integer, Integer[]> dic_year;

    public static final int SEC_MINUTE = 60;
    public static final int SEC_HOUR = SEC_MINUTE * 60;
    public static final long SEC_DAY = SEC_HOUR * 24;
    public static final int DAYS_OF_YEAR = 365;

    public static final boolean DEBUG_MODE = true;

    /**
     * Default constructor
     */
    public UnixTime() {

        this.unixTime = 0;
        // 閏年計算のため70年分（1970→1900）オフセット
        this.year = 1900;
        this.month = 0;
        this.day = 0;

        // <単位年, <含まれる閏日数, 単位年度が閏日 {0: でない, 1: である}>>
        this.dic_year = new LinkedHashMap<>();
        this.dic_year.put(400, new Integer[]{97, 1});
        this.dic_year.put(100, new Integer[]{24, 0});
        this.dic_year.put(4, new Integer[]{1, 1});
        this.dic_year.put(1, new Integer[]{0, 0});
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
     * Set UNIX Time
     *
     * @param utime UNIX Time
     */
    public void setUnixTime(int utime) {
        this.unixTime = utime;

        // 閏年計算のため70年分（1970→1900）オフセット（閏年17回）
        long utime_offset = (long)utime + (70 * DAYS_OF_YEAR + 17) * SEC_DAY;

        for (Map.Entry<Integer, Integer[]> entry: this.dic_year.entrySet()) {
            int e_year = entry.getKey();
            Integer[] e_value = entry.getValue();
            long e_sec = (e_year * DAYS_OF_YEAR + e_value[0]) * SEC_DAY;

            // 商部分の年数計算
            int e_year_count = (int)(utime_offset / e_sec);
            this.year += e_year_count * e_year;

            // 計算分を減算
            utime_offset -= e_sec * e_year_count;

            // デバッグプリント
            printDebug(String.valueOf(e_year) + "年計算(" + String.valueOf(e_year_count) + "回) : " +
                    String.valueOf(this.year) + "年 " + String.valueOf(utime_offset / (double)SEC_DAY) + "日");
        }
    }

    /**
     * Get UNIX Time
     *
     * @return UNIX Time
     */
    public int getUnixTime() { return this.unixTime; }

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
    public int getMonth() { return this.month; }

    /**
     * Return day number of UNIX Time
     *
     * @return Day number
     */
    public int getDay() { return this.day; }

    /**
     * Standard out for debug
     *
     * @param msg Debug message
     */
    private void printDebug(String msg) {
        if (DEBUG_MODE) System.out.println(msg);
    }
}