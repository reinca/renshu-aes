package com.practice.aes.domain.entity;

public class Result {

	private long id;
	private String status;

	public Result(long id, String result) {
		this.id = id;
		this.status = result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
