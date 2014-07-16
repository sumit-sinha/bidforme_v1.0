package com.amadeus.bid.dal.contract;

import java.util.List;

import com.amadeus.bid.dal.bean.UserBean;

/**
 * interface to define contract to get/set user data
 * @author ssinha
 *
 */
public interface IUserDataContract {
	
	/**
	 * method to add data to storage
	 * @param bean
	 */
	public void saveUserData(UserBean bean);
	
	/**
	 * method to fetch all the user data from storage
	 * @return {@link List} of {@link UserBean}
	 */
	public List<UserBean> getUserData();
	
	/**
	 * method to fetch user data from storage based on email
	 * @param email {@link String}
	 * @return {@link UserBean}
	 */
	public UserBean getUserData(String email);
	
}
