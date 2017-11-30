/**
 * 
 */
package org.yanhuang.springcloud.rest.jpa.b.domain;

import java.math.BigDecimal;
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
@Table(name = "company")
public class Company extends BaseEntity{

	private static final long serialVersionUID = 2039423523288255421L;

	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;

	@Column(name = "found_date")
	private LocalDate foundDate;
	
	@Column(name = "income")
	private BigDecimal income;

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

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public Boolean getMinor() {
		return minor;
	}

	public void setMinor(Boolean minor) {
		this.minor = minor;
	}

}
