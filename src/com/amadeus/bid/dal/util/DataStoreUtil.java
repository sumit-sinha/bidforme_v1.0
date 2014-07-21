package com.amadeus.bid.dal.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.amadeus.bid.dal.contract.IBeanContract;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

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
	
	/**
	 * an instance of {@link Logger} to track errors
	 */
	private static Logger logger;
	
	static {
		logger = Logger.getLogger(DataStoreUtil.class.getName());
		dataStore = DatastoreServiceFactory.getDatastoreService();
	}
	
	/**
	 * method to add an {@link Entity} to datastore
	 * @param contract
	 */
	public static Key addEntity(IBeanContract contract) {
		try {
			Entity aEntity = contract.getEntity();
			dataStore.put(aEntity);
			return (aEntity.getKey());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Unable to add entity in database", e);
		}
		
		return null;
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
	 * method to read data from storage based on {@link Query}
	 * @param query {@link Query}
	 * @return {@link Map} of {@link Entity}
	 */
	public static Map<Key, Entity> getData(Query query) {
		PreparedQuery pq = dataStore.prepare(query);
		Map<Key, Entity> entities = new HashMap<Key, Entity>();
		for (Entity result : pq.asIterable()) {		
			entities.put(result.getKey(), result);
		}
		
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
