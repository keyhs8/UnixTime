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

    private LinkedHashMap<Integer, int[]> dic_year;

    public static final int SEC_MINUTE = 60;
    public static final int SEC_HOUR = SEC_MINUTE * 60;
    public static final int SEC_DAY = SEC_HOUR * 24;
    public static final int DAYS_OF_YEAR = 365;
    public static final int YEARS_OFFSET = 369;

    public static final boolean DEBUG_MODE = false;

    /**
     * Default constructor
     */
    public UnixTime() {

        this.unixTime = 0;
        this.year = 1970 - YEARS_OFFSET;
        this.month = 1;
        this.day = 1;
        this.daysOfMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // <単位年, {含まれる日数, カウント}>
        this.dic_year = new LinkedHashMap<>();
        this.dic_year.put(400, new int[]{400 * DAYS_OF_YEAR + 97, 0});
        this.dic_year.put(100, new int[]{100 * DAYS_OF_YEAR + 24, 0});
        this.dic_year.put(4, new int[]{4 * DAYS_OF_YEAR + 1, 0});
        this.dic_year.put(1, new int[]{DAYS_OF_YEAR, 0});
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

        // 閏年計算のため369年分（1970→1601）+ 閏日(89日）オフセット
        long utime_offset = (long)utime + (YEARS_OFFSET * DAYS_OF_YEAR + 89) * (long)SEC_DAY;
        long d_sec;

        // 年計算
        for (Map.Entry<Integer, int[]> entry: this.dic_year.entrySet()) {
            int e_year = entry.getKey();
            int[] e_value = entry.getValue();
            d_sec = (e_value[0]) * (long)SEC_DAY;

            // 商部分の年数計算
            e_value[1] = (int)(utime_offset / d_sec);

            // 閏年12/31例外補正
            if (e_year == 1 && e_value[1] == 4) e_value[1] = 3;

            // 年数加算
            this.year += e_value[1] * e_year;

            // 計算分を減算
            utime_offset -= d_sec * e_value[1];

            // デバッグプリント
            printDebug(String.valueOf(e_year) + "年計算(" + String.valueOf(e_value[1]) + "回) / ");
        }

        // 閏年判定
        if (this.year % 4 == 0 && (this.year % 100 != 0 || this.year % 400 == 0)) {
            this.daysOfMonth[1] = 29;
        }

        printDebug("残日数 : " + String.valueOf(utime_offset / (double)SEC_DAY) + "日 \n");

        // 月計算
        for (int i = 0; (utime_offset / (d_sec = this.daysOfMonth[i] * SEC_DAY)) > 0; i++) {
            utime_offset -= d_sec;
            this.month++;
        }

        // 日計算
        this.day += (int)(utime_offset / SEC_DAY);
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
        if (DEBUG_MODE) System.out.print(msg);
    }
}