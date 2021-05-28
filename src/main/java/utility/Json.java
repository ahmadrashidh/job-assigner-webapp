package utility;

import com.google.gson.Gson;

public class Json<T> {

	private static Gson gson = new Gson();

	final Class<T> type;

	public Json(Class<T> type) {
		this.type = type;
	}

	public T convertToModel(String jsonPayload) {
		if (jsonPayload == null || jsonPayload.isEmpty())
			throw new NullPointerException("JSON cannot be null or empty");
		T model =  gson.fromJson(jsonPayload, type);	
		return model;
	}

	public String convertToPayload(T model) {
		
		if(model == null) {
			throw new NullPointerException("Passed object cannot be null");
		}
		return gson.toJson(model);
	}
	


}
