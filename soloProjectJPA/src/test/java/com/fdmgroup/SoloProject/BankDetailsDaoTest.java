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

public class BankDetailsDaoTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("soloProjectJPA");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.createQuery("DELETE FROM BankDetails b").executeUpdate();
        entityTransaction.commit();
	}

	@Before
	public void setUp() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("soloProjectJPA");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.createQuery("DELETE FROM BankDetails b").executeUpdate();
        entityTransaction.commit();
	}

	@Test
	public void test1_listAllBankDetails_ReturnsEmptyListWhenNoBankDetailsAdded() {
		BankDetailsDAO bdd = new BankDetailsDAO();
		
		List<BankDetails>bankDetailsDaoList = bdd.listAllBankDetails();
		assertEquals(0, bankDetailsDaoList.size());
		
	}
	
	@Test
    public void test2_listAllBankDetails_ReturnsListSizeOneWhenOneBankDetailAdded() {
		BankDetails bankAccount = new BankDetails(1,326364737,33400,606699);
		BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount);
        List<BankDetails> bankAccountsDaoList = bdd.listAllBankDetails();
        
        assertEquals(1, bankAccountsDaoList.size());
    }
    
    @Test
    public void test3_listAllBankDetails_ReturnsListContainingSameBankDetailsWhichWasAdded() {
    	BankDetails bankAccount = new BankDetails(1,326364737,33400,606699);
    	
    	BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount);
        List<BankDetails> bankAccountsDaoList = bdd.listAllBankDetails();
        
        assertEquals(bankAccount,bankAccountsDaoList.get(0));
    }
	
    @Test
    public void test4_addBankDetailsInBankDetails_ReturnsListSizeThatIsOneItemLarger(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	
    	BankDetailsDAO bdd = new BankDetailsDAO();
  	   bdd.addBankDetails(bankAccount1);
  	   bdd.addBankDetails(bankAccount2);
  	   
  	 List<BankDetails>bankDetailsDaoList = bdd.listAllBankDetails();
  	 assertEquals(2,bankDetailsDaoList.size());
    }
    
    @Test
    public void test5_test5_removeBankDetailsInBankDetails_RemovesOneBankAccount_ReturnsTheOtherAccountInBankDetails(){
    	//Makes sure that the right bank account was removed from the database
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	
    	BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		bdd.addBankDetails(bankAccount2);   
    	
		bdd.removeBankDetails(2);
		BankDetails retrievedBankAccount = bdd.getBankDetails(1);
    	   
    	assertEquals(bankAccount1,retrievedBankAccount);
    }
    
    @Test
    public void test6_removeBankDetailsInBankDetailsRemovesOneBankDetail_ReturnsListSizeThatIsOneItemSmaller(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	BankDetails bankAccount3 = new BankDetails(3,354738929,8000,602933);
    	
    	BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		bdd.addBankDetails(bankAccount2);
		bdd.addBankDetails(bankAccount3);
		
		bdd.removeBankDetails(3);
        List<BankDetails>BankDetailsDaoList = bdd.listAllBankDetails();
        
        assertEquals(2,BankDetailsDaoList.size());
    }
    
    @Test
    public void test7_removeBankDetailInBankDetailsThatDoesNotExistInTheDatabase_ReturnsSameListSizeAsBeforeRemove(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	BankDetails bankAccount3 = new BankDetails(3,354738929,8000,602933);
    	
    	BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		bdd.addBankDetails(bankAccount2);
		bdd.addBankDetails(bankAccount3);
		
		bdd.removeBankDetails(4);
        List<BankDetails>bankDetailsDaoList = bdd.listAllBankDetails();
        
        assertEquals(3,bankDetailsDaoList.size());
    }
    
    @Test
    public void test8_updateBankDetailsInBankDetails_UpdatesABankDetail_ReturnsUpdatedInfoFromBankDetailsTable(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	BankDetails bankAccount3 = new BankDetails(3,354738929,8000,602933);
    	
    	BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		bdd.addBankDetails(bankAccount2);
		bdd.addBankDetails(bankAccount3);
		
		BankDetails newAccount = new BankDetails(3,356748383,56000,605589); 
    	bdd.updateBankDetails(newAccount);
    	BankDetails updatedBankAccount = bdd.getBankDetails(3);
    	
    	assertEquals(newAccount, updatedBankAccount);
    }
    
    @Test
    public void test9_updateBankDetails_UpdatesNonExistentBankDetail_ReturnsTheSameListSizeAsBeforeUpdate(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	BankDetails bankAccount3 = new BankDetails(3,354738929,8000,602933);
    	
    	BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		bdd.addBankDetails(bankAccount2);
		bdd.addBankDetails(bankAccount3);
		
		BankDetails newAccount = new BankDetails(3,356748383,56000,605589); 
    	bdd.updateBankDetails(newAccount);
    	List<BankDetails>bankDetailsDaoList = bdd.listAllBankDetails();
    	
    	assertEquals(3,bankDetailsDaoList.size());
    }
    
    @Test
    public void test10_updateBankDetails_UpdatesNonExistentBankDetail_ReturnsSameListOfInformationAsBeforeUpdate(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);
  	   
    	List<BankDetails> bankAccountList = new ArrayList<BankDetails>();
 	    bankAccountList.add(bankAccount1);
 	   
 	   BankDetailsDAO bdd = new BankDetailsDAO();
 	   bdd.addBankDetails(bankAccount1);
 	   
 	  BankDetails newBankAccount = new BankDetails(6,356382930,4000,602577); 
 	   bdd.updateBankDetails(newBankAccount);
 	   List<BankDetails> bankDetailsDaoList = bdd.listAllBankDetails();
 	   
 	   assertEquals(bankAccountList,bankDetailsDaoList);
    }
    
    @Test
    public void test11_getBankDetails_FromEmptyBankDetailsTable_ReturnsNull(){
    	BankDetailsDAO bdd = new BankDetailsDAO();
    	
    	BankDetails bankAccount = bdd.getBankDetails(1);
    	
    	assertEquals(null,bankAccount);
    }
    
    @Test
    public void test12_getBankDetailsOfBankAccount_ReturnsDetailsOfTheBankAccountThatWasPutThroughTheGetBankDetails(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);
    	
    	BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		
		BankDetails retrievedBankDetails = bdd.getBankDetails(1);
	 	
		assertEquals(bankAccount1, retrievedBankDetails);
    }
    
    @Test
    public void test13_removeBankAccountFromBankDetails_ReturnsListSizeWhenMoreThanOneBankDetailIsRemoved(){
    	BankDetails bankAccount1 = new BankDetails(1,326364737,33400,606699);
    	BankDetails bankAccount2 = new BankDetails(2,356382930,4000,602577);
    	BankDetails bankAccount3 = new BankDetails(3,354738929,8000,602933);
    	
    	BankDetailsDAO bdd = new BankDetailsDAO();
		bdd.addBankDetails(bankAccount1);
		bdd.addBankDetails(bankAccount2);
		bdd.addBankDetails(bankAccount3);
		
		bdd.removeBankDetails(3);
		bdd.removeBankDetails(2);
        List<BankDetails>bankDetailsDaoList = bdd.listAllBankDetails();
        
        assertEquals(1,bankDetailsDaoList.size());
    }
    
}
