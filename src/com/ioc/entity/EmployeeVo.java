package com.ioc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author Administrator
 * 数据库列类型		Java实体类属性类型
 * tinyint		byte short int long
 * smallint		short int long
 * int			int long
 * bigint		long
 * decimal		java.math.BigDecimal 可以转化为double
 * varchar		java.lang.String
 * datetime		java.util.Date
 * 当数据库表的列类型为整数或浮点数（decimal）时，且此列值为null时，若对应实体类的属性类型为基本数据类型，获取数据的时候
 * 会报错java.lang.IllegalArgumentException(argument type missmatch类型不匹配),两种办法：
 * 	1）可以更改数据库中的数据，确保这些列值没有null值，并且建表语句加入一些not null非空约束以及默认值等；
 * 	2）可以将其对应的实体类的属性类型改为基本数据类型对应的包装类类型。
 */
@Entity
@Table(name="v_employeeVo")
public class EmployeeVo implements Serializable{
	private static final long serialVersionUID = 271404636773253811L;
	
	@Id
	@Column(name="empid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int empid;
	
	@Column(name="empName",length=20,nullable=false)
	private String empName;
	
	@Column(name="age")
	private int age;
	
	@Column(name="gender")
	private int gender;
	
	@Column(name="phone",length=11)
	private String phone;
	
	@Column(name="email",length=50)
	private String email;
	
	@Column(name="deptid")
	private int deptid;
	
//	@Transient//当实体类与表结构映射的时候忽略此属性
	@Column(name="deptName")
	private String deptName;
	
	@Column(name="entryDate")
	private Date entryDate;
	
	@Override
	public String toString() {
		return empid+" " + empName + " "+age+" "+gender+" "+phone+" "+email+" "+deptid+" "+deptName+" "+entryDate.toString();
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public EmployeeVo(int empid, String empName, int age, int gender, String phone, String email, int deptid,
			String deptName, Date entryDate) {
		this.empid = empid;
		this.empName = empName;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.deptid = deptid;
		this.deptName = deptName;
		this.entryDate = entryDate;
	}
	public EmployeeVo() {}

}
