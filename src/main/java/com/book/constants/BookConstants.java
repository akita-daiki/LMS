package com.book.constants;

public class BookConstants {
	
	public enum GenreType {
	    JAVA(1, "Java"),
	    C(2, "C"),
	    C_SHARP(3, "C#"),
	    C_PLUS_PLUS(4, "C++"),
	    HTML(5, "HTML"),
	    CSS(6, "CSS"),
	    JAVASCRIPT(7, "JavaScript"),
	    PHP(8, "PHP"),
	    ANDROID(9, "Android"),
	    IOS(10, "iOS"),
	    SQL(11, "SQL"),
	    DB(12, "DB"),
	    UNIX(13, "Unix"),
	    WEB_APP(14, "WEBアプリ"),
	    QUALIFICATION(15, "資格"),
	    BUSINESS(16, "ビジネス");

	    private final Integer code;
	    private final String displayName;

	    GenreType(Integer code, String displayName) {
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
	    public static GenreType fromCode(Integer code) {
	        for (GenreType type : GenreType.values()) {
	            if (type.getCode() == code) {
	                return type;
	            }
	        }
	        throw new IllegalArgumentException("Invalid code: " + code);
	    }
	    
	    public static String getGenre(Integer code) {
    		for(GenreType type: GenreType.values()) {
    			if(type.code.equals(code)) {
    				return type.displayName;
    			}
    		}
    		return null;
    	}
	}


}
