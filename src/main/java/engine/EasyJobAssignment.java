package engine;

import java.util.*;

public class EasyJobAssignment implements JobAssignment {

	@Override
	public Map<Integer, ArrayList<Integer>> assignJobs(int[] jobs, boolean toSortAscending) {

		int optimumRuntime;
		Map<Integer, ArrayList<Integer>> workerJobMap = new HashMap<>();

		if (jobs == null || jobs.length == 0) {
			return workerJobMap;
		}

		initializeWorkerJobMap(workerJobMap);

		sortJobs(jobs, toSortAscending);

		optimumRuntime = findOptimumRuntime(jobs);

		optimizeWorkerJobMap(workerJobMap, jobs, optimumRuntime);

		return workerJobMap;
	}

	private int findOptimumRuntime(int[] jobs) {
		int totalRunTime, minRunTime;
		int low, high, mid;
		int workerCount;

		totalRunTime = getTotalRuntime(jobs);
		minRunTime = getMinimumRuntime(jobs);

		// Finding optimumRuntime using Binary Search
		low = minRunTime;
		high = totalRunTime;
		while (low < high) {
			mid = low + (high - low) / 2;
			workerCount = calculateReqWorkerCount(jobs, mid);

			if (workerCount <= NO_OF_WORKERS)
				high = mid;
			else
				low = mid + 1;
		}

		return low;
	}

	private int calculateReqWorkerCount(int[] jobs, int optimumRuntime) {
		int workerRunTime = 0, workerCount = 1;

		for (int jobTime : jobs) {
			workerRunTime += jobTime;

			if (workerRunTime > optimumRuntime) {
				workerRunTime = jobTime;
				workerCount++;
			}
		}

		return workerCount;
	}

	private void optimizeWorkerJobMap(Map<Integer, ArrayList<Integer>> workerJobMap, int[] jobs, int optimumRuntime) {
		int workerRunTime = 0;
		for (int jobIndex = 0, workerIndex = 0; jobIndex < jobs.length - 1; jobIndex++) {
			workerJobMap.get(workerIndex + 1).add(jobs[jobIndex]);
			workerRunTime += jobs[jobIndex];

			if (workerRunTime + jobs[jobIndex + 1] > optimumRuntime) {
				workerIndex++;
				workerRunTime = 0;
			}
		}
		workerJobMap.get(workerJobMap.size()).add((jobs[jobs.length - 1]));
	}

	private int getMinimumRuntime(int[] jobs) {

		int minRuntime = 0;

		for (int jobTime : jobs) {
			minRuntime = Math.max(minRuntime, jobTime);

		}

		return minRuntime;
	}

	private int getTotalRuntime(int[] jobs) {

		int totalRunTime = 0;

		for (int jobTime : jobs) {
			totalRunTime += jobTime;

		}
		return totalRunTime;
	}

	private void initializeWorkerJobMap(Map<Integer, ArrayList<Integer>> workerJobMap) {

		for (int worker = 1; worker <= NO_OF_WORKERS; worker++) {
			workerJobMap.put(worker, new ArrayList<>());
		}

	}

}
