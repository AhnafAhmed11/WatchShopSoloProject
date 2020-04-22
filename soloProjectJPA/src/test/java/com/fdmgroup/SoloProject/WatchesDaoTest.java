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

public class WatchesDaoTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("soloProjectJPA");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.createQuery("DELETE FROM Watches w").executeUpdate();
        entityTransaction.commit();
	}

	@Before
	public void setUp() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("soloProjectJPA");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.createQuery("DELETE FROM Watches w").executeUpdate();
        entityTransaction.commit();
	}

	@Test
	public void test1_listAllWatches_ReturnsEmptyListWhenNoWatchesAdded() {
		
		WatchesDAO wd = new WatchesDAO();
		
		List<Watches>watchesDaoList = wd.listAllWatches();
		assertEquals(0, watchesDaoList.size());
	}
	
	@Test
    public void test2_listAllWatches_ReturnsListSizeOne_WhenOneWatchAdded() {
		Watches watch = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
		WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch);
        List<Watches>watches = wd.listAllWatches();
        
        assertEquals(1,watches.size());
    }
    
    @Test
    public void test3_listAllWatches_ReturnsListContainingSameWatchWhichWasAdded() {
       
    	Watches watch = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
 	   
 	   WatchesDAO wd = new WatchesDAO();
 	   wd.addWatch(watch);
 	   List<Watches> watchesDaoList = wd.listAllWatches();
 	   
 	   assertEquals(watch,watchesDaoList.get(0));
    }
	
    @Test
    public void test4_addMultipleWatchesInWatches_ReturnsListSize(){
    	Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
    	Watches watch2 = new Watches(2,"Casio","Men's Casio G-Shock Alarm Chronograph", 72.00);
    	
    	WatchesDAO wd = new WatchesDAO();
  	   wd.addWatch(watch1);
  	   wd.addWatch(watch2);
  	   
  	 List<Watches>watches = wd.listAllWatches();
  	 assertEquals(2,watches.size());
    }
    
    @Test
    public void test5_removeWatchInWatches_RemovesOneWatch_ReturnsTheOtherWatchInWatches(){
    	   //Makes sure that the right watch was removed from the database
    	Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
    	Watches watch2 = new Watches(2,"Casio","Men's Casio G-Shock Alarm Chronograph", 72.00);
    	
    	WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		wd.addWatch(watch2);   
    	
		wd.removeWatch(2);
		Watches retrievedWatch = wd.getWatch(1);
    	   
    	assertEquals(watch1,retrievedWatch);
    }
    
    @Test
    public void test6_removeWatchInWatchesRemovesOneWatch_ReturnsListSizeThatIsOneItemSmaller(){
    	Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
    	Watches watch2 = new Watches(2,"Casio","Men's Casio G-Shock Alarm Chronograph", 72.00);
    	Watches watch3 = new Watches(3, "Tommy Hilfiger", "Tommy Hilfiger Gavin Watch", 172.00);
    	
    	WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		wd.addWatch(watch2);
		wd.addWatch(watch3);
		
		wd.removeWatch(3);
        List<Watches>watchesDaoList = wd.listAllWatches();
        
        assertEquals(2,watchesDaoList.size());
    }
    
    @Test
    public void test7_removeWatchInWatches_ThatDoesNotExistInTheDatabase_ReturnsSameListSizeAsBeforeRemove(){
    	
    	Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
    	Watches watch2 = new Watches(2,"Casio","Men's Casio G-Shock Alarm Chronograph", 72.00);
    	Watches watch3 = new Watches(3, "Tommy Hilfiger", "Tommy Hilfiger Gavin Watch", 172.00);
    	
    	WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		wd.addWatch(watch2);
		wd.addWatch(watch3);
		
		wd.removeWatch(4);
        List<Watches>watchesDaoList = wd.listAllWatches();
        
        assertEquals(3,watchesDaoList.size());
    }
    
    @Test
    public void test8_updateWatchInWatches_UpdatesAWatch_ReturnsUpdatedInfoFromWatchesTable(){
    	Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
    	Watches watch2 = new Watches(2,"Casio","Men's Casio G-Shock Alarm Chronograph", 72.00);
    	Watches watch3 = new Watches(3, "Tommy Hilfiger", "Tommy Hilfiger Gavin Watch", 172.00);
    	
    	WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		wd.addWatch(watch2);
		wd.addWatch(watch3);
		
		Watches newWatch = new Watches(3, "Citizen", "Men's Citizen Pro Master Divers Watch", 182.00); 
    	wd.updateWatch(newWatch);
    	Watches updatedWatch = wd.getWatch(3);
    	
    	assertEquals(newWatch, updatedWatch);
    }
    
    @Test
    public void test9_updateWatchInWatches_UpdatesNonExistentWatch_ReturnsTheSameListSizeAsBeforeUpdate(){
    	Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
    	Watches watch2 = new Watches(2,"Casio","Men's Casio G-Shock Alarm Chronograph", 72.00);
    	Watches watch3 = new Watches(3, "Tommy Hilfiger", "Tommy Hilfiger Gavin Watch", 172.00);
    	
    	WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		wd.addWatch(watch2);
		wd.addWatch(watch3);
		
		Watches newWatch = new Watches(6, "Citizen", "Men's Citizen Pro Master Divers Watch", 182.00); 
    	wd.updateWatch(newWatch);
    	List<Watches>watchesDaoList = wd.listAllWatches();
    	
    	assertEquals(3,watchesDaoList.size());
    }
    
    @Test
    public void test10_updateWatchInWatches_UpdatesNonExistentWatch_ReturnsSameListOfInformationAsBeforeUpdate(){
    	Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
 	   
    	List<Watches> watches = new ArrayList<Watches>();
 	    watches.add(watch1);
 	   
 	   WatchesDAO wd = new WatchesDAO();
 	   wd.addWatch(watch1);
 	   
 	   Watches newWatch = new Watches(6, "Citizen", "Men's Citizen Pro Master Divers Watch", 182.00); 
 	   wd.updateWatch(newWatch);
 	   List<Watches> watchesDaoList = wd.listAllWatches();
 	   
 	   assertEquals(watches,watchesDaoList);
    }
    
    @Test
    public void test11_getWatchInWatchesFromEmptyWatchesTable_ReturnsNull(){
    	WatchesDAO wd = new WatchesDAO();
    	
    	Watches watch = wd.getWatch(1);
    	
    	assertEquals(null,watch);
    }
    
    @Test
    public void test12_getWatchInWatches_ReturnsDetailsOfTheWatchThatWasPutThroughTheGetWatch(){
    	Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
    	
    	WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		
		Watches retrievedWatch = wd.getWatch(1);
	 	
		assertEquals(watch1, retrievedWatch);
    }
    
    @Test
    public void test13_removeWatchFromWatches_ReturnsListSizeWhenMoreThanOneWatchIsRemoved(){
    	//Testing to see if the remove method can remove more than 1 watch
    	Watches watch1 = new Watches(1,"Hugo Boss","Men's Hugo Boss Grand Prix Chronograph", 350.00);
    	Watches watch2 = new Watches(2,"Casio","Men's Casio G-Shock Alarm Chronograph", 72.00);
    	Watches watch3 = new Watches(3, "Tommy Hilfiger", "Tommy Hilfiger Gavin Watch", 172.00);
    	
    	WatchesDAO wd = new WatchesDAO();
		wd.addWatch(watch1);
		wd.addWatch(watch2);
		wd.addWatch(watch3);
		
		wd.removeWatch(3);
		wd.removeWatch(2);
        List<Watches>watchesDaoList = wd.listAllWatches();
        
        assertEquals(1,watchesDaoList.size());
    }
    
    
}
