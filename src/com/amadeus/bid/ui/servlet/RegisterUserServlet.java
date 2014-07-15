package com.amadeus.bid.ui.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amadeus.bid.be.fwk.LocalizationUtil;
import com.amadeus.bid.dal.bean.MessageBean;
import com.amadeus.bid.dal.bean.UserBean;
import com.amadeus.bid.dal.contract.IUserDataContract;
import com.amadeus.bid.dal.impl.UserDataImpl;
import com.amadeus.bid.ui.fwk.json.JSONArray;
import com.amadeus.bid.ui.fwk.json.JSONObject;
import com.amadeus.bid.ui.generic.ApplicationServlet;

/**
 * servlet class used to register user to database
 * @author ssinha
 *
 */
public class RegisterUserServlet extends ApplicationServlet {

	private static final long serialVersionUID = 1L;
	
	private static Pattern pattern;
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	static {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	@Override
	protected void doTask(HttpServletRequest req, HttpServletResponse res) {
		
		// read parameters from request
		String email = req.getParameter("");
		String password = req.getParameter("");
		String confirmPassword = req.getParameter("");
		List<MessageBean> messages = new ArrayList<MessageBean>();
		
		if (email == null || email.trim().equals("") || !pattern.matcher(email).matches()) {
			MessageBean message = new MessageBean();
			message.setType("E");
			message.setNumber("1001");
			message.setMessage(LocalizationUtil.getString("tx_bidforme_registration_email_error"));
			
			messages.add(message);
		}
		
		if (password == null || password.trim().equals("")) {
			MessageBean message = new MessageBean();
			message.setType("E");
			message.setNumber("1002");
			message.setMessage(LocalizationUtil.getString("tx_bidforme_registration_password_error"));
			
			messages.add(message);
		}
		
		if (!password.equalsIgnoreCase(confirmPassword)) {
			MessageBean message = new MessageBean();
			message.setType("E");
			message.setNumber("1003");
			message.setMessage(LocalizationUtil.getString("tx_bidforme_registration_no_pwd_match"));
			
			messages.add(message);
		}
		
		if (messages.size() == 0) {
			UserBean bean = new UserBean();
			bean.setEmail(email);
			bean.setPassword(password);
			
			IUserDataContract user = new UserDataImpl();
			user.saveUserData(bean);
		} else {
			req.setAttribute("errors", messages);
		}
		
	}
	
	public RegisterUserServlet() {
		super("register");
	}

	@Override
	protected JSONObject getLabels() {
		return null;
	}

	@Override
	protected JSONObject getModel() {
		
		JSONObject json = new JSONObject();
		json.put("success", this.getRequest().getAttribute("errors") instanceof List<?>);		
		return json;
	}

	@Override
	protected JSONObject getErrors() {
		
		JSONArray errors = new JSONArray();
		if (this.getRequest().getAttribute("errors") instanceof List<?>) {
			for (int i = 0; i < ((List<?>)this.getRequest().getAttribute("errors")).size(); i++) {
				JSONObject error = new JSONObject(((List<?>)this.getRequest().getAttribute("errors")).get(i));
				errors.put(error);
			}
		}
		
		return new JSONObject().put("validation_error", errors);
	}

}
