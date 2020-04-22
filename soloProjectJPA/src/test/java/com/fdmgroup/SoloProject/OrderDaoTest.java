package com.fdmgroup.SoloProject;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class OrderDaoTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("soloProjectJPA");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.createQuery("DELETE FROM CustomerOrders c").executeUpdate();
        entityManager.createQuery("DELETE FROM Customer c").executeUpdate();
        entityManager.createQuery("DELETE FROM BankDetails b").executeUpdate();
        entityManager.createQuery("DELETE FROM Watches w").executeUpdate();
        entityTransaction.commit();
	}

	@Before
	public void setUp() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("soloProjectJPA");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.createQuery("DELETE FROM CustomerOrders c").executeUpdate();
        entityManager.createQuery("DELETE FROM Customer c").executeUpdate();
        entityManager.createQuery("DELETE FROM BankDetails b").executeUpdate();
        entityManager.createQuery("DELETE FROM Watches w").executeUpdate();
        entityTransaction.commit();
	}

	@Test
	public void test1_listAllOrders_ReturnsEmptyListWhenNoOrderAdded() {
		
		OrderDAO od = new OrderDAO();
		
		List<Order> orderDaoList = od.listAllOrder();
		
		assertEquals(0, orderDaoList.size());
	}
	
	@Test
    public void test2_listAllOrders_ReturnsListSizeOneWhenOneOrderAdded() {
		
		BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
		Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
		
		Map<Watches, Integer> watchesMap = new HashMap<Watches, Integer>();
		watchesMap.put(watch1, 1);
		
		Order order1 = new Order(1,customer1,"09873563", new Date(), watchesMap);
		
		WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		
		BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		
		CustomerDAO cdd = new CustomerDAO();
		cdd.addCustomer(customer1);
		
		OrderDAO od = new OrderDAO();
		od.addOrder(order1);
		List<Order> ordersDaoList = od.listAllOrder();
		
		assertEquals(1,ordersDaoList.size());
    }
    
    @Test
    public void test3_listAllOrders_ReturnsListContainingSameOrderWhichWasAdded() {
    	
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
		Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
		
		Map<Watches, Integer> watchesMap = new HashMap<Watches, Integer>();
		watchesMap.put(watch1, 1);
		
		Order order1 = new Order(1,customer1,"09873563", new Date(), watchesMap);
		
		WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		
		BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		
		CustomerDAO cdd = new CustomerDAO();
		cdd.addCustomer(customer1);
		
		OrderDAO od = new OrderDAO();
		od.addOrder(order1);
		List<Order> ordersDaoList = od.listAllOrder();
		
		assertEquals(order1, ordersDaoList.get(0));
    }
	
    @Test
    public void test4_addOrderInOrder_ReturnsListSizeThatIsOneItemLarger(){
    	
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Customer customer2 = new Customer(2, "Bob", "Dylan", "BD", "Password2", "Luton", "07685746346", bankAccount2);
		
		Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
		Watches watch2 = new Watches(2,"Casio","Men's Casio G-Shock Alarm Chronograph", 72.00);
    	
		Map<Watches, Integer> watchesMap = new HashMap<Watches, Integer>();
		watchesMap.put(watch1, 1);
		watchesMap.put(watch2, 2);
		
		Order order1 = new Order(1,customer1,"09873563", new Date(), watchesMap);
		Order order2 = new Order(2,customer2,"05643563", new Date(), watchesMap);
		
		WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		wd.addWatch(watch2);
		
		BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		bdd.addBankDetails(bankAccount2);
		
		CustomerDAO cdd = new CustomerDAO();
		cdd.addCustomer(customer1);
		cdd.addCustomer(customer2);
		
		OrderDAO od = new OrderDAO();
		od.addOrder(order1);
		od.addOrder(order2);
		
		List<Order> ordersDaoList = od.listAllOrder();
		
		assertEquals(2,ordersDaoList.size());
    }
    
    @Test
    public void test5_removeOrderInOrder_RemovesOneOrder_ReturnsTheOtherOrderInOrder(){
    	
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Customer customer2 = new Customer(2, "Bob", "Dylan", "BD", "Password2", "Luton", "07685746346", bankAccount2);
		
		Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
		Watches watch2 = new Watches(2,"Casio","Men's Casio G-Shock Alarm Chronograph", 72.00);
    	
		Map<Watches, Integer> watchesMap = new HashMap<Watches, Integer>();
		watchesMap.put(watch1, 1);
		watchesMap.put(watch2, 2);
		
		Order order1 = new Order(1,customer1,"09873563", new Date(), watchesMap);
		Order order2 = new Order(2,customer2,"05643563", new Date(), watchesMap);
		
		WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		wd.addWatch(watch2);
		
		BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		bdd.addBankDetails(bankAccount2);
		
		CustomerDAO cdd = new CustomerDAO();
		cdd.addCustomer(customer1);
		cdd.addCustomer(customer2);
		
		OrderDAO od = new OrderDAO();
		od.addOrder(order1);
		od.addOrder(order2);
		
		od.removeOrder(2);
		Order retrievedOrder = od.getOrder(1);
		
		assertEquals(order1, retrievedOrder);
    }
    
    @Test
    public void test6_removeOrdersInOrdersRemovesOneOrder_ReturnsListSizeThatIsOneItemSmaller(){
    	
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	BankDetails bankAccount3 = new BankDetails(3,354738929,8000,602933);
    	
    	Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Customer customer2 = new Customer(2, "Bob", "Dylan", "BD", "Password2", "Luton", "07685746346", bankAccount2);
		Customer customer3 = new Customer(3, "John", "Malkovic", "JM", "Password3", "Cambridge", "07467453621", bankAccount3);
		
		Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
		Watches watch2 = new Watches(2,"Casio","Men's Casio G-Shock Alarm Chronograph", 72.00);
		Watches watch3 = new Watches(3, "Tommy Hilfiger", "Tommy Hilfiger Gavin Watch", 172.00);
    	
		Map<Watches, Integer> watchesMap = new HashMap<Watches, Integer>();
		watchesMap.put(watch1, 1);
		watchesMap.put(watch2, 2);
		watchesMap.put(watch3, 1);
		
		Order order1 = new Order(1,customer1,"09873563", new Date(), watchesMap);
		Order order2 = new Order(2,customer2,"05643563", new Date(), watchesMap);
		Order order3 = new Order(3,customer3,"05643563", new Date(), watchesMap);
		
		WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		wd.addWatch(watch2);
		wd.addWatch(watch3);
		
		BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		bdd.addBankDetails(bankAccount2);
		bdd.addBankDetails(bankAccount3);
		
		CustomerDAO cdd = new CustomerDAO();
		cdd.addCustomer(customer1);
		cdd.addCustomer(customer2);
		cdd.addCustomer(customer3);
		
		OrderDAO od = new OrderDAO();
		od.addOrder(order1);
		od.addOrder(order2);
		od.addOrder(order3);
		
		od.removeOrder(3);
		List<Order> orderDaoList = od.listAllOrder();
		
		assertEquals(2, orderDaoList.size());
    }
    
    @Test
    public void test7_removeCustomersInCustomersThatDoesNotExistInTheDatabase_ReturnsSameListSizeAsBeforeRemove(){
    	
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	BankDetails bankAccount3 = new BankDetails(3,354738929,8000,602933);
    	
    	Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Customer customer2 = new Customer(2, "Bob", "Dylan", "BD", "Password2", "Luton", "07685746346", bankAccount2);
		Customer customer3 = new Customer(3, "John", "Malkovic", "JM", "Password3", "Cambridge", "07467453621", bankAccount3);
		
		Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
		Watches watch2 = new Watches(2,"Casio","Men's Casio G-Shock Alarm Chronograph", 72.00);
		Watches watch3 = new Watches(3, "Tommy Hilfiger", "Tommy Hilfiger Gavin Watch", 172.00);
    	
		Map<Watches, Integer> watchesMap = new HashMap<Watches, Integer>();
		watchesMap.put(watch1, 1);
		watchesMap.put(watch2, 2);
		watchesMap.put(watch3, 1);
		
		Order order1 = new Order(1,customer1,"09873563", new Date(), watchesMap);
		Order order2 = new Order(2,customer2,"05643563", new Date(), watchesMap);
		Order order3 = new Order(3,customer3,"05643563", new Date(), watchesMap);
		
		WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		wd.addWatch(watch2);
		wd.addWatch(watch3);
		
		BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		bdd.addBankDetails(bankAccount2);
		bdd.addBankDetails(bankAccount3);
		
		CustomerDAO cdd = new CustomerDAO();
		cdd.addCustomer(customer1);
		cdd.addCustomer(customer2);
		cdd.addCustomer(customer3);
		
		OrderDAO od = new OrderDAO();
		od.addOrder(order1);
		od.addOrder(order2);
		od.addOrder(order3);
		
		od.removeOrder(4);
		List<Order> orderDaoList = od.listAllOrder();
		
		assertEquals(3, orderDaoList.size());
    }
    
    @Test
    public void test8_updateCustomersInCustomers_UpdatesACustomers_ReturnsUpdatedInfoFromCustomersTable(){
    	
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	BankDetails bankAccount3 = new BankDetails(3,354738929,8000,602933);
    	
    	Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Customer customer2 = new Customer(2, "Bob", "Dylan", "BD", "Password2", "Luton", "07685746346", bankAccount2);
		Customer customer3 = new Customer(3, "John", "Malkovic", "JM", "Password3", "Cambridge", "07467453621", bankAccount3);
		
		Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
		Watches watch2 = new Watches(2,"Casio","Men's Casio G-Shock Alarm Chronograph", 72.00);
		Watches watch3 = new Watches(3, "Tommy Hilfiger", "Tommy Hilfiger Gavin Watch", 172.00);
    	
		Map<Watches, Integer> watchesMap = new HashMap<Watches, Integer>();
		watchesMap.put(watch1, 1);
		watchesMap.put(watch2, 2);
		watchesMap.put(watch3, 1);
		
		Order order1 = new Order(1,customer1,"09873563", new Date(), watchesMap);
		Order order2 = new Order(2,customer2,"05643563", new Date(), watchesMap);
		Order order3 = new Order(3,customer3,"05643563", new Date(), watchesMap);
		
		WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		wd.addWatch(watch2);
		wd.addWatch(watch3);
		
		BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		bdd.addBankDetails(bankAccount2);
		bdd.addBankDetails(bankAccount3);
		
		CustomerDAO cdd = new CustomerDAO();
		cdd.addCustomer(customer1);
		cdd.addCustomer(customer2);
		cdd.addCustomer(customer3);
		
		OrderDAO od = new OrderDAO();
		od.addOrder(order1);
		od.addOrder(order2);
		od.addOrder(order3);
		
		Customer newCustomer = new Customer(4, "Elaine", "Nunes", "EN", "Password3", "London", "07467453621", bankAccount3);
		Order newOrder = new Order(3,newCustomer,"05547563", new Date(), watchesMap);
		od.updateOrder(newOrder);
		Order updatedOrder = od.getOrder(3);
		
		assertEquals(newOrder, updatedOrder);
    }
    
    @Test
    public void test9_updateCustomers_UpdatesNonExistentCustomers_ReturnsTheSameListSizeAsBeforeUpdate(){
    	
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	BankDetails bankAccount3 = new BankDetails(3,354738929,8000,602933);
    	
    	Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Customer customer2 = new Customer(2, "Bob", "Dylan", "BD", "Password2", "Luton", "07685746346", bankAccount2);
		Customer customer3 = new Customer(3, "John", "Malkovic", "JM", "Password3", "Cambridge", "07467453621", bankAccount3);
		
		Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
		Watches watch2 = new Watches(2,"Casio","Men's Casio G-Shock Alarm Chronograph", 72.00);
		Watches watch3 = new Watches(3, "Tommy Hilfiger", "Tommy Hilfiger Gavin Watch", 172.00);
    	
		Map<Watches, Integer> watchesMap = new HashMap<Watches, Integer>();
		watchesMap.put(watch1, 1);
		watchesMap.put(watch2, 2);
		watchesMap.put(watch3, 1);
		
		Order order1 = new Order(1,customer1,"09873563", new Date(), watchesMap);
		Order order2 = new Order(2,customer2,"05643563", new Date(), watchesMap);
		Order order3 = new Order(3,customer3,"05643563", new Date(), watchesMap);
		
		WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		wd.addWatch(watch2);
		wd.addWatch(watch3);
		
		BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		bdd.addBankDetails(bankAccount2);
		bdd.addBankDetails(bankAccount3);
		
		CustomerDAO cdd = new CustomerDAO();
		cdd.addCustomer(customer1);
		cdd.addCustomer(customer2);
		cdd.addCustomer(customer3);
		
		OrderDAO od = new OrderDAO();
		od.addOrder(order1);
		od.addOrder(order2);
		od.addOrder(order3);
		
		Customer newCustomer = new Customer(4, "Elaine", "Nunes", "EN", "Password3", "London", "07467453621", bankAccount3);
		Order newOrder = new Order(6,newCustomer,"05547563", new Date(), watchesMap);
		od.updateOrder(newOrder);
		
		List<Order> orderDaoList = od.listAllOrder();
		assertEquals(3, orderDaoList.size());
    }
    
    @Test
    public void test10_updateCustomers_UpdatesNonExistentCustomers_ReturnsSameListOfInformationAsBeforeUpdate(){
    	
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
		Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
		
		Map<Watches, Integer> watchesMap = new HashMap<Watches, Integer>();
		watchesMap.put(watch1, 1);
		
		Order order1 = new Order(1,customer1,"09873563", new Date(), watchesMap);
		
		List<Order>orders = new ArrayList<Order>();
		orders.add(order1);
		
		WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		
		BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		
		CustomerDAO cdd = new CustomerDAO();
		cdd.addCustomer(customer1);
		
		OrderDAO od = new OrderDAO();
		od.addOrder(order1);
		
		Customer newCustomer = new Customer(4, "Elaine", "Nunes", "EN", "Password3", "London", "07467453621", bankAccount1);
		Order newOrder = new Order(6,newCustomer,"05547563", new Date(), watchesMap);
		od.updateOrder(newOrder);
		
		List<Order> orderDaoList = od.listAllOrder();
		
		assertEquals(orders, orderDaoList);
    }
    
    @Test
    public void test11_getCustomers_FromEmptyCustomersTable_ReturnsNull(){
    	   OrderDAO od = new OrderDAO();
    	   
    	   Order order = od.getOrder(1);
    	   
    	   assertEquals(null, order);
    }
    
    @Test
    public void test12_getCustomersOfCustomers_ReturnsDetailsOfTheCustomersThatWasPutThroughTheGetCustomers(){
    	
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
		Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
		
		Map<Watches, Integer> watchesMap = new HashMap<Watches, Integer>();
		watchesMap.put(watch1, 1);
		
		Order order1 = new Order(1,customer1,"09873563", new Date(), watchesMap);
		
		WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		
		BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		
		CustomerDAO cdd = new CustomerDAO();
		cdd.addCustomer(customer1);
		
		OrderDAO od = new OrderDAO();
		od.addOrder(order1);
		
		Order retrievedOrder = od.getOrder(1);
		
		assertEquals(order1, retrievedOrder);
    }
    
    @Test
    public void test13_removeOrderFromOrder_ReturnsListSizeWhenMoreThanOneOrderIsRemoved(){
    	
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	BankDetails bankAccount3 = new BankDetails(3,354738929,8000,602933);
    	
    	Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Customer customer2 = new Customer(2, "Bob", "Dylan", "BD", "Password2", "Luton", "07685746346", bankAccount2);
		Customer customer3 = new Customer(3, "John", "Malkovic", "JM", "Password3", "Cambridge", "07467453621", bankAccount3);
		
		Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
		Watches watch2 = new Watches(2,"Casio","Men's Casio G-Shock Alarm Chronograph", 72.00);
		Watches watch3 = new Watches(3, "Tommy Hilfiger", "Tommy Hilfiger Gavin Watch", 172.00);
    	
		Map<Watches, Integer> watchesMap = new HashMap<Watches, Integer>();
		watchesMap.put(watch1, 1);
		watchesMap.put(watch2, 2);
		watchesMap.put(watch3, 1);
		
		Order order1 = new Order(1,customer1,"09873563", new Date(), watchesMap);
		Order order2 = new Order(2,customer2,"05643563", new Date(), watchesMap);
		Order order3 = new Order(3,customer3,"05643563", new Date(), watchesMap);
		
		WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		wd.addWatch(watch2);
		wd.addWatch(watch3);
		
		BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		bdd.addBankDetails(bankAccount2);
		bdd.addBankDetails(bankAccount3);
		
		CustomerDAO cdd = new CustomerDAO();
		cdd.addCustomer(customer1);
		cdd.addCustomer(customer2);
		cdd.addCustomer(customer3);
		
		OrderDAO od = new OrderDAO();
		od.addOrder(order1);
		od.addOrder(order2);
		od.addOrder(order3);
		
		od.removeOrder(3);
		od.removeOrder(2);
		List<Order> orderDaoList = od.listAllOrder();
		
		assertEquals(1, orderDaoList.size());
    }

}
