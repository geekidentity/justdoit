package com.justdoit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 员工外勤申请表
 * @author geek1994
 * 
 * 2016年4月18日
 */
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class LegWork implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7416267095539100826L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(updatable=false)
	private Integer id; //外勤流水号
	@Column(updatable=false)
	private Integer employee;//员工工号（外键）
	@Column(updatable=false)
	private String reason;//外勤事由
	private Short status;//外勤状态，1：已完成0：未完成
	@Column(updatable=false)
	private String position;//地图上的地理描述位置
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@Column(updatable=false)
	private Date applyTime;//外勤申请时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@Column(updatable=false)
	private Date reachTime;//到达时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@Column(updatable=false)
	private Date leaveTime;//离开时间
	@Column(updatable=false)
	private Float lng;//地理经度
	@Column(updatable=false)
	private Float lat;//地理纬度
	@Column(updatable=false)
	private Float reachLng;//外勤签到的地理经度
	@Column(updatable=false)
	private Float reachLat;//外勤签到的地理纬度
	@Column(updatable=false)
	private Float backLng;//外勤签退的地理经度
	@Column(updatable=false)
	private Float backLat;//外勤签退的地理纬度
	@Column(updatable=false)
	private String ip;//IP地址
	private String title;
	@Transient
	private Short type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmployee() {
		return employee;
	}
	public void setEmployee(Integer employee) {
		this.employee = employee;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Date getReachTime() {
		return reachTime;
	}
	public void setReachTime(Date reachTime) {
		this.reachTime = reachTime;
	}
	public Date getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
	public Float getLng() {
		return lng;
	}
	public void setLng(Float lng) {
		this.lng = lng;
	}
	public Float getLat() {
		return lat;
	}
	public void setLat(Float lat) {
		this.lat = lat;
	}
	
	public Float getReachLng() {
		return reachLng;
	}
	public void setReachLng(Float reachLng) {
		this.reachLng = reachLng;
	}
	public Float getReachLat() {
		return reachLat;
	}
	public void setReachLat(Float reachLat) {
		this.reachLat = reachLat;
	}
	public Float getBackLng() {
		return backLng;
	}
	public void setBackLng(Float backLng) {
		this.backLng = backLng;
	}
	public Float getBackLat() {
		return backLat;
	}
	public void setBackLat(Float backLat) {
		this.backLat = backLat;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
