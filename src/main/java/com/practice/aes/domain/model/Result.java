package com.practice.aes.domain.model;

public class Result {

	private String id;
	private String status;

	public Result(String id, String result) {
		this.id = id;
		this.status = result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Result [id=" + id + ", status=" + status + "]";
	}
	
}
