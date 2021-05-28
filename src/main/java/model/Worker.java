package model;

import java.util.ArrayList;
import java.util.Collections;

public class Worker {

	public static final int WORKER_COUNT = 3;

	private static int workerSerial = 0;

	private int id;

	private ArrayList<Job> jobs;
	
	public Worker() {
		this.id = ++workerSerial;
		this.jobs = new ArrayList<>();
		System.out.println("Worker Id" + this.id);
	}

	public Worker(Job[] jobs) {
		this.id = ++workerSerial;

		this.jobs = new ArrayList<>();
		Collections.addAll(this.jobs, jobs);
	}
	
	public void addJob(Job job) {
		this.jobs.add(job);
	}
	
	public Job[] getJobs() {
		Job[] jobsArr = new Job[this.jobs.size()];
		this.jobs.toArray(jobsArr);
		return jobsArr;
	}
	
	public int getId() {
		return this.id;
	}

}
