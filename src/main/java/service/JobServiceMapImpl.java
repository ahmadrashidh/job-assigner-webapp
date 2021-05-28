package service;

import java.util.HashMap;
import java.util.Map;

import model.Job;

public class JobServiceMapImpl implements JobService {

	private static JobServiceMapImpl jobService;

	private Map<Integer, Job> jobs = new HashMap<>();

	private int jobCount = jobs.size();
	
	private JobServiceMapImpl() {

	}

	public static JobServiceMapImpl getInstance() {
		if (jobService == null)
			jobService = new JobServiceMapImpl();

		return jobService;
	}

	public void insertJob(Job job) {
		job.setId(++jobCount);
		this.jobs.put(job.getId(), job);
	}

	public void insertAllJob(Job[] jobs) {
		for (Job job : jobs) {
			job.setId(++jobCount);
			this.jobs.put(job.getId(), job);
		}
	}

	public Job[] selectAllJob() {
		Job[] jobsArr = new Job[this.jobs.size()];
		this.jobs.values().toArray(jobsArr);
		return jobsArr;
	}

	public Job selectJobById(int id) {
		return this.jobs.get(id);
	}
	
	

}
