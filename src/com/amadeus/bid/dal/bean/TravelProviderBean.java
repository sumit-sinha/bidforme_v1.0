package com.amadeus.bid.dal.bean;

import com.amadeus.bid.dal.contract.IBeanContract;
import com.google.appengine.api.datastore.Entity;

/**
 * this structure is used to hold data of travel providers
 * @author ssinha
 *
 */
public class TravelProviderBean implements IBeanContract {

	private static final long serialVersionUID = 1L;

	private String companyName;
	
	private UserBean userInfo;
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public UserBean getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserBean userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String getName() {
		return "TravelProviderTable";
	}

	@Override
	public Entity getEntity() {
		
		// create a key with email address
		Entity entity = new Entity(this.getName(), this.getUserInfo().getEmail());
		entity.setProperty("company_name", this.getCompanyName());
		entity.setProperty("email", this.getUserInfo().getEmail());
		
		return entity;
	}
	
	/**
	 * creates a {@link TravelProviderBean} from {@link Entity}
	 * @param entity {@link Entity}
	 * @return {@link TravelProviderBean}
	 */
	public static TravelProviderBean getTravelProvider(Entity entity) {
		TravelProviderBean travelProvider = new TravelProviderBean();
		travelProvider.setCompanyName((String)entity.getProperty("company_name"));
		travelProvider.setUserInfo(UserBean.getUserBean(entity));
		
		return travelProvider;
	}

}
