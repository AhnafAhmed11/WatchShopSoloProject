package com.fdmgroup.SoloProject;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CustomerDAO {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("soloProjectJPA");
    EntityManager entityManager = emf.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();

	public List<Customer> listAllCustomer() {
		String jpql = "SELECT c FROM Customer c";
        TypedQuery<Customer> customerQuery = entityManager.createQuery(jpql, Customer.class);
        List<Customer> customer = customerQuery.getResultList();
        return customer;
	}

	public void addCustomer(Customer customer1) {
		entityTransaction.begin();
        entityManager.persist(customer1);
        entityTransaction.commit();
	}

	public void removeCustomer(int custID) {
		Customer customer1 = entityManager.find(Customer.class, custID);
        if(customer1 != null){
        	entityTransaction.begin();
        	entityManager.remove(customer1);
        	entityTransaction.commit();
        }
	}

	public Customer getCustomer(int custID) {
		Customer customer = entityManager.find(Customer.class, custID);
    	return customer;
	}

	public void updateCustomer(Customer newCustomer) {
		Customer customer1 = entityManager.find(Customer.class, newCustomer.getCustID());
		
		if(customer1 != null){
			entityTransaction.begin();
			entityManager.merge(newCustomer);
			entityTransaction.commit();
		}
		
	}

	public Customer getCustomerByUsername(String username) {
		TypedQuery<Customer> queryUser = entityManager.createQuery("SELECT c FROM Customer c WHERE c.username=:username",
				Customer.class);
		queryUser.setParameter("username", username);
		List<Customer> listUsers = queryUser.getResultList();
		if (listUsers.size() == 1) {
			return listUsers.get(0);
		}
		return null;
	}

}
