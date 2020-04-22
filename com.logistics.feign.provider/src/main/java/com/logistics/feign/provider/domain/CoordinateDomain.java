package com.logistics.feign.provider.domain;

/**
 * 
 * 简述部分: 坐标位置
 *
 * @author lijiawen
 * @version 2020年4月21日
 */
public class CoordinateDomain {
	private String id;
	private Double longitude;// 经度
	private Double latitude;// 纬度
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

}
