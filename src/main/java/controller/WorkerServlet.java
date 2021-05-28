package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import engine.JobEngine;
import model.Worker;
import model.WorkerList;
import service.WorkerService;
import service.WorkerServiceMapImpl;
import utility.Json;
import utility.Response;

/**
 * Servlet implementation class WorkerServlet
 */
@WebServlet("/workers")
public class WorkerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WorkerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String responseJson;
		PrintWriter writer = response.getWriter();
		Response responseUtil;
		String contentType = "application/json";
		
		try {
			
			String pathInfo = request.getPathInfo();

			WorkerService workerService = WorkerServiceMapImpl.getInstance();
			
			if (pathInfo == null || pathInfo.equals("/")) {

				responseJson = getResponseForAllWorkers();

				responseUtil = new Response.ResponseBuilder(200, contentType).setJson(responseJson).build();

			} else {

				String[] endpointSplits = pathInfo.split("/");

				if (endpointSplits.length != 2) {

					responseUtil = new Response.ResponseBuilder(400, contentType).setError("Invalid JobId").build();

				} else {

					int workerId = Integer.parseInt(endpointSplits[1]);
					Worker worker = workerService.selectWorkerById(workerId);

					if (worker == null) {

						responseUtil = new Response.ResponseBuilder(400, contentType).setError("Job doesn't exist")
								.build();

					} else {

						responseJson = getResponseForWorker(worker);
						responseUtil = new Response.ResponseBuilder(200, contentType).setJson(responseJson).build();


					}

				}

			}

			response.setStatus(responseUtil.getStatusCode());
			response.setContentType(responseUtil.getContentType());
			System.out.println("Content Type" + responseUtil.getContentType());
			response.setContentLength(responseUtil.getContentLength());
			writer.print(responseUtil.getJson());

		} catch (NumberFormatException e) {

			responseUtil = new Response.ResponseBuilder(400, contentType).setError("Invalid Job Id").build();

			response.setStatus(responseUtil.getStatusCode());
			response.setContentType(responseUtil.getContentType());
			response.setContentLength(responseUtil.getContentLength());
			writer.print(responseUtil.getJson());

		} finally {

			writer.flush();
		}
	}

	private String getResponseForWorker(Worker worker) {

		return null;

	}

	private String getResponseForAllWorkers() {

		JobEngine jobEngine = new JobEngine();
		jobEngine.runJobAssignment();

		WorkerService workerService = WorkerServiceMapImpl.getInstance();
		Worker[] workers = workerService.selectAllWorkers();
		
		for(Worker worker : workers) {
			System.out.println("Workers Response: " + worker.getJobs().length);
		}

		WorkerList workerList = new WorkerList(workers);
		Json<WorkerList> json = new Json<>(WorkerList.class);
		return json.convertToPayload(workerList);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
	}

}
