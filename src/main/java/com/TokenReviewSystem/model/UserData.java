package com.TokenReviewSystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idData;
	private String firstname;
	private String middlename;
	private String lastname;
	private String phonenumber;
	private String city;
	private String state;
	private int pincode;

	public static boolean isValidEmail(String email)
	{
		return email.contains("@");
	}
	public static boolean isValidPhone(String phn)
	{
		String regex = "^[7-9][0-9]{9}";
		return phn.matches(regex);
	}
	public static boolean isValidPin(String pin)
	{
		String regex = "^[1-9][0-9]{5}$";
		return pin.matches(regex);
	}
	public UserData() {
		super();
	}
	public UserData(int idData, String firstname, String middlename, String lastname, String phonenumber, String city,
			String state, int pincode) {
		super();
		this.idData = idData;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.phonenumber = phonenumber;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}
	public int getIdData() {
		return idData;
	}
	public void setIdData(int idData) {
		this.idData = idData;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}


	
	
}
