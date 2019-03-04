package com.entor.entity;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 用户类
 */
public class User {
	/**
	 * 用户编号
	 */
	private String id;
	/**
	 * 用户账号
	 */
	private String username;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 用户性别
	 */
	private int sex;
	/**
	 * 用户联系方式
	 */
	private String phone;
	/**
	 * 用户地址
	 */
	private String address;
	/**
	 * 用户头像
	 */
	private String email;
	/**
	 * 用户头像
	 */
	private String photo;
	/**
	 * 所属角色
	 */
	private int role;
	/**
	 * 创建时间
	 */
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public User(String id, String username, String password, String name, int sex, String phone, String address,
			String email, String photo, int role, Timestamp createTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.photo = photo;
		this.role = role;
		this.createTime = createTime;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", sex="
				+ sex + ", phone=" + phone + ", address=" + address + ", email=" + email + ", photo=" + photo
				+ ", role=" + role + ", createTime=" + createTime + "]";
	}

	
}
