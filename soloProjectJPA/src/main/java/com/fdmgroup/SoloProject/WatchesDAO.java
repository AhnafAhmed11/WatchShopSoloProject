package com.fdmgroup.SoloProject;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class WatchesDAO {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("soloProjectJPA");
    EntityManager entityManager = emf.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();


	public WatchesDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public WatchesDAO() {
	}

	public void addWatch(Watches watch) {
		entityTransaction.begin();
        entityManager.persist(watch);
        entityTransaction.commit();
	}

	public List<Watches> listAllWatches() {
		String jpql = "SELECT w FROM Watches w";
        TypedQuery<Watches> watchQuery = entityManager.createQuery(jpql, Watches.class);
        List<Watches> watch = watchQuery.getResultList();
        return watch;
	}

	public void removeWatch(int watchID) {
		Watches watch1 = entityManager.find(Watches.class, watchID);
        if(watch1 != null){
        	entityTransaction.begin();
        entityManager.remove(watch1);
        entityTransaction.commit();
        }
	}

	public void updateWatch(Watches newWatch) {
		Watches watch1 = entityManager.find(Watches.class, newWatch.getWatchID());
		
		if(watch1 != null){
			entityTransaction.begin();
			watch1.setWatchID(newWatch.getWatchID());
			watch1.setWatchName(newWatch.getWatchName());
			watch1.setDescription(newWatch.getDescription());
			watch1.setPrice(newWatch.getPrice());
			entityTransaction.commit();
		}
		
	}

	public Watches getWatch(int watchID) {
        Watches watch = entityManager.find(Watches.class, watchID);
        	return watch;
	}

}
