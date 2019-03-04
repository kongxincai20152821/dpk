package com.entor.entity;

public class PublicCondition {
	private String id;
	private String username;
	private String name;
	private String sex;
	private String publicName;
	private String publicId;
	private String publicType;
	private String u_id;
	private String pid;
	private int state;
	private String tid;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPublicName() {
		return publicName;
	}
	public void setPublicName(String publicName) {
		this.publicName = publicName;
	}
	public String getPublicId() {
		return publicId;
	}
	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}
	public String getPublicType() {
		return publicType;
	}
	public void setPublicType(String publicType) {
		this.publicType = publicType;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public PublicCondition(String id, String username, String name, String sex, String publicName, String publicId,
			String publicType, String u_id, String pid, int state, String tid) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.sex = sex;
		this.publicName = publicName;
		this.publicId = publicId;
		this.publicType = publicType;
		this.u_id = u_id;
		this.pid = pid;
		this.state = state;
		this.tid = tid;
	}
	public PublicCondition() {
		super();
	}
	@Override
	public String toString() {
		return "PublicCondition [id=" + id + ", username=" + username + ", name=" + name + ", sex=" + sex
				+ ", publicName=" + publicName + ", publicId=" + publicId + ", publicType=" + publicType + ", u_id="
				+ u_id + ", pid=" + pid + ", state=" + state + ", tid=" + tid + "]";
	}
	
	
	
}
