package com.amadeus.bid.ui.servlet;

import com.amadeus.bid.be.fwk.LocalizationUtil;
import com.amadeus.bid.ui.fwk.json.JSONArray;
import com.amadeus.bid.ui.fwk.json.JSONObject;
import com.amadeus.bid.ui.generic.ApplicationServlet;

/**
 * creates data required to display the index page
 * @author ssinha
 *
 */
public class IndexPageServlet extends ApplicationServlet {
	
	private static final long serialVersionUID = 1L;
	
	public IndexPageServlet() {
		super("index");
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
		json.put("tx_bidforme_plan_holiday", LocalizationUtil.getString("tx_bidforme_plan_holiday"));
		json.put("tx_bidforme_build_trip", LocalizationUtil.getString("tx_bidforme_build_trip"));
		json.put("tx_bidforme_select_dest_dates", LocalizationUtil.getString("tx_bidforme_select_dest_dates"));
		json.put("tx_bidforme_preferred_transport", LocalizationUtil.getString("tx_bidforme_preferred_transport"));
		json.put("tx_bidforme_budget_range", LocalizationUtil.getString("tx_bidforme_budget_range"));
		json.put("tx_bidforme_any_comments", LocalizationUtil.getString("tx_bidforme_any_comments"));
		json.put("tx_bidforme_any_comments_placeholder", LocalizationUtil.getString("tx_bidforme_any_comments_placeholder"));
		json.put("tx_bidforme_submit_request", LocalizationUtil.getString("tx_bidforme_submit_request"));
		json.put("tx_bidforme_origin_city", LocalizationUtil.getString("tx_bidforme_origin_city"));
		json.put("tx_bidforme_dest_city", LocalizationUtil.getString("tx_bidforme_dest_city"));
		json.put("tx_bidforme_strt_date", LocalizationUtil.getString("tx_bidforme_strt_date"));
		json.put("tx_bidforme_end_date", LocalizationUtil.getString("tx_bidforme_end_date"));
		json.put("tx_bidforme_number_days", LocalizationUtil.getString("tx_bidforme_number_days"));
		json.put("tx_bidforme_insert_criteria", LocalizationUtil.getString("tx_bidforme_insert_criteria"));
		json.put("tx_bidforme_personal_criteria", LocalizationUtil.getString("tx_bidforme_personal_criteria"));
		
		return json;
	}

	@Override
	protected JSONObject getModel() {
		
		JSONObject json = new JSONObject();
		JSONArray countries = new JSONArray();
		
		JSONObject country1 = new JSONObject();
		country1.put("name", "India");
		country1.put("capital", "New Delhi");
		countries.put(country1);
		
		JSONObject country2 = new JSONObject();
		country2.put("name", "France");
		country2.put("capital", "Paris");
		countries.put(country2);
		
		JSONObject country3 = new JSONObject();
		country3.put("name", "Germany");
		country3.put("capital", "Berlin");
		countries.put(country3);
		
		JSONObject country4 = new JSONObject();
		country4.put("name", "Spain");
		country4.put("capital", "Madrid");
		countries.put(country4);
		
		return json.put("countries", countries);
	}

	@Override
	protected JSONObject getErrors() {
		return null;
	}

}
