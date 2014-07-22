package com.amadeus.bid.dal.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amadeus.bid.dal.bean.UserBean;
import com.amadeus.bid.dal.contract.IUserDataContract;
import com.amadeus.bid.dal.util.DataStoreUtil;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

/**
 * class to implement methods defined in {@link IUserDataContract}
 * @author ssinha
 *
 */
public class UserDataImpl implements IUserDataContract {

	@Override
	public void saveUserData(UserBean bean) {
		if (bean != null && bean.getEmail() != null) {
			DataStoreUtil.addEntity(bean);
		}
	}

	@Override
	public List<UserBean> getUserData() {
		
		Query query = new Query("UserTable");
		List<UserBean> users = new ArrayList<UserBean>();
		
		// read from storage
		Map<Key, Entity> entities = DataStoreUtil.getData(query);
		if (entities != null && entities.keySet().size() > 0) {
			for (Key key: entities.keySet()) {
				users.add(UserBean.getUserBean(entities.get(key)));
			}
		}
		
		if (users.size() > 0) {
			return users;
		}
		
		return null;
	}

	@Override
	public UserBean getUserData(String email) {
		
		Filter userFilter = new FilterPredicate("email", FilterOperator.EQUAL, email);
		Query query = new Query("UserTable").setFilter(userFilter);
		
		// read from storage
		Map<Key, Entity> entities = DataStoreUtil.getData(query);
		if (entities != null && entities.keySet().size() > 0) {
			for (Key key: entities.keySet()) {
				return UserBean.getUserBean(entities.get(key));
			}
		}
		
		return null;
	}

}
