package com.fdmgroup.SoloProject;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class BankDetailsDAO {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("soloProjectJPA");
    EntityManager entityManager = emf.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    
	public List<BankDetails> listAllBankDetails() {
		String jpql = "SELECT b FROM BankDetails b";
        TypedQuery<BankDetails> bankDetailsQuery = entityManager.createQuery(jpql, BankDetails.class);
        List<BankDetails> bankDetail = bankDetailsQuery.getResultList();
        return bankDetail;
	}

	public void addBankDetails(BankDetails bankAccount) {
		entityTransaction.begin();
        entityManager.persist(bankAccount);
        entityTransaction.commit();
	}

	public void removeBankDetails(int cardID) {
		BankDetails bankAccount1 = entityManager.find(BankDetails.class, cardID);
        if(bankAccount1 != null){
        	entityTransaction.begin();
        	entityManager.remove(bankAccount1);
        	entityTransaction.commit();
        }
	}

	public BankDetails getBankDetails(int cardID) {
		BankDetails bankDetail = entityManager.find(BankDetails.class, cardID);
    	return bankDetail;
	}

	public void updateBankDetails(BankDetails newAccount) {
		BankDetails bankDetail1 = entityManager.find(BankDetails.class, newAccount.getCardID());
		
		if(bankDetail1 != null){
			entityTransaction.begin();
			entityManager.merge(newAccount);
			entityTransaction.commit();
		}
		
		
	}


}
