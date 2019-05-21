package com.juno.entity;

import java.lang.annotation.Annotation;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table
public class Xref implements Entity {
	@Id
	private @Getter String ac;
	private @Getter String created;
	private @Getter String last;
	private @Getter String upi;
	private @Getter String deleted;
	private @Getter int taxid;
	private @Getter Date timestamp;
	private @Getter String userstamp;
	private @Getter int version;
	private @Getter int version_i;
	
	@Override
	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}
}
