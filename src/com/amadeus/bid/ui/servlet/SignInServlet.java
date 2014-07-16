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
 * servlet class to allow user to sign in
 * @author ssinha
 *
 */
public class SignInServlet extends ApplicationServlet {

	private static final long serialVersionUID = 1L;
	
	private static Pattern pattern;
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	static {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	public SignInServlet() {
		super("login");
	}

	@Override
	protected void doTask(HttpServletRequest req, HttpServletResponse res) {
		
		// read parameters from request
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		List<MessageBean> messages = new ArrayList<MessageBean>();
		
		IUserDataContract user = new UserDataImpl();
		
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
		
		if (messages.size() == 0) {
			UserBean existingUser = user.getUserData(email);
			if (existingUser != null) {
				if (!existingUser.getPassword().equals(password)) {
					MessageBean message = new MessageBean();
					message.setType("E");
					message.setNumber("1002");
					message.setMessage(LocalizationUtil.getString("tx_bidforme_signin_password_error"));
					
					messages.add(message);
				} else {
					// set user in
					req.getSession().setAttribute("user", existingUser);
				}
			} else {
				MessageBean message = new MessageBean();
				message.setType("E");
				message.setNumber("1002");
				message.setMessage(LocalizationUtil.getString("tx_bidforme_signin_no_account"));
				
				messages.add(message);
			}
		}
		
		// set error message in request
		if (messages.size() > 0) {
			req.setAttribute("errors", messages);
		}
	}
	
	@Override
	protected JSONObject getLabels() {
		
		JSONObject labels = new JSONObject();
		if (!(this.getRequest().getAttribute("errors") instanceof List<?>)) {	
			labels.put("tx_bidforme_nav_myprofile", LocalizationUtil.getString("tx_bidforme_nav_myprofile"));
		} else {
			labels.put("tx_bidforme_common_errors", LocalizationUtil.getString("tx_bidforme_common_errors"));
		}
		
		return labels;
	}

	@Override
	protected JSONObject getModel() {
		JSONObject json = new JSONObject();
		if (!(this.getRequest().getAttribute("errors") instanceof List<?>)) {
			json.put("success", true);
		} else {
			json.put("success", false);
		}
				
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
