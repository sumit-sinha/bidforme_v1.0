package com.amadeus.bid.ui.servlet;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amadeus.bid.be.contract.ITravelProvider;
import com.amadeus.bid.be.fwk.LocalizationUtil;
import com.amadeus.bid.be.impl.TravelProviderImpl;
import com.amadeus.bid.be.util.SendEmailUtil;
import com.amadeus.bid.dal.bean.MessageBean;
import com.amadeus.bid.dal.bean.TravelProviderBean;
import com.amadeus.bid.dal.bean.UserBean;
import com.amadeus.bid.ui.fwk.json.JSONArray;
import com.amadeus.bid.ui.fwk.json.JSONObject;
import com.amadeus.bid.ui.generic.ApplicationServlet;

/**
 * servlet used to Register a provider
 * @author ssinha
 *
 */
public class RegisterProviderServlet extends ApplicationServlet {

	private static final long serialVersionUID = 1L;
	
	public RegisterProviderServlet() {
		super("register_provider");
	}

	@Override
	protected void doTask(HttpServletRequest req, HttpServletResponse res) {
		
		// read parameters from request
		String email = req.getParameter("email");
		String feedback = req.getParameter("feedback");
		String companyname = req.getParameter("company_name");
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String country = req.getParameter("country");
		
		UserBean user = new UserBean();
		user.setEmail(email);
		user.setFeedback(feedback);
		user.setAddress(address);
		user.setCity(city);
		user.setState(state);
		user.setCountry(country);
		
		TravelProviderBean provider = new TravelProviderBean();
		provider.setCompanyName(companyname);
		provider.setUserInfo(user);
		
		ITravelProvider travelProvider = new TravelProviderImpl();
		List<MessageBean> messages = travelProvider.addProvider(provider);
		if (messages == null || messages.size() == 0) {
			this.sendEmail(provider);
		} else {
			req.setAttribute("errors", messages);
		}
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
	 * @param user
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	private void sendEmail(TravelProviderBean provider) {
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
		args[0] = "Dear " + provider.getCompanyName();
		args[1] = "Thanks a lot for signing up for Thereyougo! The whole team is delighted to have you onboard. We will notify you as soon as the site is up and running. In the mean time, we are working on engaging more travellers. Please feel free to share with us an early feedback.";
		args[2] = "Thereyougo Team";

        //adding group name and email to message
        String message = emailContent.format(args);

        SendEmailUtil emailUtil = new SendEmailUtil();
        emailUtil.setFrom("amadeuseia14@gmail.com");
        emailUtil.setTo(provider.getUserInfo().getEmail());
        emailUtil.setMessage(message);
        emailUtil.setSubject("Thank you for registration!!!");
        emailUtil.setMessageType("text/html");
        
        emailUtil.sendMail();
	}
}
