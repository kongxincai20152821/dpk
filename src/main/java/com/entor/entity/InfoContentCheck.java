package com.entor.entity;

import java.util.Date;
/**
 * 信息内容审核类
 */
public class InfoContentCheck {
	/**
	 * 信息内容审核ID
	 */
	private String id;
	/**
	 * 公众号编号
	 */
	private String pid;
	/**
	 * 信息内容
	 */
	private String content;
	/**
	 * 发布人编号
	 */
	private String u_id;
	/**
	 * 提交时间
	 */
	private Date applyTime;
	/**
	 * 审核是否通过
	 */
	private int state;
	/**
	 * 审核意见
	 */
	private String advise;
	/**
	 * 审核时间
	 */
	private Date checkedTime;
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
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAdvise() {
		return advise;
	}
	public void setAdvise(String advise) {
		this.advise = advise;
	}
	public Date getCheckedTime() {
		return checkedTime;
	}
	public void setCheckedTime(Date checkedTime) {
		this.checkedTime = checkedTime;
	}
	public InfoContentCheck(String id, String pid, String content, String u_id, Date applyTime, int state, String advise,
			Date checkedTime) {
		super();
		this.id = id;
		this.pid = pid;
		this.content = content;
		this.u_id = u_id;
		this.applyTime = applyTime;
		this.state = state;
		this.advise = advise;
		this.checkedTime = checkedTime;
	}
	public InfoContentCheck() {
		super();
	}
	@Override
	public String toString() {
		return "InfoContentCheck [id=" + id + ", pid=" + pid + ", content=" + content + ", u_id=" + u_id + ", applyTime="
				+ applyTime + ", state=" + state + ", advise=" + advise + ", checkedTime=" + checkedTime + "]";
	}
	
	
	
}
