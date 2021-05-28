package service;

import model.Job;
import model.Worker;

public interface WorkerService {

	public void insertWorker(Worker worker);

	public Worker[] selectAllWorkers();

	public Job[] getAllJobsAssigned(int workerId);

	public Worker selectWorkerById(int id);

}
