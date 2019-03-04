package com.entor.entity;

/**
 *	公众号跟用户绑定类VO
 */
public class UserPublicNumberVO {
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
	/**
	 *	用户名
	 */
	private String name;
	/**
	 *	公众号账号
	 */
	private String publicId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPublicId() {
		return publicId;
	}
	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}
	public UserPublicNumberVO(String id, String u_id, String pid, String name, String publicId) {
		super();
		this.id = id;
		this.u_id = u_id;
		this.pid = pid;
		this.name = name;
		this.publicId = publicId;
	}
	
	public UserPublicNumberVO() {
		super();
	}
	@Override
	public String toString() {
		return "UserPublicNumberVO [id=" + id + ", u_id=" + u_id + ", pid=" + pid + ", name=" + name + ", publicId="
				+ publicId + "]";
	}
	

	
}
