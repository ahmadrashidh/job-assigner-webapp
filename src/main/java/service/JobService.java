package service;

import model.Job;

public interface JobService {

	public void insertAllJob(Job[] job);

	public void insertJob(Job job);

	public Job[] selectAllJob();

	public Job selectJobById(int id);

}
