package com.amadeus.bid.dal.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amadeus.bid.dal.bean.TravelProviderBean;
import com.amadeus.bid.dal.contract.ITravelProviderContract;
import com.amadeus.bid.dal.contract.IUserDataContract;
import com.amadeus.bid.dal.util.DataStoreUtil;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

/**
 * implementation for {@link ITravelProviderContract}
 * @author ssinha
 *
 */
public class TravelProviderDataImpl implements ITravelProviderContract {

	@Override
	public void saveTravelProviderData(TravelProviderBean bean) {
		if (bean != null 
				&& bean.getUserInfo() != null
				&& bean.getUserInfo().getEmail() != null) {
			
			DataStoreUtil.addEntity(bean.getUserInfo());
			DataStoreUtil.addEntity(bean);
		}
	}

	@Override
	public List<TravelProviderBean> getProviderData() {
		Query query = new Query("TravelProviderTable");
		IUserDataContract userData = new UserDataImpl();
		List<TravelProviderBean> providers = new ArrayList<TravelProviderBean>();
		
		// read from storage
		Map<Key, Entity> entities = DataStoreUtil.getData(query);
		if (entities != null && entities.keySet().size() > 0) {
			for (Key key: entities.keySet()) {
				
				Entity entity = entities.get(key);
				Entity userEntity = userData.getUserData((String)entity.getProperty("email")).getEntity();
				for (String user_key: userEntity.getProperties().keySet()) {
					entity.setProperty(user_key, userEntity.getProperty(user_key));
				}
				
				providers.add(TravelProviderBean.getTravelProvider(entity));
			}
		}
		
		if (providers.size() > 0) {
			return providers;
		}
		
		return null;
	}

	@Override
	public TravelProviderBean getProviderData(String email) {
		
		IUserDataContract userData = new UserDataImpl();
		Filter providerFilter = new FilterPredicate("email", FilterOperator.EQUAL, email);
		Query query = new Query("TravelProviderTable").setFilter(providerFilter);
		
		// read from storage
		Map<Key, Entity> entities = DataStoreUtil.getData(query);
		if (entities != null && entities.keySet().size() > 0) {
			for (Key key: entities.keySet()) {
				
				Entity entity = entities.get(key);
				Entity userEntity = userData.getUserData((String)entity.getProperty("email")).getEntity();
				for (String user_key: userEntity.getProperties().keySet()) {
					entity.setProperty(user_key, userEntity.getProperty(user_key));
				}
				
				return TravelProviderBean.getTravelProvider(entity);
			}
		}
		
		return null;
	}
}