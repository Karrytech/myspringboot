package com.ccm.base.sys.po;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
//@Table(name="dtmj_user")
public class DtmjUser /*extends BaseDomain*/{

	private static final long serialVersionUID = 1L;
	
	/**
	 * OID
	 * 主键，自增
	 */
	@Id
	@GeneratedValue/*(strategy= GenerationType.IDENTITY)*/
	@Column(name="user_id", length=40)
	private String userId;
	
	@Column(name="user_name", length =40)
	private String userName;
	
	@Column(name="user_pwd", length =80)
	private String userPwd;
	
	@Column(name="id_type", length=80)
	private String idType;
	
	@Column(name="id_no", length =80)
	private String idNo;
	
	@Column(name="person_name", length =80)
	private String personName;
	
	@Column(name="mobile_phone", length =20)
	private String mobilePhone;
	
	@Column(name="email", length =80)
	private String email;
	
	@Column(name="card_no", length =80)
	private String cardNo;
	
	@Column(name="address", length =80)
	private String address;
	
	@Column(name="last_update_date")
	private Timestamp lastUpdateDate;
	
	@Column(name="remark", length =256)
	private String remark;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "DtmjUser{" +
				"userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", userPwd='" + userPwd + '\'' +
				", idType='" + idType + '\'' +
				", idNo='" + idNo + '\'' +
				", personName='" + personName + '\'' +
				", mobilePhone='" + mobilePhone + '\'' +
				", email='" + email + '\'' +
				", cardNo='" + cardNo + '\'' +
				", address='" + address + '\'' +
				", lastUpdateDate=" + lastUpdateDate +
				", remark='" + remark + '\'' +
				'}';
	}
}
