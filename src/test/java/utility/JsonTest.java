package utility;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Job;

public class JsonTest {

	
	@Test
	public void PassingNullToConvertToPayload_throwsNullPointerException() {
		Job job = null;
		Json<Job> json = new Json<>(Job.class);
		
		NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
			json.convertToPayload(job);;
		});

		assertTrue(thrown.getMessage()
				.contains("Passed object cannot be null"));
	}
	
	@Test
	public void PassingValidObjectToConvertToPayload_returnsCorrectJson() {
		Json<Job> json = new Json<>(Job.class);
		Job job = new Job(23);
		job.setId(2);
		String expectedJson = "{\"id\":2,\"runtime\":23}";
		
		assertEquals(expectedJson, json.convertToPayload(job));

	}
	
	@Test
	public void PassingNullToConvertToModel_throwsNullPointerException() {

		Json<Job> json = new Json<>(Job.class);
		
		NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
			json.convertToModel(null);;
		});

		assertTrue(thrown.getMessage()
				.contains("JSON cannot be null or empty"));
	}
	
	@Test
	public void PassingEmptyStringToConvertToModel_throwsNullPointerException() {
		Json<Job> json = new Json<>(Job.class);
		
		NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
			json.convertToModel("");;
		});

		assertTrue(thrown.getMessage()
				.contains("JSON cannot be null or empty"));
	}
	
	@Test
	public void PassingValidJsonToConvertToModel_returnsValidObject() {
		Job job = new Job(23);
		job.setId(2);
		
		Json<Job> json = new Json<>(Job.class);
		String jsonString = "{\"id\":2,\"runtime\":23}";
		
		assertTrue(job.equals(json.convertToModel(jsonString)));
		
	}
	

	
	

}
