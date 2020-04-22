package com.fdmgroup.validators;

import com.fdmgroup.SoloProject.Customer;
import com.fdmgroup.SoloProject.CustomerDAO;

public class Validation {

	public boolean validate(Customer customer) {
		String username = customer.getUsername();
		String password = customer.getPassword();
		
		CustomerDAO cd = new CustomerDAO();
		Customer customerUsernameInDB = cd.getCustomerByUsername(username);
		
		if(username.equals("") || password.equals("")){
			return false;
		}else if(customerUsernameInDB == null){
			return false;
		}else if (!(customerUsernameInDB.getUsername().equals(username)
				&& customerUsernameInDB.getPassword().equals(password))){
			return false;
		}else {
			return true;
		}
	}

}
