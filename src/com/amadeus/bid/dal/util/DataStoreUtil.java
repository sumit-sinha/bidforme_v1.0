package com.amadeus.bid.dal.util;

import java.util.List;
import java.util.Map;

import com.amadeus.bid.dal.contract.IBeanContract;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * utility class used to persist data in storage
 * @author ssinha
 *
 */
public class DataStoreUtil {
	
	/**
	 * an instance of {@link DatastoreService} to persist
	 */
	private static DatastoreService dataStore;
	
	static {
		dataStore = DatastoreServiceFactory.getDatastoreService();
	}
	
	/**
	 * method to add an {@link Entity} to datastore
	 * @param contract
	 */
	public static void addEntity(IBeanContract contract) {
		dataStore.put(contract.getEntity());		
	}
	
	/**
	 * read the data from data store based on keys
	 * @param keys {@link List} of {@link Key}
	 * @return
	 */
	public static Map<Key, Entity> getData(List<Key> keys) {
		
		Map<Key, Entity> entities = dataStore.get(keys);
		
		return entities;
	}
	
	/**
	 * delete the data from data store
	 * @param keys {@link List} of {@link Key}
	 */
	public static void deleteData(List<Key> keys) {
		dataStore.delete(keys);
	}
}
