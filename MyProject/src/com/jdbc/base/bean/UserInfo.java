package com.jdbc.base.bean;

import java.util.Date;

/**
 * @Author Fangys
 * @Desc  
 * @Date 2016年1月20日 下午1:58:57
 * @Version 1.x 
 */
public class UserInfo {
	
	//test 新的分支，在remote新开分支
	private Integer userId;
	private String userNumber;
	private String userEmail;
	private Integer nodeId;
	private Integer isImport;
	private String provCode;
	private String areaCode;
	private Date createDate;
	private Date modifyDate;
	private Integer status;
	private String uin;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Integer getNodeId() {
		return nodeId;
	}
	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}
	public Integer getIsImport() {
		return isImport;
	}
	public void setIsImport(Integer isImport) {
		this.isImport = isImport;
	}
	public String getProvCode() {
		return provCode;
	}
	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUin() {
		return uin;
	}
	public void setUin(String uin) {
		this.uin = uin;
	}
	
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userNumber=" + userNumber
				+ ", userEmail=" + userEmail + ", nodeId=" + nodeId
				+ ", isImport=" + isImport + ", provCode=" + provCode
				+ ", areaCode=" + areaCode + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", status=" + status
				+ ", uin=" + uin + "]";
	}
}
