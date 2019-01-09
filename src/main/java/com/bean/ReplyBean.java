/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

/**
 *
 * @author a021792876p
 */
public class ReplyBean {
    private int status;
	private String json;

	public ReplyBean(int status, String json) {
		super();
		this.status = status;
		this.json = json;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
}
}
