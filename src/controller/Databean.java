package controller;

import java.util.List;
import java.util.Map;

public class Databean {
	
	private String org_name;
	
	private String org_size;
	private String org_description;
	private String employee_size;
	private String job_title;
	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getJob_location() {
		return job_location;
	}

	public void setJob_location(String job_location) {
		this.job_location = job_location;
	}

	private String job_location;
	private Map<String, List<String>> jobs_posted;
	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getOrg_size() {
		return org_size;
	}

	public void setOrg_size(String org_size) {
		this.org_size = org_size;
	}

	public String getOrg_description() {
		return org_description;
	}

	public void setOrg_description(String org_description) {
		this.org_description = org_description;
	}

	public String getEmployee_size() {
		return employee_size;
	}

	public void setEmployee_size(String employee_size) {
		this.employee_size = employee_size;
	}

	public Map<String, List<String>> getJobs_posted() {
		return jobs_posted;
	}

	public void setJobs_posted(Map<String, List<String>> jobs_posted) {
		this.jobs_posted = jobs_posted;
	}

}
