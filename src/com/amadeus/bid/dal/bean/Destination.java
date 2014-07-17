package com.amadeus.bid.dal.bean;

import java.util.Date;

public class Destination {
	
	public Destination(String iPlace, Date iDate) {
		place = iPlace;
		arrivalDate = iDate;
	}
	
	public String place;
	public Date arrivalDate;
}
