package pks;

import java.util.HashMap;
import java.util.Map;

/**
 * An episode represents a group of all data attributes for one patient on a single day
 */
public class Episode {

	private String patientNo;
	private String dateStr;
	private Map<String, String> data;

	public Episode() {
		super();
	}
	
	/**
	 * Constructor with params
	 * 
	 * @param dateStr - date
	 * @param patientNo - patient number
	 * @param key - data attribute name
	 * @param value - data attribute value
	 */
	public Episode(String dateStr, String patientNo, String key, String value) {
		super();
		this.patientNo = patientNo;
		this.dateStr = dateStr;
		addData(key, value);
	}
	
	/**
	 * Constructor with params
	 * 
	 * @param dateStr - date
	 * @param patientNo - patient number
	 */
	public Episode(String dateStr, String patientNo) {
		super();
		this.patientNo = patientNo;
		this.dateStr = dateStr;
	}

	/**
	 * @return patient number
	 */
	public String getPatientNo() {
		return patientNo;
	}

	/** Set patient number
	 * @param patientNo
	 */
	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}

	/**
	 * Get date in the episode
	 * @return date string
	 */
	public String getDateStr() {
		return dateStr;
	}

	/**
	 * Set date in the episode
	 * @param dateStr date string
	 */
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	/**
	 * Get data attributes
	 * @return data - data attributes
	 */
	public Map<String, String> getData() {
		return data;
	}

	/**
	 * Set data attributes
	 * @param data - data attributes
	 */
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
	/**
	 * Add a data attribute element to the episode
	 * @param key
	 * @param value
	 */
	public void addData(String key, String value) {
		if (this.data == null) {
			this.data = new HashMap<String, String>();
		}
		this.data.put(key, value);
	}
	
	/**
	 * @return patient gender
	 */
	public GenderEnum getPatientGender() {
		if (this.data == null || this.data.get("Gender") == null) {
			return null;
		} else {
			return this.data.get("Gender").equals("M") ? GenderEnum.MALE : GenderEnum.FEMALE;
		}
	}
	
	public Integer getPatientAge() {
		if (this.data == null || this.data.get("Age") == null) {
			return null;
		} else {
			return Integer.valueOf(this.data.get("Age"));
		}
	}

	@Override
	public int hashCode() {
        int result = 17;          
        result = 37*result + dateStr.hashCode();          
        result = 37*result + patientNo.hashCode();      
        return result;     
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Episode other = (Episode) obj;
		if (dateStr == null) {
			if (other.dateStr != null)
				return false;
		} else if (!dateStr.equals(other.dateStr))
			return false;
		if (patientNo == null) {
			if (other.patientNo != null)
				return false;
		} else if (!patientNo.equals(other.patientNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EpisodeSet [patientNo=" + patientNo + ", dateStr=" + dateStr + "]";
	}
	
}
