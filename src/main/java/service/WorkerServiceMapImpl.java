package service;

import java.util.HashMap;
import java.util.Map;

import model.Job;
import model.Worker;

public class WorkerServiceMapImpl implements WorkerService {

	private static WorkerServiceMapImpl workerService;

	private Map<Integer, Worker> workers = new HashMap<>();

	private WorkerServiceMapImpl() {

	}

	public static WorkerServiceMapImpl getInstance() {
		if (workerService == null)
			workerService = new WorkerServiceMapImpl();

		return workerService;
	}

	public void insertWorker(Worker worker) {
		this.workers.put(worker.getId(), worker);
	}

	public Worker selectWorkerById(int id) {
		return this.workers.get(id);
	}

	public Worker[] selectAllWorkers() {
		Worker[] workersArr = new Worker[this.workers.size()];
		this.workers.values().toArray(workersArr);
		return workersArr;
	}

	public Job[] getAllJobsAssigned(int workerId) {
		return this.workers.get(workerId).getJobs();
	}

	public void assignJob(int workerId, Job job) {

		this.workers.get(workerId).addJob(job);

	}
}
