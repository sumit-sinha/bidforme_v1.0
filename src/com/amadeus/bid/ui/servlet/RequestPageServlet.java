
package com.amadeus.bid.ui.servlet;

import com.amadeus.bid.be.fwk.LocalizationUtil;
import com.amadeus.bid.ui.constants.IApplicationConstant;
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
		
		json.put("tx_bidforme_request_page_title", LocalizationUtil.getString("tx_bidforme_request_page_title"));

		// for body		
		return json;
	}

	@Override
	protected JSONObject getModel() {
		int aRequestId;
		try {
			aRequestId = Integer.parseInt(this.getRequest().getParameter(IApplicationConstant.CONST_PARAM_REQUEST));
		} catch (NumberFormatException aEx){
			aRequestId = -1;
		}
		
		JSONObject json = new JSONObject();
		json.put("provider_id", getUserId());

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
