package com.amadeus.bid.dal.contract;

import java.util.List;

import com.amadeus.bid.dal.bean.TravelProviderBean;

/**
 * contract to add/edit/delete TravelProvider information
 * @author ssinha
 *
 */
public interface ITravelProviderContract {
	
	/**
	 * method to add data to storage
	 * @param bean
	 */
	public void saveTravelProviderData(TravelProviderBean bean);
	
	/**
	 * method to fetch all the provider data from storage
	 * @return {@link List} of {@link TravelProviderBean}
	 */
	public List<TravelProviderBean> getProviderData();
	
	/**
	 * method to fetch provider data from storage based on email
	 * @param email {@link String}
	 * @return {@link TravelProviderBean}
	 */
	public TravelProviderBean getProviderData(String email);
}
