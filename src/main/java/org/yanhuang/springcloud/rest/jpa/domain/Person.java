/**
 * 
 */
package org.yanhuang.springcloud.rest.jpa.domain;

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
@Table(name = "person")
public class Person extends BaseEntity{

	private static final long serialVersionUID = 4513168467687604168L;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "birthday")
	private LocalDate birthDay;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}
	
}
