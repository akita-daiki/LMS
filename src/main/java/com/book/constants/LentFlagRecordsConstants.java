package com.book.constants;

public class LentFlagRecordsConstants {
	
	public enum LentRecordsType {
	    AVAILABLE(0,"返却済み"),
	    LENDING(1,"貸出中");

	    private final int code;
	    private final String displayName;

	    LentRecordsType(int code, String displayName) {
	        this.code = code;
	        this.displayName = displayName;
	    }

	    public int getCode() {
	        return code;
	    }

	    public String getDisplayName() {
	        return displayName;
	    }

	    // 数字から文字列変換
	    public static LentRecordsType fromCode(int code) {
	        for (LentRecordsType type : LentRecordsType.values()) {
	            if (type.getCode() == code) {
	                return type;
	            }
	        }
	        throw new IllegalArgumentException("Invalid code: " + code);
	    }
	}


}
