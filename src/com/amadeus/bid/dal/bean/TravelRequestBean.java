package com.amadeus.bid.dal.bean;

import java.awt.List;
import java.util.ArrayList;

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
	
	private ArrayList<Destination> destinations;
	
	private ArrayList<String> criteria;
	
	private int budget;
	
	private String freeTextComment;

	public int getNbOfAdults() {
		return nbOfAdults;
	}

	public int getNbOfChildren() {
		return nbOfChildren;
	}

	public ArrayList<Destination> getDestinations() {
		return destinations;
	}

	public ArrayList<String> getCriteria() {
		return criteria;
	}

	public int getBudget() {
		return budget;
	}

	public String getFreeTextComment() {
		return freeTextComment;
	}

	public void setNbOfAdults(int nbOfAdults) {
		this.nbOfAdults = nbOfAdults;
	}

	public void setNbOfChildren(int nbOfChildren) {
		this.nbOfChildren = nbOfChildren;
	}

	public void setDestinations(ArrayList<Destination> destinations) {
		this.destinations = destinations;
	}

	public void setCriteria(ArrayList<String> criteria) {
		this.criteria = criteria;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public void setFreeTextComment(String freeTextComment) {
		this.freeTextComment = freeTextComment;
	}
}
