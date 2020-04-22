package com.fdmgroup.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.SoloProject.BankDetails;
import com.fdmgroup.SoloProject.BankDetailsDAO;
import com.fdmgroup.SoloProject.Customer;
import com.fdmgroup.SoloProject.CustomerDAO;
import com.fdmgroup.SoloProject.Order;
import com.fdmgroup.SoloProject.OrderDAO;
import com.fdmgroup.SoloProject.Watches;
import com.fdmgroup.SoloProject.WatchesDAO;
import com.fdmgroup.validators.RegistryValidation;
import com.fdmgroup.validators.Validation;

@Controller
public class HomeController {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soloProjectJPA");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
	private HashMap<Integer, Integer> basket = new HashMap<Integer, Integer>();
	private HashMap<Watches, Integer> basketItems = new HashMap<Watches, Integer>();
	
	
	public void syncHashMap(){
		basketItems.clear();
		for(Integer eachWatchID: basket.keySet()){
			int quantity = basket.get(eachWatchID);
			
			WatchesDAO wd = new WatchesDAO();
			Watches watch = wd.getWatch(eachWatchID);
			
			basketItems.put(watch, quantity);
		}
	}
	
	@RequestMapping(value = "basket")
	public String basket(Model model){
		syncHashMap();
		model.addAttribute("basketItems", basketItems);
		System.out.println("this contains: "+basketItems);
		
		return "basket";
	}
	
	
	@RequestMapping(value = "/")
	public String loginPage(Model model) {
		model.addAttribute("customer", new Customer());
		return "login";
	}

	@RequestMapping(value = "submitLogin", method = POST)
	public String loginSubmitHandler(Model model, Customer customer, HttpServletRequest request) {
		model.addAttribute("customer", customer);
		Validation validation = new Validation();
		boolean result = validation.validate(customer);
		if (result == true){
			HttpSession session = request.getSession();
			session.setAttribute("username", customer.getUsername());
			return "loginSuccess";
			} else {
			request.setAttribute("message", "Wrong login and password");
			return "login";
		}
	}
	
	@RequestMapping(value = "loginSuccess")
	public String loginSuccessHandler(Model model, Customer customer, HttpServletRequest request, HttpSession session) {
		
			String username = (String)session.getAttribute("username");
			CustomerDAO cd = new CustomerDAO();
			customer = cd.getCustomerByUsername(username);
			model.addAttribute("customer", customer);
			return "loginSuccess";
		
	}
	
	
	@RequestMapping(value = "registration")
	public String registrationHandler(Model model) {
		model.addAttribute("customer", new Customer());
		return "registration";
	}
	
	@RequestMapping(value = "login")
	public String loginHandler(Model model) {
		model.addAttribute("customer", new Customer());
		return "login";
	}
	
	@RequestMapping(value = "submitRegister")
	public String submitRegistrationHandler(Model model, Customer customer, HttpServletRequest request) {
		model.addAttribute("customer", customer);
		RegistryValidation registryValidation = new RegistryValidation();
		boolean result = registryValidation.validate(customer);
		
		if(result == true){
			CustomerDAO cd = new CustomerDAO();
			cd.addCustomer(customer);
			return "login";
		}else {
			request.setAttribute("message", "Please enter valid information");
			return "registration";
		}
		
	}
	
	@RequestMapping(value = "addAccount")
	public String addAccountHandler(Model model) {
		model.addAttribute("bankDetails", new BankDetails());
		return "addAccount";
	}
	
	@RequestMapping(value = "submitAddAccount")
	public String submitAddAccountHandler(Model model, BankDetails bankDetails, HttpSession session){
		model.addAttribute("bankDetails", bankDetails);
		
		BankDetailsDAO bd = new BankDetailsDAO();
		bd.addBankDetails(bankDetails);
		
		session.setAttribute("cardID", bankDetails.getCardID());
		
		String username = (String)session.getAttribute("username");
		CustomerDAO cd = new CustomerDAO();
		Customer customer = cd.getCustomerByUsername(username);
		customer.setBankDetails(bankDetails);
		cd.updateCustomer(customer);
		
		return "addAccount";
	}
	
	
	@RequestMapping("listWatches")
	public String listWatchItems(Model model){
		WatchesDAO wd = new WatchesDAO(entityManager);
        List<Watches> listWatches = wd.listAllWatches();
        model.addAttribute("listWatches", listWatches);
        
        return "listWatches";

	}
	
	@RequestMapping("listOrders")
	public String listOrderItems(Model model, Customer customer, HttpSession session){
		model.addAttribute("customer", customer);
		String username = (String) session.getAttribute("username");
		OrderDAO od = new OrderDAO(entityManager);
        List<Order> listOrders = od.listCustomerOrder(username);
        model.addAttribute("listOrders", listOrders);
        
        return "listOrders";
	}
	
	@RequestMapping("customerProfile")
	public String getCustomerItem(Model model, Customer customer, HttpSession session){
		model.addAttribute("customer", customer);
		String username = (String)session.getAttribute("username");
		
		CustomerDAO cd = new CustomerDAO();
		Customer customerInDB = cd.getCustomerByUsername(username);
		
		model.addAttribute("customerInDB", customerInDB);
		
		return "customerProfile";
		
	}
	
	@RequestMapping("listOfBankDetails")
	public String listCustomerAccount(Model model, Customer customer, BankDetails bankDetails, HttpSession session){
		model.addAttribute("customer", customer);
		String username = (String)session.getAttribute("username"); 
		
		CustomerDAO cd = new CustomerDAO();
		Customer customerInDB = cd.getCustomerByUsername(username);
		
		BankDetails account = customerInDB.getBankDetails();
		
		BankDetailsDAO bd = new BankDetailsDAO();
		
		int cardID= account.getCardID();
		BankDetails accountInDB = bd.getBankDetails(cardID);
		
		model.addAttribute("accountInDB", accountInDB);
		return "listOfBankDetails";
	}
	
	@RequestMapping(value = "addOrder")
	public String orderSubmitHandler(Model model, Order order, HttpSession session, HttpServletRequest request) {
		model.addAttribute("order", order);
		
		OrderDAO od = new OrderDAO();
		CustomerDAO cd = new CustomerDAO();
		
		String username = (String) session.getAttribute("username");
		Customer customer = cd.getCustomerByUsername(username);
		
		
		String orderNumberString = ""+Math.random();
		orderNumberString=orderNumberString.substring(0, 8);
		order.setOrderNumber(orderNumberString);
		order.setOrderDate(new Date());
		order.setCustomer(customer);
		
		od.addOrder(order);
		
		model.addAttribute("orders", order);
		return "confirmOrder";
		
	}
	
	@RequestMapping(value = "addItemToBasket/{watchID}" , method=POST)
	public String addProduct(@PathVariable int watchID, Model model, HttpServletRequest request, HttpSession session) {

		if(basket.containsKey(watchID)){
			int quantity = basket.get(watchID);
			basket.put(watchID, quantity+1);
		}else{
			basket.put(watchID, 1);
		}
		syncHashMap();
		model.addAttribute("basketItems", basketItems);
		
		double total = calculateTotal(session, request);
		request.setAttribute("total", total);
		
		return "basket";
	}
	
	@RequestMapping(value = "removeItemFromBasket/{watchID}", method=POST)
	public String removeProduct(@PathVariable int watchID, Model model, HttpServletRequest request, HttpSession session){
		
		boolean isInBasket = basket.containsKey(watchID);

		if(isInBasket){
			
			int quantity = basket.get(watchID);
						
			if (quantity > 1){
				
				basket.put(watchID, quantity-1);
			} else if(quantity <= 1){
				basket.remove(watchID); 
			}				
		
		} 

		syncHashMap();
		
		model.addAttribute("basketItems", basketItems);
		
		double total = calculateTotal(session, request);
		request.setAttribute("total", total);
		return "basket";
	}
	
	public double calculateTotal(HttpSession session, HttpServletRequest request){
		String username = (String) session.getAttribute("username");
		CustomerDAO cd = new CustomerDAO();
		Customer customer = cd.getCustomerByUsername(username);
		
		double balance = customer.getBankDetails().getBalance();
		request.setAttribute("balance", balance);
		
		double total=0;
		for(Entry<Watches, Integer> eachWatch : basketItems.entrySet()){
			double price = eachWatch.getKey().getPrice();
			int quantity1 = eachWatch.getValue();
			total = total + quantity1*price;
		}
		
		if(balance<total){
			String message1 = "You're broke, take things out";
			request.setAttribute("message1", message1);
		}
		return total;
	}

	@RequestMapping(value = "removeCustomer")
	public String removeCustomerInDB(Model model, Customer customer, HttpSession session){
		model.addAttribute("customer", customer);
		String username = (String)session.getAttribute("username");
		
		CustomerDAO cd = new CustomerDAO();
		customer = cd.getCustomerByUsername(username);
		cd.removeCustomer(customer.getCustID());
		return "login";
	}
}
