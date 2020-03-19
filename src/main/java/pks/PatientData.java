package pks;

/**
 * PatientData provides APIs for analyzing patient data available in a batch
 */
public interface PatientData {

	/**
	 * Retrieves the numbers of episodes available in a batch
	 * @return number of episodes
	 */
	public long getNumberOfEpisodes();

	/**
	 * Retrieves the numbers of distinct patients available in a batch
	 * @return number of distinct patients
	 */
	public long getNumberOfPatients();

	/**
	 * Retrieves average age given gender
	 * @param gender
	 * @return average age
	 */
	public double getAverageAgeByGender(String gender);
}
