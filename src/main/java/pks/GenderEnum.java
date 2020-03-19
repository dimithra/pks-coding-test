package pks;

/**
 * Gender enum
 */
public enum GenderEnum {
	
	MALE("M"), FEMALE("F");
	
	private String code;
	
	GenderEnum(String code) {
        this.code = code;
    }
	
	/**
	 * Retrieve Enum by code
	 * @param code
	 * @return GenderEnum
	 */
	public static GenderEnum getByCode(String code) {
	    for(GenderEnum g : values()) {
	        if(g.code.equals(code)) return g;
	    }
	    return null;
	}

}
