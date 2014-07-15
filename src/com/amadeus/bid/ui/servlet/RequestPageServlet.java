package com.amadeus.bid.ui.servlet;

import com.amadeus.bid.be.fwk.LocalizationUtil;
import com.amadeus.bid.ui.fwk.json.JSONArray;
import com.amadeus.bid.ui.fwk.json.JSONObject;
import com.amadeus.bid.ui.generic.ApplicationServlet;

/**
 * creates data required to display the provider page
 * @author pmoulinier
 *
 */
public class RequestPageServlet extends ApplicationServlet {
	
	private static final long serialVersionUID = 1L;
	
	public RequestPageServlet() {
		super("request");
	}

	@Override
	protected JSONObject getLabels() {
		
		JSONObject json = new JSONObject();
		
		// labels for header
		this.fillHeaderLabels(json);

		// for body		
		return json;
	}

	@Override
	protected JSONObject getModel() {
		JSONObject json = new JSONObject();
		json.put("provider_id", userId);
		return json;
	}

	@Override
	protected JSONObject getErrors() {
		return null;
	}

}
