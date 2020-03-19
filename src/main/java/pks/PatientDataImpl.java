package pks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * PatientData API implementation for csv batch data
 */
public class PatientDataImpl implements PatientData {

	private List<String> dataRows = null;

	/**
	 * Instantiates Patient data services for a given data batch
	 * @param pathStr - cvs path 
	 */
	public PatientDataImpl(String pathStr) {
		Stream<String> dataStream = null;
		try {
			dataStream = Files.lines(Paths.get(pathStr));
			dataRows = dataStream.collect(Collectors.toList());
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (dataStream != null)
				dataStream.close();
		}
	}

	@Override
	public long getNumberOfEpisodes() {
	    return getEpisodes().size();
	}

	@Override
	public long getNumberOfPatients() {
		return getPatients().size();
	}

	@Override
	public double getAverageAgeByGender(String gender) {
		Set<String> patientSet = new HashSet<String>();
		GenderEnum genderEnum = GenderEnum.getByCode(gender);
				
		OptionalDouble avg = getEpisodes().stream()
				.filter(p -> p.getPatientGender() == genderEnum)
				.filter(p -> patientSet.add(p.getPatientNo()))
				.mapToInt(p -> p.getPatientAge())
				.average();
		
		return avg.getAsDouble();
	}
	
	/**
	 * @return episodes available in the data batch
	 */
	private Set<Episode> getEpisodes() {
		Set<Episode> episodeSet = new HashSet<Episode>();
		
		Consumer<String[]> action = i -> {
			Episode epi = new Episode(i[0].trim(), i[1].trim(), i[2].trim(), i[3].trim());
			
		    if(episodeSet.add(epi)) {
		        return;
		    } else {
		    	Optional<Episode> exist = episodeSet.stream().filter(epi::equals).findFirst();
		    	exist.get().addData(i[2].trim(), i[3].trim());
		    }
		};
		
		dataRows.stream().skip(1).map(x -> x.split(",")).forEach(action);
		
		return episodeSet;
	}
	
	/**
	 * @return distinct patients available in the data batch
	 */
	private Set<String> getPatients() {
		Set<String> patientSet = new HashSet<String>();
		getEpisodes().stream().forEach(p -> patientSet.add(p.getPatientNo()));
		return patientSet;
	}
	
	
}

