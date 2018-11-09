package com.ioc.entity;

import java.io.Serializable;

public class Role implements Serializable{
	private static final long serialVersionUID = 912802144226301647L;

	private int rid;
	
	private String roleName;

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
