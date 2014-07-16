package com.amadeus.bid.ui.servlet;

import com.amadeus.bid.be.fwk.LocalizationUtil;
import com.amadeus.bid.ui.fwk.json.JSONObject;
import com.amadeus.bid.ui.generic.ApplicationServlet;

/**
 * creates data required to display the index page
 * @author ssinha
 *
 */
public class TravelerPageServlet extends ApplicationServlet {
	
	private static final long serialVersionUID = 1L;
	
	public TravelerPageServlet() {
		super("traveler");
	}

	@Override
	protected JSONObject getLabels() {
		
		JSONObject json = new JSONObject();
		
		// labels for header
		this.fillHeaderLabels(json);
		
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
		json.put("tx_bidforme_email_address", LocalizationUtil.getString("tx_bidforme_email_address"));
		json.put("tx_bidforme_password", LocalizationUtil.getString("tx_bidforme_password"));
		json.put("tx_bidforme_remember_me", LocalizationUtil.getString("tx_bidforme_remember_me"));
		json.put("tx_bidforme_signin_btn", LocalizationUtil.getString("tx_bidforme_signin_btn"));
		json.put("tx_bidforme_sign_in", LocalizationUtil.getString("tx_bidforme_sign_in"));
		json.put("tx_bidforme_confirm_password", LocalizationUtil.getString("tx_bidforme_confirm_password"));
		json.put("tx_bidforme_register_btn", LocalizationUtil.getString("tx_bidforme_register_btn"));
		json.put("tx_bidforme_login_with_google", LocalizationUtil.getString("tx_bidforme_login_with_google"));
		json.put("tx_bidforme_login_with_facebook", LocalizationUtil.getString("tx_bidforme_login_with_facebook"));
		json.put("tx_bidforme_register_with_google", LocalizationUtil.getString("tx_bidforme_register_with_google"));
		json.put("tx_bidforme_register_with_facebook", LocalizationUtil.getString("tx_bidforme_register_with_facebook"));
		return json;
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
