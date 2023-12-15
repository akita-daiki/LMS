package com.book.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

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

	private DateUtils() {
	}

	/**
	 * 現在日時取得
	 * @return
	 */
	public static Timestamp getNowDate() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 現在の日時から1週間後の日付を00:00:00の時間でTimestamp形式で返します。
	 * 
	 * @param nowDate 現在の日時（Timestamp形式）
	 * @return 1週間後の日付（Timestamp形式）
	 */
	public static Timestamp calculateOneWeekLaterWithEndTime(Timestamp currentTimestamp) {
		// 現在のTimestampをLocalDateTimeに変換
		LocalDateTime now = currentTimestamp.toLocalDateTime();

		// 1週間後の日付を計算し、時刻部分を23:59:59に設定
		LocalDateTime oneWeekLaterWithEndTime = now.plusWeeks(1).withHour(23).withMinute(59).withSecond(00);

		// LocalDateTimeをTimestampに戻して返す
		return Timestamp.valueOf(oneWeekLaterWithEndTime);
	}
	
	 /**
     * RETURNDATEを初期状態（1970-01-01 00:00:00）に設定するメソッド。
     *
     * @return 初期状態のTimestamp。
     */
    public static Timestamp initializeReturnDate() {
        LocalDateTime initialDateTime = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        return Timestamp.valueOf(initialDateTime);
    }
    
    /**
     * 日付を（yyyy/MM/dd EEEE）の形式で返します。
     * @param date
     * @return
     */
    public static String dateFormat(Date date) {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd EEEE", Locale.JAPAN);
        return formatter.format(date);
    }
}
