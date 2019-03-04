package com.entor.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
/**
 *	公众号审批类
 */
public class PublicNumberCheck {
	/**
	 *	公众号审批ID
	 */
	private String id;
	/**
	 *	公众号编号
	 */
	private String pid;
	/**
	 *	申请人编号
	 */
	private String u_id;
	/**
	 *	提交时间
	 */
	@JSONField(format="yyyy-MM-dd")
	private Date applyTime;
	/**
	 *	审核意见
	 */
	private String advise;
	/**
	 *	审核是否通过
	 */
	private int state;
	/**
	 *	审核时间
	 */
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp checkedTime;
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
	public String getAdvise() {
		return advise;
	}
	public void setAdvise(String advise) {
		this.advise = advise;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Timestamp getCheckedTime() {
		return checkedTime;
	}
	public void setCheckedTime(Timestamp checkedTime) {
		this.checkedTime = checkedTime;
	}
	public PublicNumberCheck(String id, String pid, String u_id, Date applyTime, String advise, int state,
			Timestamp checkedTime) {
		super();
		this.id = id;
		this.pid = pid;
		this.u_id = u_id;
		this.applyTime = applyTime;
		this.advise = advise;
		this.state = state;
		this.checkedTime = checkedTime;
	}
	public PublicNumberCheck() {
		super();
	}
	@Override
	public String toString() {
		return "PublicNumberCheck [id=" + id + ", pid=" + pid + ", u_id=" + u_id + ", applyTime=" + applyTime
				+ ", advise=" + advise + ", state=" + state + ", checkedTime=" + checkedTime + "]";
	}


	
	
	
}
