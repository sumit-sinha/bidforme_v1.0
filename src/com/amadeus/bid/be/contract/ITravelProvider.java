package com.amadeus.bid.be.contract;

import java.util.List;

import com.amadeus.bid.dal.bean.MessageBean;
import com.amadeus.bid.dal.bean.TravelProviderBean;

/**
 * interface to define the methods which are required to be<br/>
 * implemented by Business layer for accessing provider data
 * @author ssinha
 *
 */
public interface ITravelProvider {
	
	/**
	 * method to add providers to data store
	 * @param provider {@link TravelProviderBean}
	 * @return {@link List} of {@link MessageBean}
	 */
	public List<MessageBean> addProvider(TravelProviderBean provider);
	
	/**
	 * method to fetch {@link List} of all {@link TravelProviderBean}
	 * @return {@link List} of {@link TravelProviderBean}
	 */
	public List<TravelProviderBean> getProvider();
	
	/**
	 * method to fetch {@link TravelProviderBean} based on email
	 * @param email {@link String}
	 * @return {@link TravelProviderBean}
	 */
	public TravelProviderBean getProvider(String email);
}
