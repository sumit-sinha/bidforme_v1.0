

package com.amadeus.bid.ui.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amadeus.bid.be.fwk.LocalizationUtil;
import com.amadeus.bid.ui.constants.IApplicationConstant;
import com.amadeus.bid.ui.fwk.json.JSONObject;
import com.amadeus.bid.ui.generic.ApplicationServlet;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * creates data required to display the provider page
 * @author pmoulinier
 *
 */
public class RequestPageServlet extends ApplicationServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected Key travelRequestKey;
	
	public RequestPageServlet() {
		super("request");
	}

	@Override
	protected JSONObject getLabels() {
		
		JSONObject json = new JSONObject();
		
		// labels for header
		this.fillHeaderLabels(json);
		
		json.put("tx_bidforme_request_page_title", LocalizationUtil.getString("tx_bidforme_request_page_title"));
		json.put("tx_bidforme_request_travel_summary", LocalizationUtil.getString("tx_bidforme_request_travel_summary"));
		json.put("tx_bidforme_request_adults", LocalizationUtil.getString("tx_bidforme_request_adults"));
		json.put("tx_bidforme_request_children", LocalizationUtil.getString("tx_bidforme_request_children"));
		json.put("tx_bidforme_request_travelfrom", LocalizationUtil.getString("tx_bidforme_request_travelfrom"));
		json.put("tx_bidforme_request_to", LocalizationUtil.getString("tx_bidforme_request_to"));
		json.put("tx_bidforme_request_return_date", LocalizationUtil.getString("tx_bidforme_request_return_date"));
		json.put("tx_bidforme_travelers", LocalizationUtil.getString("tx_bidforme_travelers"));
		json.put("tx_bidforme_budget", LocalizationUtil.getString("tx_bidforme_budget"));
		json.put("tx_bidforme_itinerary", LocalizationUtil.getString("tx_bidforme_itinerary"));
		json.put("tx_bidforme_criteria", LocalizationUtil.getString("tx_bidforme_criteria"));

		// for body		
		return json;
	}

	@Override
	protected void doTask(HttpServletRequest req, HttpServletResponse res) {
		travelRequestKey = null;
		try {
			travelRequestKey = KeyFactory.stringToKey(req.getParameter(IApplicationConstant.CONST_PARAM_REQUEST));
		} catch (NumberFormatException aEx){
			
		}
	}
	
	@Override
	protected JSONObject getModel() {
		JSONObject json = new JSONObject();
		json.put("provider_id", getUserId());
		json.put("request_id", travelRequestKey);
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
