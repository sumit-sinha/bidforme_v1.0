package com.amadeus.bid.dal.impl;

import java.util.List;

import com.amadeus.bid.dal.bean.UserBean;
import com.amadeus.bid.dal.contract.IUserDataContract;
import com.amadeus.bid.dal.util.DataStoreUtil;

/**
 * class to implement methods defined in {@link IUserDataContract}
 * @author ssinha
 *
 */
public class UserDataImpl implements IUserDataContract {

	@Override
	public void saveUserData(UserBean bean) {
		if (bean != null && bean.getEmail() != null) {
			DataStoreUtil.addEntity(bean);
		}
	}

	@Override
	public List<UserBean> getUserData() {
		// TODO Auto-generated method stub
		return null;
	}

}
