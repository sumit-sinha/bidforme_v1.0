package com.amadeus.bid.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amadeus.bid.ui.fwk.json.JSONObject;
import com.amadeus.bid.ui.generic.ApplicationServlet;

/**
 * servlet class used as an entry point for Providers
 * @author ssinha
 *
 */
public class TravelProviderWelcomeServlet extends ApplicationServlet {

	private static final long serialVersionUID = 1L;

	public TravelProviderWelcomeServlet() {
		super("provider");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("resources/html/provider.jsp").include(req, resp);
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
