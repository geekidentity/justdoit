package com.justdoit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 签到表，包含员工签到的所有信息
 * @author geek1994
 * 
 * 2016年3月19日
 */
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class Reach implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5226701375916405085L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date reachTime;//签到时间
	private String position;//地图上的地理描述位置
	private Float lng;//签到时地理经度
	private Float lat;//签到时地理纬度
	private String ip;//签到时IP地址
	private Integer employee;
	private Integer type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getReachTime() {
		return reachTime;
	}
	public void setReachTime(Date reachTime) {
		this.reachTime = reachTime;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Integer getEmployee() {
		return employee;
	}
	public void setEmployee(Integer employee) {
		this.employee = employee;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
