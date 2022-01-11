package com.neosoft.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column
	@NotNull
	private String fname;
	
	@Column
	@NotNull
	private String surname;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date DOB;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date joiningDate;
	
	@Column
	@Pattern(regexp="(^$|[0-9]{10})")
	private String mobileNo;
	
	@Column
	@Pattern(regexp = ("^[0-9]{6}"))
	private String pincode;
	
	@Column
    private boolean deleted;
	
}
