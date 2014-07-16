package com.amadeus.bid.ui.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amadeus.bid.ui.fwk.json.JSONObject;
import com.amadeus.bid.ui.generic.ApplicationServlet;

/**
 * servlet class to logout user
 * @author ssinha
 *
 */
public class LogoutServlet extends ApplicationServlet {

	private static final long serialVersionUID = 1L;

	public LogoutServlet() {
		super("logout");
	}

	@Override
	protected void doTask(HttpServletRequest req, HttpServletResponse res) {
		this.getRequest().getSession().removeAttribute("user");
	}
	
	@Override
	protected JSONObject getLabels() {
		return null;
	}

	@Override
	protected JSONObject getModel() {
		return null;
	}

	@Override
	protected JSONObject getErrors() {
		return null;
	}

}
