package com.entor.entity;

/**
 *	公众号跟用户绑定类
 */
public class UserPublicNumber {
	/**
	 *	ID
	 */
	private String id;
	/**
	 *	用户编号
	 */
	private String u_id;
	/**
	 *	公众号编号
	 */
	private String pid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public UserPublicNumber(String id, String u_id, String pid) {
		super();
		this.id = id;
		this.u_id = u_id;
		this.pid = pid;
	}
	public UserPublicNumber() {
		super();
	}
	@Override
	public String toString() {
		return "UserPublicNumber [id=" + id + ", u_id=" + u_id + ", pid=" + pid + "]";
	}
	

	
	
	
}
