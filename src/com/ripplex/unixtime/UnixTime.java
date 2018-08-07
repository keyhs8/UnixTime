package com.ripplex.unixtime;

import java.util.Map;
import java.util.Iterator;
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
    private LinkedHashMap<Integer, Long> dic_year;

    public static final int SEC_MINUTE = 60;
    public static final int SEC_HOUR = SEC_MINUTE * 60;
    public static final int SEC_DAY = SEC_HOUR * 24;
    public static final long SEC_YEAR = SEC_DAY * 365;

    /**
     * Default constructor
     */
    public UnixTime() {

        this.unixTime = 0;
        this.year = 1900;
        this.month = 0;
        this.day = 0;

        long sec_y_four = SEC_YEAR * 4 + SEC_DAY;                               // 4年目は閏年
        long sec_y_one_hundred = sec_y_four * 24 + SEC_YEAR;                    // 100年目は閏年ではない
        long sec_y_four_handred = sec_y_one_hundred * 3 + SEC_YEAR + SEC_DAY;   // 400年目は閏年

        this.dic_year = new LinkedHashMap<>();
        this.dic_year.put(400, sec_y_four_handred);
        this.dic_year.put(100, sec_y_one_hundred);
        this.dic_year.put(4, sec_y_four);
        this.dic_year.put(1, SEC_YEAR);
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

        // 閏年計算のため70年分（1970→1900）オフセット（閏年17回）
        long utime_offset = (long)utime + (SEC_YEAR * 70 + SEC_DAY * 17);

        Iterator<Map.Entry<Integer, Long>> iter = this.dic_year.entrySet().iterator();
        Map.Entry<Integer, Long> entry = null;
        if (iter.hasNext()) {
            entry = iter.next();
        }
        while (iter.hasNext()) {
            int e_year = entry.getKey();
            long e_sec = entry.getValue();
            entry = iter.next();
            int n_year = entry.getKey();;
            long n_sec = entry.getValue();

            // 商部分の年数計算
            this.year += (utime_offset / e_sec) * e_year;

            // 余り部分の年数計算
            this.year += ((utime_offset % e_sec) / n_sec) * n_year;

            // 計算分を減算
            utime_offset %= n_sec;

            System.out.println(String.valueOf(n_year)
                    + "年計算 : " + String.valueOf(this.year) + "年 "
                    + String.valueOf(utime_offset / (double)SEC_DAY) + "日");
        }

        // オフセットを戻す
        //this.year -= 30;

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