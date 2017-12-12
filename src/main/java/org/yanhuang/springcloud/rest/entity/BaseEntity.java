package org.yanhuang.springcloud.rest.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.yanhuang.springcloud.rest.util.JsonUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * base entity
 * 
 * @author zhyhang
 *
 */
@MappedSuperclass
@EntityListeners({ AuditingEntityListener.class })
public abstract class BaseEntity implements Serializable,Cloneable {

	private static final long serialVersionUID = 4865664298299542863L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable=false)
	private Long id;
	
	@Version
	@Column(name = "version")
	private long version = -1;

	@Column(name = "creation",updatable=false)
	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creation;

	@Column(name = "last_modified")
	@LastModifiedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastModified;
	
	@Column(name="active")
	private boolean active = true;
	
	@Column(name="removed")
	private boolean removed = false;
	
	// TODO abac property(e.g. parentid)
	@Override
	public int hashCode() {
		if(getId()!=null) {
			return getId().hashCode();
		}
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		}
		if(this.getId()==null || ((BaseEntity)obj).getId()==null) {
			return super.equals(obj);
		}
		return Objects.equals(this.getId(), ((BaseEntity)obj).getId());
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public LocalDateTime getCreation() {
		return creation;
	}

	public void setCreation(LocalDateTime creation) {
		this.creation = creation;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

}
