package com.fdmgroup.SoloProject;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class CustomerDaoTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("soloProjectJPA");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.createQuery("DELETE FROM Customer c").executeUpdate();
        entityManager.createQuery("DELETE FROM BankDetails b").executeUpdate();
        entityTransaction.commit();
	}

	@Before
	public void setUp() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("soloProjectJPA");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.createQuery("DELETE FROM Customer c").executeUpdate();
        entityManager.createQuery("DELETE FROM BankDetails b").executeUpdate();
        entityTransaction.commit();
        
	}

	@Test
	public void test1_listAllCustomers_ReturnsEmptyListWhenNoCustomerAdded() {
		CustomerDAO wd = new CustomerDAO();
		
		List<Customer>customerDaoList = wd.listAllCustomer();
		assertEquals(0, customerDaoList.size());
	}
	
	@Test
    public void test2_listAllCustomers_ReturnsListSizeOneWhenOneCustomerAdded() {
		BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
		Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		
		BankDetailsDAO bankDetailsDAO = new BankDetailsDAO();
		bankDetailsDAO.addBankDetails(bankAccount1);
		
		CustomerDAO cd = new CustomerDAO();
		cd.addCustomer(customer1);
		List<Customer> customerDaoList = cd.listAllCustomer();
		assertEquals(1,customerDaoList.size());
		
    }
    
    @Test
    public void test3_listAllCustomers_ReturnsListContainingSameCustomerWhichWasAdded() {
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
		Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		
		BankDetailsDAO bankDetailsDAO = new BankDetailsDAO();
		bankDetailsDAO.addBankDetails(bankAccount1);
		
		CustomerDAO cd = new CustomerDAO();
		cd.addCustomer(customer1);
		List<Customer> customerDaoList = cd.listAllCustomer();
		
		assertEquals(customer1, customerDaoList.get(0));
    }
	
    @Test
    public void test4_addCustomerInCustomers_ReturnsListSizeThatIsOneItemLarger(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	
    	BankDetailsDAO bankDetailsDAO = new BankDetailsDAO();
		bankDetailsDAO.addBankDetails(bankAccount1);
		bankDetailsDAO.addBankDetails(bankAccount2);
		
    	Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Customer customer2 = new Customer(2, "Bob", "Dylan", "BD", "Password2", "Luton", "07685746346", bankAccount2);
		
		CustomerDAO cd = new CustomerDAO();
		cd.addCustomer(customer1);
		cd.addCustomer(customer2);
		
		List<Customer> customerDaoList = cd.listAllCustomer();
		assertEquals(2,customerDaoList.size());
    }
    
    @Test
    public void test5_removeCustomerInCustomer_RemovesOneCustomer_ReturnsTheOtherCustomerInCustomer(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Customer customer2 = new Customer(2, "Bob", "Dylan", "BD", "Password2", "Luton", "07685746346", bankAccount2);
		
		BankDetailsDAO bankDetailsDAO = new BankDetailsDAO();
		bankDetailsDAO.addBankDetails(bankAccount1);
		bankDetailsDAO.addBankDetails(bankAccount2);
		
		CustomerDAO cd = new CustomerDAO();
		cd.addCustomer(customer1);
		cd.addCustomer(customer2);
		
		cd.removeCustomer(2);
		Customer retrievedCustomer = cd.getCustomer(1);
		
		assertEquals(customer1,retrievedCustomer);
    }
    
    @Test
    public void test6_removeCustomersInCustomersRemovesOneCustomers_ReturnsListSizeThatIsOneItemSmaller(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	BankDetails bankAccount3 = new BankDetails(3,354738929,8000,602933);
    	
    	Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Customer customer2 = new Customer(2, "Bob", "Dylan", "BD", "Password2", "Luton", "07685746346", bankAccount2);
		Customer customer3 = new Customer(3, "John", "Malkovic", "JM", "Password3", "Cambridge", "07467453621", bankAccount3);
		
		BankDetailsDAO bankDetailsDAO = new BankDetailsDAO();
		bankDetailsDAO.addBankDetails(bankAccount1);
		bankDetailsDAO.addBankDetails(bankAccount2);
		bankDetailsDAO.addBankDetails(bankAccount3);
		
		CustomerDAO cd = new CustomerDAO();
		cd.addCustomer(customer1);
		cd.addCustomer(customer2);
		cd.addCustomer(customer3);
		
		cd.removeCustomer(3);
		List<Customer> customerDaoList = cd.listAllCustomer();
		assertEquals(2,customerDaoList.size());
    }
    
    @Test
    public void test7_removeCustomersInCustomersThatDoesNotExistInTheDatabase_ReturnsSameListSizeAsBeforeRemove(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	BankDetails bankAccount3 = new BankDetails(3,354738929,8000,602933);
    	
    	Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Customer customer2 = new Customer(2, "Bob", "Dylan", "BD", "Password2", "Luton", "07685746346", bankAccount2);
		Customer customer3 = new Customer(3, "John", "Malkovic", "JM", "Password3", "Cambridge", "07467453621", bankAccount3);
		
		BankDetailsDAO bankDetailsDAO = new BankDetailsDAO();
		bankDetailsDAO.addBankDetails(bankAccount1);
		bankDetailsDAO.addBankDetails(bankAccount2);
		bankDetailsDAO.addBankDetails(bankAccount3);
		
		CustomerDAO cd = new CustomerDAO();
		cd.addCustomer(customer1);
		cd.addCustomer(customer2);
		cd.addCustomer(customer3);
		
		cd.removeCustomer(4);
		List<Customer> customerDaoList = cd.listAllCustomer();
		assertEquals(3,customerDaoList.size());
    }
    
    @Test
    public void test8_updateCustomersInCustomers_UpdatesACustomers_ReturnsUpdatedInfoFromCustomersTable(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	BankDetails bankAccount3 = new BankDetails(3,354738929,8000,602933);
    	
    	Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Customer customer2 = new Customer(2, "Bob", "Dylan", "BD", "Password2", "Luton", "07685746346", bankAccount2);
		Customer customer3 = new Customer(3, "John", "Malkovic", "JM", "Password3", "Cambridge", "07467453621", bankAccount3);
		
		BankDetailsDAO bankDetailsDAO = new BankDetailsDAO();
		bankDetailsDAO.addBankDetails(bankAccount1);
		bankDetailsDAO.addBankDetails(bankAccount2);
		bankDetailsDAO.addBankDetails(bankAccount3);
		
		CustomerDAO cd = new CustomerDAO();
		cd.addCustomer(customer1);
		cd.addCustomer(customer2);
		cd.addCustomer(customer3);
		
		Customer newCustomer = new Customer(3, "Elaine", "Nunes", "EN", "Password3", "London", "07467453621", bankAccount3);
		cd.updateCustomer(newCustomer);
		Customer updatedCustomer = cd.getCustomer(3);
		
		assertEquals(newCustomer, updatedCustomer);
    }
    
    @Test
    public void test9_updateCustomers_UpdatesNonExistentCustomers_ReturnsTheSameListSizeAsBeforeUpdate(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	BankDetails bankAccount3 = new BankDetails(3,354738929,8000,602933);
    	
    	Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Customer customer2 = new Customer(2, "Bob", "Dylan", "BD", "Password2", "Luton", "07685746346", bankAccount2);
		Customer customer3 = new Customer(3, "John", "Malkovic", "JM", "Password3", "Cambridge", "07467453621", bankAccount3);
		
		BankDetailsDAO bankDetailsDAO = new BankDetailsDAO();
		bankDetailsDAO.addBankDetails(bankAccount1);
		bankDetailsDAO.addBankDetails(bankAccount2);
		bankDetailsDAO.addBankDetails(bankAccount3);
		
		CustomerDAO cd = new CustomerDAO();
		cd.addCustomer(customer1);
		cd.addCustomer(customer2);
		cd.addCustomer(customer3);
		
		Customer newCustomer = new Customer(6, "Elaine", "Nunes", "EN", "Password3", "London", "07467453621", bankAccount3);
		cd.updateCustomer(newCustomer);
		List<Customer> customerDaoList = cd.listAllCustomer();
		
		assertEquals(3,customerDaoList.size());
		
    }
    
    @Test
    public void test10_updateCustomers_UpdatesNonExistentCustomers_ReturnsSameListOfInformationAsBeforeUpdate(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
		Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		
		List<Customer> customerList = new ArrayList<Customer>();
 	    customerList.add(customer1);
		
 	   BankDetailsDAO bankDetailsDAO = new BankDetailsDAO();
		bankDetailsDAO.addBankDetails(bankAccount1);
 	    
		CustomerDAO cd = new CustomerDAO();
		cd.addCustomer(customer1);
		
		Customer newCustomer = new Customer(6, "Elaine", "Nunes", "EN", "Password3", "London", "07467453621", bankAccount1);
		
		cd.updateCustomer(newCustomer);
		List<Customer> customerDaoList = cd.listAllCustomer();
		
		assertEquals(customerList, customerDaoList);
    }
    
    @Test
    public void test11_getCustomers_FromEmptyCustomersTable_ReturnsNull(){
    	CustomerDAO cd = new CustomerDAO();
    	
    	Customer customer = cd.getCustomer(1);
    	
    	assertEquals(null, customer);
    }
    
    @Test
    public void test12_getCustomersOfCustomers_ReturnsDetailsOfTheCustomersThatWasPutThroughTheGetCustomers(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
		Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		
		BankDetailsDAO bankDetailsDAO = new BankDetailsDAO();
		bankDetailsDAO.addBankDetails(bankAccount1);
		
		CustomerDAO cd = new CustomerDAO();
		cd.addCustomer(customer1);
		
		Customer retrievedCustomer = cd.getCustomer(1);
		
		assertEquals(customer1, retrievedCustomer);
    }
    
    @Test
    public void test13_removeCustomerFromCustomer_ReturnsListSizeWhenMoreThanOneCustomerIsRemoved(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);   
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	BankDetails bankAccount3 = new BankDetails(3,354738929,8000,602933);
    	
    	Customer customer1 = new Customer(1,"Jason", "Wellington", "JW", "password1", "London", "07654839843", bankAccount1);
		Customer customer2 = new Customer(2, "Bob", "Dylan", "BD", "Password2", "Luton", "07685746346", bankAccount2);
		Customer customer3 = new Customer(3, "John", "Malkovic", "JM", "Password3", "Cambridge", "07467453621", bankAccount3);
		
		BankDetailsDAO bankDetailsDAO = new BankDetailsDAO();
		bankDetailsDAO.addBankDetails(bankAccount1);
		bankDetailsDAO.addBankDetails(bankAccount2);
		bankDetailsDAO.addBankDetails(bankAccount3);
		
		CustomerDAO cd = new CustomerDAO();
		cd.addCustomer(customer1);
		cd.addCustomer(customer2);
		cd.addCustomer(customer3);
		
		cd.removeCustomer(3);
		cd.removeCustomer(2);
		
		List<Customer> customerDaoList = cd.listAllCustomer();
		assertEquals(1,customerDaoList.size());
    }

}
