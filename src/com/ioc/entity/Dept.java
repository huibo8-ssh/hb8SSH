package com.ioc.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_dept")
public class Dept implements Serializable{
	private static final long serialVersionUID = -4482403326795489506L;
	
	@Id
	@Column(name="deptid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int deptid;
	
	@Column(name="deptName",length=20,nullable=false)
	private String deptName;
	
	
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Dept(int deptid, String deptName) {
		this.deptid = deptid;
		this.deptName = deptName;
	}
	public Dept() {}

}
