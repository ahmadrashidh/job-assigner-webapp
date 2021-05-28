package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkerList {

	private List<Worker> workers = new ArrayList<>();

	public WorkerList(Worker[] workers) {
		Collections.addAll(this.workers, workers);
	}

	public Worker[] getWorkers() {
		Worker[] workersArr = new Worker[this.workers.size()];
		this.workers.toArray(workersArr);
		return workersArr;
	}

}
