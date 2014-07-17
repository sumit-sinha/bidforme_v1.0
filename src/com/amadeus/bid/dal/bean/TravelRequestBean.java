package com.amadeus.bid.dal.bean;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import org.apache.jsp.ah.entityDetailsBody_jsp;

import com.amadeus.bid.dal.contract.IBeanContract;
import com.google.appengine.api.datastore.Entity;


/**
 * bean class to represent User data structure
 * @author pmoulinier
 *
 */
public class TravelRequestBean implements IBeanContract {

	private static final long serialVersionUID = 1L;

	public TravelRequestBean() {
		this.nbOfAdults = 0;
		this.nbOfChildren = 0;
		this.destinations = new ArrayList<Destination>();
		this.criteria = new ArrayList<String>();
		this.budget = 0;
		this.freeTextComment = null;
	}
	
	public TravelRequestBean(Entity iEntity) {
		this.nbOfAdults = ((Long)iEntity.getProperty("nb_of_adults")).intValue();
		this.nbOfChildren = ((Long)iEntity.getProperty("nb_of_children")).intValue();
		
		this.destinations = new ArrayList<Destination>();
		String aSerializedDestinations = (String)iEntity.getProperty("destinations");
		String[] aSplitDestinations = aSerializedDestinations.split("/");
		for (String aDestinationPair : aSplitDestinations)
		{
			String[] aSplitDestinationPair = aDestinationPair.split("\\+");	
			Date aDate = new Date(Long.parseLong(aSplitDestinationPair[1]));
			this.destinations.add(new Destination(aSplitDestinationPair[0], aDate));
		}
		
		this.criteria = new ArrayList<String>();
		String aSerializedCriteria = (String)iEntity.getProperty("criteria");
		String[] aSplitCriteria = aSerializedCriteria.split("/");
		for (String aCriterion : aSplitCriteria)
		{
			this.criteria.add(aCriterion);
		}
		
		this.budget = ((Long)iEntity.getProperty("budget")).intValue();
		
		this.freeTextComment = (String)iEntity.getProperty("free_text_comment");
	}
	
	@Override
	public String getName() {
		return "TravelRequestTable";
	}

	@Override
	public Entity getEntity() {
		
		Entity entity = new Entity(this.getName());
		entity.setProperty("nb_of_adults", nbOfAdults);
		entity.setProperty("nb_of_children", nbOfChildren);
		
		String aSerializedDestinations = new String();
		for (Destination aDestination : destinations)
		{
			aSerializedDestinations += aDestination.place + "+" + aDestination.arrivalDate.getTime() + "/";
		}
		entity.setProperty("destinations", aSerializedDestinations);
		
		String aSerializedCriteria = new String();
		for (String aCriterion : criteria)
		{
			aSerializedCriteria += aCriterion + "/";
		}
		entity.setProperty("criteria", aSerializedCriteria);

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
