package com.book.constants;

public class AvailabilityConstants {
	
	public enum AvailabilityType {
	    AVAILABLE(0,"貸出可能"),
	    LENDING(1,"貸出中");

	    private final int code;
	    private final String displayName;

	    AvailabilityType(int code, String displayName) {
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
	    public static AvailabilityType fromCode(int code) {
	        for (AvailabilityType type : AvailabilityType.values()) {
	            if (type.getCode() == code) {
	                return type;
	            }
	        }
	        throw new IllegalArgumentException("Invalid code: " + code);
	    }
	}


}
