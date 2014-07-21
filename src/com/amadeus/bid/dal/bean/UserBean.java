package com.amadeus.bid.dal.bean;

import com.amadeus.bid.be.util.EncryptionUtil;
import com.amadeus.bid.dal.contract.IBeanContract;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Text;

/**
 * bean class to represent User data structure
 * @author ssinha
 *
 */
public class UserBean implements IBeanContract {

	private static final long serialVersionUID = 1L;
	
	private static final String encryptionKey = "A2ERuQ@q!0123ASD";

	@Override
	public String getName() {
		return "UserTable";
	}

	@Override
	public Entity getEntity() {
		Entity entity = new Entity(this.getName(), this.getEmail());
		entity.setProperty("username", this.getUsername());
		entity.setProperty("email", this.getEmail());
		entity.setProperty("password", EncryptionUtil.AES.encrypt(this.getPassword(),encryptionKey));
		entity.setProperty("mobile", this.getMobile());
		entity.setProperty("address", this.getAddress());
		entity.setProperty("state", this.getState());
		entity.setProperty("country", this.getCountry());
		entity.setProperty("pincode", this.getPinCode());
		entity.setProperty("aboutme", this.getAboutMe());
		entity.setProperty("feedback", this.getFeedback());
		return entity;
	}
	
	public static UserBean getUserBean(Entity entity) {
		
		UserBean user = new UserBean();
		user.setAboutMe((String)entity.getProperty("aboutme"));
		user.setUsername((String)entity.getProperty("username"));
		user.setEmail((String)entity.getProperty("email"));
		user.setPassword(EncryptionUtil.AES.decrypt((String)entity.getProperty("password"),encryptionKey));
		user.setMobile((String)entity.getProperty("mobile"));
		user.setAddress((String)entity.getProperty("address"));
		user.setState((String)entity.getProperty("state"));
		user.setCountry((String)entity.getProperty("country"));
		user.setPinCode((String)entity.getProperty("pincode"));
		
		if (entity.getProperty("feedback") instanceof String) {
			Text txt = new Text((String)entity.getProperty("feedback"));
			user.setFeedback(txt);
		} else if (entity.getProperty("feedback") instanceof Text) {
			user.setFeedback((Text)entity.getProperty("feedback"));
		}
		
		return user;
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
	
	private Text feedback;

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
		
		if (username == null && this.getEmail() != null) {
			return this.getEmail().substring(0, this.getEmail().indexOf('@'));
		}
		
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

	public Text getFeedback() {
		return feedback;
	}

	public void setFeedback(Text feedback) {
		this.feedback = feedback;
	}
}
