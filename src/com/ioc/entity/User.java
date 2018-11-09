package com.ioc.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_user")
@Cacheable(value=true)
public class User implements Serializable{
	private static final long serialVersionUID = -2962917634375613160L;
	
	@Id
	@Column(name="uid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int uid;
	
	@Column(name="userName",length=20,unique=true,nullable=false)
	private String userName;
	
	@Column(name="userPass",length=20,nullable=false)
	private String userPass;
	
	@Column(name="realName",length=20,nullable=false)
	private String realName;
	
	@Column(name="headPicture",length=50)
	private String headPicture = "headImage/default.png";
	
	
	@Override
	public String toString() {
		return uid + "~~~" + userName + "~~~" + userPass + "~~~" + realName + "~~~" + headPicture;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getHeadPicture() {
		return headPicture;
	}
	public void setHeadPicture(String headPicture) {
		this.headPicture = headPicture;
	}
//	@Override
//	public void sessionDidActivate(HttpSessionEvent event) {
//		System.out.println("11111111111111111111111111");
//	}
//	@Override
//	public void sessionWillPassivate(HttpSessionEvent event) {
//		System.out.println("22222222222222222222222222");
//	}
	public User() {}
	public User(int uid, String userName, String userPass, String realName, String headPicture) {
		this.uid = uid;
		this.userName = userName;
		this.userPass = userPass;
		this.realName = realName;
		if(null != headPicture && !"".equals(headPicture))
			this.headPicture = headPicture;
	}

}
