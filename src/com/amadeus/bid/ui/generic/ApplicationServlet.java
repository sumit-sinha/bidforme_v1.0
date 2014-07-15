package com.amadeus.bid.ui.generic;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amadeus.bid.be.fwk.LocalizationUtil;
import com.amadeus.bid.ui.constants.IApplicationConstant;
import com.amadeus.bid.ui.fwk.json.JSONObject;

/**
 * servlet class used by all the UI layer servlets as Parent
 * @author ssinha
 *
 */
public abstract class ApplicationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private String viewName;
	protected int userId;
	/**
	 * get the name of view
	 * @return
	 */
	public String getViewName() {
		return viewName;
	}
	
	public ApplicationServlet(String viewName) {
		this.viewName = viewName;
		this.userId = -1;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			userId = Integer.parseInt(req.getParameter(IApplicationConstant.CONST_PARAM_USER));
		} catch (NumberFormatException aEx){
			userId = -1;
		}
		
		JSONObject view = new JSONObject();
		view.put(IApplicationConstant.CONST_LABEL, this.getLabels());
		view.put(IApplicationConstant.CONST_MODEL, this.getModel());
		view.put(IApplicationConstant.CONST_ERROR, this.getErrors());
		
		JSONObject data = new JSONObject();
		data.put(this.getViewName(), view);
		
		String result = req.getHeader(IApplicationConstant.CONST_PARAM_RESULT);
		if (result == null || !result.equals(IApplicationConstant.CONST_PARAM_VALUE_JSON)) {
			req.setAttribute(IApplicationConstant.CONST_DATA_NAME, data);
			req.getRequestDispatcher(IApplicationConstant.CONST_JSP_PATH).include(req, resp);
		} else {
			String json = data.toString();
			String contentType = "application/json";
			String characterEncoding = "UTF-8";

		    byte[] jsonBytes = json.getBytes(characterEncoding);
		    resp.addHeader("Access-Control-Allow-Origin", "*");
		    resp.setContentType(contentType + ";charset=" + characterEncoding);

		    /* This to be commented after testing of JSON response is over in browser */
		    OutputStream os = resp.getOutputStream();
		    os.write(jsonBytes);
		    os.flush();
		    
		    // clear memory
		    os = null;
		    view = null;
		    data = null;
		    json = null;
		    jsonBytes = null;
		    contentType = null;
		    characterEncoding = null;
		    
		    // flush the buffer
		    resp.flushBuffer();
		}
		
		
		
	}
	
	protected void fillHeaderLabels(JSONObject ioJson)
	{
		ioJson.put("tx_bidforme_app_name", LocalizationUtil.getString("tx_bidforme_app_name"));
		ioJson.put("tx_bidforme_nav_home", LocalizationUtil.getString("tx_bidforme_nav_home"));
		ioJson.put("tx_bidforme_nav_what", LocalizationUtil.getString("tx_bidforme_nav_what"));
		ioJson.put("tx_bidforme_nav_contactus", LocalizationUtil.getString("tx_bidforme_nav_contactus"));
		ioJson.put("tx_bidforme_nav_signin", LocalizationUtil.getString("tx_bidforme_nav_signin"));
		ioJson.put("tx_bidforme_nav_register", LocalizationUtil.getString("tx_bidforme_nav_register"));
	}
	
	/**
	 * method to construct labels required for UI
	 * @return {@link JSONObject}
	 */
	protected abstract JSONObject getLabels();
	
	/**
	 * method to construct business data required for UI
	 * @return {@link JSONObject}
	 */
	protected abstract JSONObject getModel();
	
	/**
	 * method to construct error if any for UI
	 * @return {@link JSONObject}
	 */
	protected abstract JSONObject getErrors();
}
