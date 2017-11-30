/**
 * 
 */
package org.yanhuang.springcloud.rest.jpa.domain.o;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.yanhuang.springcloud.rest.entity.BaseEntity;

/**
 * @author zhyhang
 *
 */
@Entity
@Table(name = "nation")
public class Nation extends BaseEntity{

	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;

	@Column(name = "found_date")
	private LocalDate foundDate;
	
	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "minor")
	private Boolean minor;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDate getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(LocalDate foundDate) {
		this.foundDate = foundDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getMinor() {
		return minor;
	}

	public void setMinor(Boolean minor) {
		this.minor = minor;
	}
	
}
