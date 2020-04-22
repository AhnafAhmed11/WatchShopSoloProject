package com.fdmgroup.validators;

import com.fdmgroup.SoloProject.Customer;
import com.fdmgroup.SoloProject.CustomerDAO;

public class RegistryValidation {

	
	public boolean validate(Customer customer) {
		String firstname = customer.getFirstname();
		String lastname = customer.getLastname();
		String address = customer.getAddress();
		String phone = customer.getPhone();
		String username = customer.getUsername();
		String password = customer.getPassword();
		
		CustomerDAO cd = new CustomerDAO();
		Customer customerUsernameInDB = cd.getCustomerByUsername(username);
		
		if(firstname.equals("") || lastname.equals("") || address.equals("") || 
				phone.equals("") || username.equals("") || password.equals("")){
			
			return false;
			
		}else if(customerUsernameInDB != null){
			
			return false;
			
		}else if(phone.length() >11){
			return false;
			
		}else if(phone.contains("[a-zA-Z]+") == true){
			return false;
			
		}else {
			return true;
		}
		
	}
}
