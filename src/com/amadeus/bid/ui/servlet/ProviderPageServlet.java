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
public class ProviderPageServlet extends ApplicationServlet {
	
	private static final long serialVersionUID = 1L;
	
	public ProviderPageServlet() {
		super("provider");
	}

	@Override
	protected JSONObject getLabels() {
		
		JSONObject json = new JSONObject();
		
		// labels for header
		json.put("tx_bidforme_app_name", LocalizationUtil.getString("tx_bidforme_app_name"));
		json.put("tx_bidforme_nav_home", LocalizationUtil.getString("tx_bidforme_nav_home"));
		json.put("tx_bidforme_nav_what", LocalizationUtil.getString("tx_bidforme_nav_what"));
		json.put("tx_bidforme_nav_contactus", LocalizationUtil.getString("tx_bidforme_nav_contactus"));
		json.put("tx_bidforme_nav_signin", LocalizationUtil.getString("tx_bidforme_nav_signin"));
		json.put("tx_bidforme_nav_register", LocalizationUtil.getString("tx_bidforme_nav_register"));
		
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
