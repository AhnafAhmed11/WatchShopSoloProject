package com.fdmgroup.SoloProject;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class OrderDAO {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("soloProjectJPA");
    EntityManager entityManager = emf.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();

	public OrderDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public OrderDAO() {
	}

	public List<Order> listAllOrder() {
		String jpql = "SELECT c FROM CustomerOrders c";
        TypedQuery<Order> orderQuery = entityManager.createQuery(jpql, Order.class);
        List<Order> order = orderQuery.getResultList();
        return order;
	}

	public void addOrder(Order order1) {
		entityTransaction.begin();
        entityManager.persist(order1);
        entityTransaction.commit();
	}

	public void removeOrder(int orderID) {
		Order order1 = entityManager.find(Order.class, orderID);
        if(order1 != null){
        	entityTransaction.begin();
        	entityManager.remove(order1);
        	entityTransaction.commit();
        }
	}

	public Order getOrder(int orderID) {
		Order order = entityManager.find(Order.class, orderID);
    	return order;
	}

	public void updateOrder(Order newOrder) {
		Order order1 = entityManager.find(Order.class, newOrder.getOrderID());
		
		if(order1 != null){
			entityTransaction.begin();
			entityManager.merge(newOrder);
			entityTransaction.commit();
		}
	}

	public List<Order> listCustomerOrder(String username) {
		String jpql = "SELECT c FROM CustomerOrders c WHERE c.customer.username =:username ";
		TypedQuery<Order> orderQuery = entityManager.createQuery(jpql, Order.class);
        orderQuery.setParameter("username", username);
		List<Order> order = orderQuery.getResultList();
        return order;
	}

}
