package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Job;
import model.Worker;
import service.JobService;
import service.JobServiceMapImpl;
import service.WorkerService;
import service.WorkerServiceMapImpl;

public class JobEngine {

	public void runJobAssignment() {

		JobAssignment jobAssignment = new EasyJobAssignment();
		Map<Integer, ArrayList<Integer>> workerJobMap = new HashMap<>();

		int[] jobsArr = getJobsFromStore();

		workerJobMap = jobAssignment.assignJobs(jobsArr, false);

		updateStore(workerJobMap);

	}

	private int[] getJobsFromStore() {

		JobService jobService = JobServiceMapImpl.getInstance();

		Job[] jobs = jobService.selectAllJob();
		int[] jobsArr = new int[jobs.length];

		for (int index = 0; index < jobs.length; index++) {
			jobsArr[index] = jobs[index].getRuntime();
			System.out.println("Jobs Array"  + jobsArr[index]);
		}

		return jobsArr;

	}

	private void updateStore(Map<Integer, ArrayList<Integer>> workerJobMap) {

		WorkerService workerService = WorkerServiceMapImpl.getInstance();

		for (Map.Entry<Integer, ArrayList<Integer>> workerEntry : workerJobMap.entrySet()) {
			
			Worker worker = new Worker();
			

			for (Integer runtime : workerEntry.getValue()) {
				Job job = new Job(runtime);
				worker.addJob(job);
			}
			
			System.out.println("Worker" + worker.getJobs().length);
			workerService.insertWorker(worker);
		}

	}

}
