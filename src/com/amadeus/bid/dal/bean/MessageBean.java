package com.amadeus.bid.dal.bean;

import java.io.Serializable;
import java.util.List;

/**
 * bean class to define message structure
 * @author ssinha
 *
 */
public class MessageBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	private String number;
	
	private String type;
	
	private List<MessageBean> submessages;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<MessageBean> getSubmessages() {
		return submessages;
	}

	public void setSubmessages(List<MessageBean> submessages) {
		this.submessages = submessages;
	}
}
