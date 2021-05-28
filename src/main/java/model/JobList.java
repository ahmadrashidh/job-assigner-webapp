package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JobList {

	private List<Job> jobs = new ArrayList<>();

	public JobList() {
	}

	public JobList(Job[] jobs) {
		Collections.addAll(this.jobs, jobs);
	}

	public Job[] getJobs() {
		Job[] jobsArr = new Job[this.jobs.size()];
		this.jobs.toArray(jobsArr);
		return jobsArr;
	}

	public void setJobs(Job[] jobs) {
		Collections.addAll(this.jobs, jobs);
	}

	public void addJob(Job job) {
		this.jobs.add(job);
	}
}
