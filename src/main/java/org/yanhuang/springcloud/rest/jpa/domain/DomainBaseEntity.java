package org.yanhuang.springcloud.rest.jpa.domain;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.yanhuang.springcloud.rest.entity.BaseEntity;
import org.yanhuang.springcloud.rest.jpa.domain.security.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *  base entity in default datasource (domain)
 * 
 * @author zhyhang
 *
 */
@MappedSuperclass
@EntityListeners({ AuditingEntityListener.class })
public abstract class DomainBaseEntity extends BaseEntity {

	private static final long serialVersionUID = 6822687111198055490L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="creator_id")
	@CreatedBy
	@JsonIgnore
	private User creator;
	
	@Column(name="creator_id",insertable = false, updatable = false, nullable = false)
	private Long creatorId;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="last_modifier_id")
	@LastModifiedBy
	@JsonIgnore
	private User lastModifier;
	
	@Column(name="last_modifier_id",insertable = false, updatable = false, nullable = false)
	private Long last_modifier_id;
	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public User getLastModifier() {
		return lastModifier;
	}

	public void setLastModifier(User lastModifier) {
		this.lastModifier = lastModifier;
	}

	public Long getLast_modifier_id() {
		return last_modifier_id;
	}

	public void setLast_modifier_id(Long last_modifier_id) {
		this.last_modifier_id = last_modifier_id;
	}

}
