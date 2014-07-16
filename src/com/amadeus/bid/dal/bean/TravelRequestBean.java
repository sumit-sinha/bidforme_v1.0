package com.amadeus.bid.dal.bean;

import java.awt.List;

import com.amadeus.bid.dal.contract.IBeanContract;
import com.google.appengine.api.datastore.Entity;


/**
 * bean class to represent User data structure
 * @author pmoulinier
 *
 */
public class TravelRequestBean implements IBeanContract {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "TravelRequestTable";
	}

	@Override
	public Entity getEntity() {
		
		Entity entity = new Entity(this.getName());
		entity.setProperty("nb_of_adults", nbOfAdults);
		entity.setProperty("nb_of_children", nbOfChildren);
		entity.setProperty("destinations", destinations);
		entity.setProperty("criteria", criteria);
		entity.setProperty("budget", budget);
		entity.setProperty("free_text_comment", freeTextComment);
		
		return entity;
	}
	
	private int nbOfAdults;
	
	private int nbOfChildren;
	
	private Destination[] destinations;
	
	private String[] criteria;
	
	private int budget;
	
	private String freeTextComment;
	
}
