package com.entor.entity;

 
import com.sun.jmx.snmp.Timestamp;
/**
 * 信息内容类
 */
public class InfoContent {
	/**
	 * 信息内容ID
	 */
	private String id;
	/**
	 * 公众号编号
	 */
	private String pid;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 内容模板编号
	 */
	private String tid;
	/**
	 * 发布人编号
	 */
	private String u_id;
	/**
	 * 发布时间
	 */
	private Timestamp createTime;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public InfoContent(String id, String pid, String content, String tid, String u_id, Timestamp createTime) {
		super();
		this.id = id;
		this.pid = pid;
		this.content = content;
		this.tid = tid;
		this.u_id = u_id;
		this.createTime = createTime;
	}
	public InfoContent() {
		super();
	}
	@Override
	public String toString() {
		return "InfoContent [id=" + id + ", pid=" + pid + ", content=" + content + ", tid=" + tid + ", u_id=" + u_id
				+ ", createTime=" + createTime + "]";
	}
	
	
}
