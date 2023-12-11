package com.book.util;

import java.sql.Timestamp;

public class DateUtils {

	 /** 年月日 */
    public static final String FORMAT_JAPAN = "yyyy年MM月dd日";
    /** 年月日 */
    public static final String FORMAT_JAPAN_2 = "yyyy年M月d日";
    /** yyyyMMdd */
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    /** yyyy/MM/dd */
    public static final String FORMAT_YYYYMMDD_SLASH = "yyyy/MM/dd";
    /** yyyy-MM-dd */
    public static final String FORMAT_YYYYMMDD_HIFUN = "yyyy-MM-dd";
    
    private DateUtils(){}

    /**
     * 現在日時取得
     * @return
     */
    public static Timestamp getNowDate(){
        return new Timestamp(System.currentTimeMillis());
    }
}
