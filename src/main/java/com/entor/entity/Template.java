package com.entor.entity;

import com.sun.jmx.snmp.Timestamp;
/**
 * 内容模板类
 */
public class Template {
	/**
	 * 内容模板编号
	 */
	private String id;
	/**
	 * 内容模板名
	 */
	private String name;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 类型
	 */
	private int type;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建人
	 */
	private String u_id;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Template(String id, String name, String content, int type, String remark, String u_id,
			Timestamp createTime) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.type = type;
		this.remark = remark;
		this.u_id = u_id;
		this.createTime = createTime;
	}
	public Template() {
		super();
	}
	@Override
	public String toString() {
		return "Template [id=" + id + ", name=" + name + ", content=" + content + ", type=" + type + ", remark="
				+ remark + ", u_id=" + u_id + ", createTime=" + createTime + "]";
	}

	
	
	
}
