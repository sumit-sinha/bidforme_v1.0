package com.amadeus.bid.ui.servlet;

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
