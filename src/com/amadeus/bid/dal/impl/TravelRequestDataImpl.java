package com.amadeus.bid.dal.impl;

import com.amadeus.bid.dal.bean.TravelRequestBean;
import com.amadeus.bid.dal.util.DataStoreUtil;

public class TravelRequestDataImpl {

	public String saveTravelRequestData(TravelRequestBean bean) {
		if (bean != null)
		{
			return(DataStoreUtil.addEntity(bean));
		}
		else
		{
			return null;
		}
	}
	
	public TravelRequestBean getTravelRequestData(String iKey) {
		return null;
	}
}
