package com.ds.moviemate.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


//user entity
@Data
@ToString
@NoArgsConstructor
@Table(name = "users")
@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	private String fullName;
	private int age;
	private String userName;
	private Long contactNumber;
	private String email;
	private String password;
	@OneToOne(fetch = FetchType.EAGER)
	private RoleEntity role;
	@OneToMany(fetch = FetchType.EAGER)
	private List<BookingEntity> bookingId;

}