package com.entor.entity;
/**
 *	公众号模板绑定类
 */
public class PublicNumberTemplate {
	/**
	 * ID
	 */
	private String id;
	/**
	 * 公众号编号
	 */
	private String pid;
	/**
	 * 模板编号
	 */
	private String tid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public PublicNumberTemplate(String id, String pid, String tid) {
		super();
		this.id = id;
		this.pid = pid;
		this.tid = tid;
	}
	public PublicNumberTemplate() {
		super();
	}
	@Override
	public String toString() {
		return "PublicNumberTemplate [id=" + id + ", pid=" + pid + ", tid=" + tid + "]";
	}
	
	
	
}
