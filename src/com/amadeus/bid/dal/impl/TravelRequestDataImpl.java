package com.amadeus.bid.dal.impl;

import java.util.ArrayList;
import java.util.Map;

import com.amadeus.bid.dal.bean.TravelRequestBean;
import com.amadeus.bid.dal.util.DataStoreUtil;
import com.google.appengine.api.datastore.Entity;
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
		
		ArrayList<Key> aList = new ArrayList<Key>();
		aList.add(iKey);
		
		Map<Key, Entity> aMap = DataStoreUtil.getData(aList);
		return (new TravelRequestBean(aMap.get(iKey)));
	}
}
