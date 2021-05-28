package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Job;
import model.JobList;
import service.JobService;
import service.JobServiceMapImpl;
import utility.Json;
import utility.Response;

/**
 * Servlet implementation class JobServlet
 */
@WebServlet("/jobs/*")
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JobServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String responseJson;
		PrintWriter writer = response.getWriter();
		Response responseUtil;
		String contentType = "application/json";

		try {

			String pathInfo = request.getPathInfo();

			JobService jobService = JobServiceMapImpl.getInstance();

			if (pathInfo == null || pathInfo.equals("/")) {

				responseJson = getResponseForAllJobs();

				responseUtil = new Response.ResponseBuilder(200, contentType).setJson(responseJson).build();

			} else {

				String[] endpointSplits = pathInfo.split("/");

				if (endpointSplits.length != 2) {

					responseUtil = new Response.ResponseBuilder(400, contentType).setError("Invalid JobId").build();

				} else {

					int jobId = Integer.parseInt(endpointSplits[1]);
					Job job = jobService.selectJobById(jobId);

					if (job == null) {

						responseUtil = new Response.ResponseBuilder(400, contentType).setError("Job doesn't exist")
								.build();

					} else {

						responseJson = getResponseForJob(job);

						responseUtil = new Response.ResponseBuilder(200, contentType).setJson(responseJson).build();

					}

				}

			}

			response.setStatus(responseUtil.getStatusCode());
			response.setContentType(responseUtil.getContentType());
			response.setContentLength(responseUtil.getContentLength());
			writer.print(responseUtil.getJson());

		} catch (NumberFormatException e) {

			responseUtil = new Response.ResponseBuilder(400, contentType).setError("Invalid Job Id").build();

			response.setStatus(responseUtil.getStatusCode());
			response.setContentType(responseUtil.getContentType());
			response.setContentLength(responseUtil.getContentLength());
			writer.print(responseUtil.getJson());

		} catch (NullPointerException e){
			
			responseUtil = new Response.ResponseBuilder(400, contentType).setError(e.getMessage()).build();

			response.setStatus(responseUtil.getStatusCode());
			response.setContentType(responseUtil.getContentType());
			response.setContentLength(responseUtil.getContentLength());
			writer.print(responseUtil.getJson());
			
			
		} finally {

			writer.flush();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getPathInfo();
		String requestJson = "";
		Response responseUtil;
		String contentType = "application/json";

		JobService jobService = JobServiceMapImpl.getInstance();

		response.setContentType("application/json");

		if (pathInfo == null || pathInfo.equals("/")) {
			
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String payload;
			while ((payload = reader.readLine()) != null) {
				buffer.append(payload);
			}
			
			requestJson = buffer.toString();

			Json<JobList> json = new Json<>(JobList.class);
			JobList jobList = json.convertToModel(requestJson);

			jobService.insertAllJob(jobList.getJobs());

			responseUtil = new Response.ResponseBuilder(204, contentType).build();

		} else {

			responseUtil = new Response.ResponseBuilder(503, contentType).build();

		}

		response.setStatus(responseUtil.getStatusCode());
		response.setContentType(responseUtil.getContentType());

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestJson;
		PrintWriter writer = response.getWriter();
		Response responseUtil;
		String contentType = "application/json";

		try {

			String pathInfo = request.getPathInfo();

			JobService jobService = JobServiceMapImpl.getInstance();

			if (pathInfo == null || pathInfo.equals("/")) {

				StringBuilder buffer = new StringBuilder();
				BufferedReader reader = request.getReader();
				String payload;
				while ((payload = reader.readLine()) != null) {
					buffer.append(payload);
				}

				requestJson = buffer.toString();

				Json<JobList> json = new Json<>(JobList.class);
				JobList jobList = json.convertToModel(requestJson);

				jobService.insertAllJob(jobList.getJobs());

				responseUtil = new Response.ResponseBuilder(204, contentType).build();

			} else {

				String[] endpointSplits = pathInfo.split("/");

				if (endpointSplits.length != 2) {

					responseUtil = new Response.ResponseBuilder(400, contentType).setError("Invalid JobId").build();

				} else {

					StringBuilder buffer = new StringBuilder();
					BufferedReader reader = request.getReader();
					String payload;
					while ((payload = reader.readLine()) != null) {
						buffer.append(payload);
					}

					requestJson = buffer.toString();

					Json<Job> json = new Json<>(Job.class);
					Job job = json.convertToModel(requestJson);

					jobService.insertJob(job);

					responseUtil = new Response.ResponseBuilder(204, contentType).build();

				}

			}

			response.setStatus(responseUtil.getStatusCode());
			response.setContentType(responseUtil.getContentType());
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

	private String getResponseForAllJobs() {

		String responseJson;
		Json<JobList> json = new Json<>(JobList.class);
		JobService jobService = JobServiceMapImpl.getInstance();

		Job[] jobs = jobService.selectAllJob();
		JobList jobList = new JobList(jobs);

		responseJson = json.convertToPayload(jobList);
		return responseJson;
	}

	private String getResponseForJob(Job job) {

		Json<Job> json = new Json<>(Job.class);
		String responseJson = json.convertToPayload(job);
		return responseJson;
	}

}
