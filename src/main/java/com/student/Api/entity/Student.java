package com.student.Api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@NotBlank(message = "First name is mandatory")
	private String first_Name;

	@Column
	@NotBlank(message = "Last name is mandatory")
	private String last_Name;

	@Column
	@NotBlank(message = "age is mandatory")
	private int age;

	@Column
	@NotBlank(message = "gender is mandatory")
	private String gender;

	@Column
	@NotBlank(message = "Address is mandatory")
	private String address;

	@Column
	@Pattern(regexp = "^\\d{10}$", message = "Mobile number should be a valid 10-digit number")
	private String mobile_no;

	public Student() {
	}

	public Student(String first_Name, String last_Name, int age, String gender, String address, String mobile_no) {
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.mobile_no = mobile_no;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_Name() {
		return first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public String getLast_Name() {
		return last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", first_Name=" + first_Name + ", last_Name=" + last_Name + ", age=" + age
				+ ", gender=" + gender + ", address=" + address + ", mobile_no=" + mobile_no + "]";
	}
}
