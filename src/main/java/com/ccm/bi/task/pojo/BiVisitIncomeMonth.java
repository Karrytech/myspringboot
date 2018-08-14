package com.ccm.bi.task.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="bi_visit_income_month")
public class BiVisitIncomeMonth {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	
	/**
	 *aid
	 * 主键，自增
	 */
	@Id
	@Column(name="in_id", length=40)
	private String inId;
	
	@Column(name="patient_type", length=40)
	private String patientType;
	
	@Column(name="icode", length=40)
	private String icode;
	
	@Column(name="idesc", length =128)
	private String idesc;
	
	@Column(name = "bincome", columnDefinition = "decimal(15,2)")
    private BigDecimal bincome;
	
	@Column(name="in_datetime", length =40)
	private String inDatetime;
	
	@Column(name="in_datetime_end", length =40)
	private String inDatetimeEnd;
	
	@Column(name="syc_datetime", length =20)
	private String sycDateTime;
	
	@Column(name="delete_flag", length =20)
	private String deleteFlag;
	
	@Column(name="flag", length =20)
	private String flag;
	
	@Column(name="zchar1", length =256)
	private String zchar1;
	
	@Column(name="zchar2", length =256)
	private String zchar2;
	
	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	@Column(name="times", length =2 )
	private Integer times;

	public String getInId() {
		return inId;
	}

	public void setInId(String inId) {
		this.inId = inId;
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getIcode() {
		return icode;
	}

	public void setIcode(String icode) {
		this.icode = icode;
	}

	public String getIdesc() {
		return idesc;
	}

	public void setIdesc(String idesc) {
		this.idesc = idesc;
	}

	public BigDecimal getBincome() {
		return bincome;
	}

	public void setBincome(BigDecimal bincome) {
		this.bincome = bincome;
	}

	public String getInDatetime() {
		return inDatetime;
	}

	public void setInDatetime(String inDatetime) {
		this.inDatetime = inDatetime;
	}

	public String getInDatetimeEnd() {
		return inDatetimeEnd;
	}

	public void setInDatetimeEnd(String inDatetimeEnd) {
		this.inDatetimeEnd = inDatetimeEnd;
	}

	public String getSycDateTime() {
		return sycDateTime;
	}

	public void setSycDateTime(String sycDateTime) {
		this.sycDateTime = sycDateTime;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getZchar1() {
		return zchar1;
	}

	public void setZchar1(String zchar1) {
		this.zchar1 = zchar1;
	}

	public String getZchar2() {
		return zchar2;
	}

	public void setZchar2(String zchar2) {
		this.zchar2 = zchar2;
	}

	
	
	
	

	
	
	

}
