package com.amadeus.bid.dal.bean;

import com.amadeus.bid.dal.contract.IBeanContract;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PropertyContainer;

/**
 * bean class to represent User data structure
 * @author ssinha
 *
 */
public class UserBean implements IBeanContract {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "UserTable";
	}

	@Override
	public Entity getEntity() {
		Entity entity = new Entity(this.getName(), this.getEmail());
		entity.setProperty("username", this.getUsername());
		entity.setProperty("email", this.getEmail());
		entity.setProperty("password", this.getPassword());
		entity.setProperty("mobile", this.getMobile());
		entity.setProperty("address", this.getAddress());
		entity.setProperty("state", this.getState());
		entity.setProperty("country", this.getCountry());
		entity.setProperty("pincode", this.getPinCode());
		entity.setProperty("aboutme", this.getAboutMe());
		return entity;
	}
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String mobile;
	
	private String address;
	
	private String state;
	
	private String country;
	
	private String pinCode;
	
	private String aboutMe;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
