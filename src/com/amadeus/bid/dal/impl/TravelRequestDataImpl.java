package com.amadeus.bid.dal.impl;

import com.amadeus.bid.dal.bean.TravelRequestBean;
import com.amadeus.bid.dal.util.DataStoreUtil;
import com.google.appengine.api.datastore.Key;

public class TravelRequestDataImpl {

	public Key saveTravelRequestData(TravelRequestBean bean) {
		if (bean != null)
		{
			return(DataStoreUtil.addEntity(bean));
		}
		else
		{
			return null;
		}
	}
	
	public TravelRequestBean getTravelRequestData(Key iKey) {
		return null;
	}
}
