package pks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
class PatientDataImplTest {
	private static PatientData patientData = null;
	
	@BeforeAll
	static void setup() {
		String csvPath = ".\\src\\main\\resources\\messages.csv";
		patientData = new PatientDataImpl(csvPath);
	}

	@Test
	void testGetNumberOfEpisodes() {
		Assertions.assertEquals(patientData.getNumberOfEpisodes(), 4);
	}
	
	@Test
	void testGetNumberOfPatients() {
		Assertions.assertEquals(patientData.getNumberOfPatients(), 3);
	}
	
	@Test
	void testGetAverageAgeForMales() {
		Assertions.assertEquals(patientData.getAverageAgeByGender("M"), 24.0);
	}
	
	@Test
	void testGetAverageAgeForFemales() {
		Assertions.assertEquals(patientData.getAverageAgeByGender("F"), 24.5);
	}

}
