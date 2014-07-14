package com.amadeus.bid.dal.contract;

import com.google.appengine.api.datastore.Entity;

/**
 * an interface class created to standardize persistence in storage
 * @author ssinha
 *
 */
public interface IBeanContract {
	
	/**
	 * return name of table
	 * @return
	 */
	public String getName();
	
	/**
	 * return an {@link Entity} object
	 * @return
	 */
	public Entity getEntity();
}
