package model;

public class Job {

	private int id;

	private int runtime;

	public Job(int runtime) {
		this.runtime = runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public int getRuntime() {
		return this.runtime;
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if(!(o instanceof Job)) 
			return false;
		
		Job job = (Job) o;
		return this.id == job.id;
			
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}

}
