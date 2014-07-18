package com.amadeus.bid.ui.servlet;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amadeus.bid.be.fwk.LocalizationUtil;
import com.amadeus.bid.be.util.SendEmailUtil;
import com.amadeus.bid.dal.bean.MessageBean;
import com.amadeus.bid.dal.bean.UserBean;
import com.amadeus.bid.dal.contract.IUserDataContract;
import com.amadeus.bid.dal.impl.UserDataImpl;
import com.amadeus.bid.ui.fwk.json.JSONArray;
import com.amadeus.bid.ui.fwk.json.JSONObject;
import com.amadeus.bid.ui.generic.ApplicationServlet;
import javax.mail.MessagingException;

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
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		List<MessageBean> messages = new ArrayList<MessageBean>();
		
		IUserDataContract user = new UserDataImpl();
		
		if (email == null || email.trim().equals("") || !pattern.matcher(email).matches()) {
			MessageBean message = new MessageBean();
			message.setType("E");
			message.setNumber("1001");
			message.setMessage(LocalizationUtil.getString("tx_bidforme_registration_email_error"));
			
			messages.add(message);
		} else {
			UserBean existingUser = user.getUserData(email);
			if (existingUser != null) {
				MessageBean message = new MessageBean();
				message.setType("E");
				message.setNumber("1004");
				message.setMessage(LocalizationUtil.getString("tx_bidforme_registration_existing"));
				
				messages.add(message);
			}
		}
		
		if (password == null || password.trim().equals("")) {
			MessageBean message = new MessageBean();
			message.setType("E");
			message.setNumber("1002");
			message.setMessage(LocalizationUtil.getString("tx_bidforme_registration_password_error"));
			
			messages.add(message);
		} else if (!password.equals(confirmPassword)) {
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
			user.saveUserData(bean);
			
			this.sendEmail(bean);
		} else {
			req.setAttribute("errors", messages);
		}
		
	}
	
	public RegisterUserServlet() {
		super("register");
	}

	@Override
	protected JSONObject getLabels() {
		
		JSONObject labels = new JSONObject();
		if (!(this.getRequest().getAttribute("errors") instanceof List<?>)) {	
			labels.put("tx_bidforme_registration_success", LocalizationUtil.getString("tx_bidforme_registration_success"));
		} else {
			labels.put("tx_bidforme_common_errors", LocalizationUtil.getString("tx_bidforme_common_errors"));
		}
		
		return labels;
	}

	@Override
	protected JSONObject getModel() {
		
		JSONObject json = new JSONObject();
		json.put("success", !(this.getRequest().getAttribute("errors") instanceof List<?>));		
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
	
	/**
	 * 683194008
	 * @param user
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	private void sendEmail(UserBean user) {
		
		MessageFormat emailContent = new MessageFormat("<link href=\"http://there-u-go.appspot.com/resources/css/bootstrap.min.css\" rel=\"stylesheet\">" +
									"<link href=\"http://there-u-go.appspot.com//resources/css/email-template.css\" rel=\"stylesheet\">" +
									"<div class=\"container content\">" +
										"<div class=\"page-header\">" +
											"<h1>{0}</h1>" +
										"</div>" +
										"<p class=\"lead\">{1}</p>" +
										"<p>{2}</p>" +
									"</div>" +
									"<div class=\"footer\">" +
										"<div class=\"container logo\">" +
											"<p class=\"text-muted\">&nbsp;</p>" +
										"</div>" +
									"</div>");
		
		
		String[] args = new String[3];
		args[0] = "Dear Traveller";
		args[1] = "Thanks a lot for signing up for Thereyougo! The whole team is delighted to have you onboard. We will notify you as soon as the site is up and running. In the mean time, we are working on building our travel expert network. Please feel free to share with us an early feedback.";
		args[2] = "Thereyougo Team";

        //adding group name and email to message
        String message = emailContent.format(args);

        SendEmailUtil emailUtil = new SendEmailUtil();
        emailUtil.setFrom("amadeuseia14@gmail.com");
        emailUtil.setTo(user.getEmail());
        emailUtil.setMessage(message);
        emailUtil.setSubject("Thank you for registration!!!");
        emailUtil.setMessageType("text/html");
        
        emailUtil.sendMail();
	}
}
