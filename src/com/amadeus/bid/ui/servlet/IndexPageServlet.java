package com.amadeus.bid.ui.servlet;

import com.amadeus.bid.be.fwk.LocalizationUtil;
import com.amadeus.bid.ui.fwk.json.JSONObject;
import com.amadeus.bid.ui.generic.ApplicationServlet;

/**
 * servlet to display the index page
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
		this.fillHeaderLabels(json);
		
		// for body
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
