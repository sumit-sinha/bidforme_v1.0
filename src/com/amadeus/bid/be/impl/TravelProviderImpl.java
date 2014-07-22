package com.amadeus.bid.be.impl;

import java.util.ArrayList;
import java.util.List;

import com.amadeus.bid.be.contract.ITravelProvider;
import com.amadeus.bid.be.fwk.LocalizationUtil;
import com.amadeus.bid.be.util.RegexUtil;
import com.amadeus.bid.dal.bean.MessageBean;
import com.amadeus.bid.dal.bean.TravelProviderBean;
import com.amadeus.bid.dal.contract.ITravelProviderContract;
import com.amadeus.bid.dal.impl.TravelProviderDataImpl;

/**
 * implementation for {@link ITravelProvider}
 * @author ssinha
 *
 */
public class TravelProviderImpl implements ITravelProvider {
	
	private ITravelProviderContract travelProvider;
	
	public TravelProviderImpl() {
		travelProvider = new TravelProviderDataImpl();
	}
	
	@Override
	public List<MessageBean> addProvider(TravelProviderBean provider) {
		
		// to store list of messages
		List<MessageBean> messages = new ArrayList<MessageBean>();
		
		if (provider == null || provider.getUserInfo() == null) {
			MessageBean message = new MessageBean();
			message.setMessage(LocalizationUtil.getString("tx_bidforme_provider_no_data"));
			message.setNumber("2001");
			message.setType("E");
			
			messages.add(message);
			
			return messages;
		}
		
		if (provider.getCompanyName() == null || provider.getCompanyName().trim().equals("")) {
			MessageBean message = new MessageBean();
			message.setMessage(LocalizationUtil.getString("tx_bidforme_provider_no_company"));
			message.setNumber("2002");
			message.setType("E");
			
			messages.add(message);
		}
		
		if (provider.getUserInfo().getEmail() == null 
				|| provider.getUserInfo().getEmail().trim().equals("") 
				|| !RegexUtil.isValidEmail(provider.getUserInfo().getEmail())) {
			MessageBean message = new MessageBean();
			message.setMessage(LocalizationUtil.getString("tx_bidforme_registration_email_error"));
			message.setNumber("2002");
			message.setType("E");
			
			messages.add(message);
		} else {
			if (this.getProvider(provider.getUserInfo().getEmail()) != null) {
				MessageBean message = new MessageBean();
				message.setType("E");
				message.setNumber("2003");
				message.setMessage(LocalizationUtil.getString("tx_bidforme_registration_existing"));
				
				messages.add(message);
			}
		}
		
		if (messages.size() == 0) {
			travelProvider.saveTravelProviderData(provider);
			return null;
		}
		
		return messages;
	}

	@Override
	public List<TravelProviderBean> getProvider() {
		return travelProvider.getProviderData();
	}

	@Override
	public TravelProviderBean getProvider(String email) {
		
		if (email == null || email.trim().equals("") 
				|| !RegexUtil.isValidEmail(email)) {
			return null;
		}
		
		return travelProvider.getProviderData(email);
	}

}
