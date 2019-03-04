package com.entor.entity;

import java.util.Date;
/**
 * 公众号粉丝类
 */
public class PublicNumberFans {
	/**
	 * 公众号粉丝ID
	 */
	private String id;
	/**
	 * 公众号编号
	 */
	private String pid;
	/**
	 * 新增粉丝数
	 */
	private String addFans;
	/**
	 * 减少粉丝数
	 */
	private String reduceFans;
	/**
	 * 统计时间
	 */
	private Date countDate;
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
	public String getAddFans() {
		return addFans;
	}
	public void setAddFans(String addFans) {
		this.addFans = addFans;
	}
	public String getReduceFans() {
		return reduceFans;
	}
	public void setReduceFans(String reduceFans) {
		this.reduceFans = reduceFans;
	}
	public Date getCountDate() {
		return countDate;
	}
	public void setCountDate(Date countDate) {
		this.countDate = countDate;
	}
	public PublicNumberFans(String id, String pid, String addFans, String reduceFans, Date countDate) {
		super();
		this.id = id;
		this.pid = pid;
		this.addFans = addFans;
		this.reduceFans = reduceFans;
		this.countDate = countDate;
	}
	public PublicNumberFans() {
		super();
	}
	@Override
	public String toString() {
		return "PublicNumberFans [id=" + id + ", pid=" + pid + ", addFans=" + addFans + ", reduceFans=" + reduceFans
				+ ", countDate=" + countDate + "]";
	}
	
	
	
}
