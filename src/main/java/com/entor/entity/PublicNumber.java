package com.entor.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 公众号类
 */
public class PublicNumber {
	/**
	 * 公众号编号
	 */
	private String id;
	/**
	 * 公众号账号
	 */
	private String publicId;
	/**
	 * 公众号昵称
	 */
	private String publicName;
	/**
	 * 公众号性质
	 */
	private int publicType;
	/**
	 * 公众号注册人名称
	 */
	private String name;
	/**
	 * 公众号注册联系方式
	 */
	private String phone;
	/**
	 * 公众号注册联系地址
	 */
	private String address;
	/**
	 * 公众号logo图片
	 */
	private String logo;
	/**
	 * 公众号注册日期
	 */
	@JSONField(format="yyyy-MM-dd")
	private Date regDate;
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
	public String getPublicId() {
		return publicId;
	}
	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}
	public String getPublicName() {
		return publicName;
	}
	public void setPublicName(String publicName) {
		this.publicName = publicName;
	}
	public int getPublicType() {
		return publicType;
	}
	public void setPublicType(int publicType) {
		this.publicType = publicType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public PublicNumber(String id, String publicId, String publicName, int publicType, String name, String phone,
			String address, String logo, Date regDate, Timestamp createTime) {
		super();
		this.id = id;
		this.publicId = publicId;
		this.publicName = publicName;
		this.publicType = publicType;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.logo = logo;
		this.regDate = regDate;
		this.createTime = createTime;
	}
	public PublicNumber() {
		super();
	}
	@Override
	public String toString() {
		return "PublicNumber [id=" + id + ", publicId=" + publicId + ", publicName=" + publicName + ", publicType="
				+ publicType + ", name=" + name + ", phone=" + phone + ", address=" + address + ", logo=" + logo
				+ ", regDate=" + regDate + ", createTime=" + createTime + "]";
	}
	
	
	
}
