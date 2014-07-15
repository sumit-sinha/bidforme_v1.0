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
	protected JSONObject getModel(HttpServletRequest iRequest) {
		int aRequestId;
		try {
			aRequestId = Integer.parseInt(iRequest.getParameter(IApplicationConstant.CONST_PARAM_REQUEST));
		} catch (NumberFormatException aEx){
			aRequestId = -1;
		}
		
		JSONObject json = new JSONObject();
		json.put("request_id", aRequestId);
		json.put("request_origin", "NCE");
		json.put("request_destination", "JFK");
		json.put("request_comment", "bla bla bla");
		
		return json;
	}

	@Override
	protected JSONObject getErrors() {
		return null;
	}

}
