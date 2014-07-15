package com.amadeus.bid.ui.servlet;

import javax.servlet.http.HttpServletRequest;

import com.amadeus.bid.be.fwk.LocalizationUtil;
import com.amadeus.bid.ui.constants.IApplicationConstant;
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
		fillHeaderLabels(json);
		
		// for body
		
		return json;
	}

	@Override
	protected JSONObject getModel(HttpServletRequest iRequest) {
		
		int aUserId;
		try {
			aUserId = Integer.parseInt(iRequest.getParameter(IApplicationConstant.CONST_PARAM_USER));
		} catch (NumberFormatException aEx){
			aUserId = -1;
		}
		
		JSONObject json = new JSONObject();
		json.put("user_id", aUserId);
		
		return json;
	}

	@Override
	protected JSONObject getErrors() {
		return null;
	}

}
