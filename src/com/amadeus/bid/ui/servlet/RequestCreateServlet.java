
package com.amadeus.bid.ui.servlet;

import java.util.Date;

import com.amadeus.bid.dal.bean.Destination;
import com.amadeus.bid.dal.bean.TravelRequestBean;
import com.amadeus.bid.dal.impl.TravelRequestDataImpl;

/**
 * creates data required to display the provider page
 * @author pmoulinier
 *
 */
public class RequestCreateServlet extends RequestPageServlet {
	
	private static final long serialVersionUID = 1L;
		
	public RequestCreateServlet() {
		super ();
	}

	@Override
	protected void doTask(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) 
	{
		TravelRequestBean aTravelRequest = new TravelRequestBean();
		
		int aNbOfAdults = 2;
		aTravelRequest.setNbOfAdults(aNbOfAdults);
		
		int aNbOfChildren = 1;
		aTravelRequest.setNbOfChildren(aNbOfChildren);
		
		String aOrigin = req.getParameter("origin");
		String aDestination = req.getParameter("destination");
		Date aStartDate = new Date(Long.parseLong(req.getParameter("startDate")));
		Date aEndDate = new Date(Long.parseLong(req.getParameter("endDate")));
		
		aTravelRequest.getDestinations().add(new Destination(aDestination, aStartDate));
		aTravelRequest.getDestinations().add(new Destination(aOrigin, aEndDate));
		
		
		String aCriteria = req.getParameter("criteria");
		
		if (aCriteria != null)
		{
			String[] aSplitCriteria = aCriteria.split(",");

			for (int i = 0; i < aSplitCriteria.length; i++)
			{
				aTravelRequest.getCriteria().add(aSplitCriteria[i]);
			}
		}
		int aBudget = Integer.parseInt(req.getParameter("budget"));
		aTravelRequest.setBudget(aBudget);
		
		String aTextComments = req.getParameter("comments");
		aTravelRequest.setFreeTextComment(aTextComments);		
		
		TravelRequestDataImpl aDataContract = new TravelRequestDataImpl();
		travelRequestKey = aDataContract.saveTravelRequestData(aTravelRequest);
	}
	
}
