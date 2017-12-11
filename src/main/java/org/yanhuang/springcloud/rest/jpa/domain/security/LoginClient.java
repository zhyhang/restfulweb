/**
 * 
 */
package org.yanhuang.springcloud.rest.jpa.domain.security;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.yanhuang.springcloud.rest.entity.BaseEntity;

/**
 * @author zhyhang
 *
 */
@Entity
@Table(name="security_login_log")
public class LoginClient extends BaseEntity {
	
	private static final long serialVersionUID = 5195716771466450402L;
	
	public static enum loginType{
		login_form,
		login_oauth2,
		login_openid,
		logout,
		session_expire;
	}

	@Column(name="userid")
	private Long userid;
	
	@Column(name="username")
	private String username;
	
	@Column(name="session")
	private String session;
	
	@Column(name="action_type")
	@Enumerated(EnumType.STRING)
	private loginType actionType;
	
	@Column(name="action_time")
	private LocalDateTime actionTime;
	
	@Column(name="ipv4")
	private String ipv4;
	
	@Column(name="ipv6")
	private String ipv6;
	
	@Column(name="ua")
	private String ua;
	
	@Column(name="refer_url")
	private String referUrl;
	
	@Column(name="device_type")
	private String deviceType;
	
	@Column(name="device_os")
	private String deviceOs;
	
	@Column(name="device_osv")
	private String deviceOsv;
	
	@Column(name="device_brand")
	private String deviceBrand;
	
	@Column(name="device_model")
	private String deviceModel;
	
	@Column(name="app")
	private String app;
	
	@Column(name="app_version")
	private String appVersion;

	@Column(name="cookie")
	private String cookie;

	@Column(name="did")
	private String did;
	
	@Column(name="did_type")
	private String didType;
	
	@Column(name="did_encode_type")
	private String didEncodeType;
	
	@Column(name="latitude")
	private Double latitude;
	
	@Column(name="longtitude")
	private Double longitude;
	
	@Column(name="last_access")
	private LocalDateTime lastAccess;
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public loginType getActionType() {
		return actionType;
	}

	public void setActionType(loginType actionType) {
		this.actionType = actionType;
	}

	public LocalDateTime getActionTime() {
		return actionTime;
	}

	public void setActionTime(LocalDateTime actionTime) {
		this.actionTime = actionTime;
	}

	public String getIpv4() {
		return ipv4;
	}

	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}

	public String getIpv6() {
		return ipv6;
	}

	public void setIpv6(String ipv6) {
		this.ipv6 = ipv6;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getReferUrl() {
		return referUrl;
	}

	public void setReferUrl(String referUrl) {
		this.referUrl = referUrl;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceOs() {
		return deviceOs;
	}

	public void setDeviceOs(String deviceOs) {
		this.deviceOs = deviceOs;
	}

	public String getDeviceOsv() {
		return deviceOsv;
	}

	public void setDeviceOsv(String deviceOsv) {
		this.deviceOsv = deviceOsv;
	}

	public String getDeviceBrand() {
		return deviceBrand;
	}

	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getDidType() {
		return didType;
	}

	public void setDidType(String didType) {
		this.didType = didType;
	}

	public String getDidEncodeType() {
		return didEncodeType;
	}

	public void setDidEncodeType(String didEncodeType) {
		this.didEncodeType = didEncodeType;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public LocalDateTime getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(LocalDateTime lastAccess) {
		this.lastAccess = lastAccess;
	}

}

