package com.fdmgroup.SoloProject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="CustomerOrders")
public class Order {
	
	@Id
	@SequenceGenerator(name = "orderIdSeq", sequenceName = "ORDER_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderIdSeq")
	@Column(name="order_id", length=4)
	private int orderID;
	
	@ManyToOne
	@JoinColumn(name="cust_Id")
	private Customer customer;
	
	@Column(name="order_number",length=8)
	private String orderNumber;
	
	@Temporal(TemporalType.DATE)
    @Column(name="order_date")
    private Date orderDate;

	@ElementCollection
	@CollectionTable(name="order_details", joinColumns=@JoinColumn(name="order_id"))
	
	@MapKeyJoinColumn(name="watch_id")
	@Column(name="quantity")
	private Map<Watches,Integer> basket = new HashMap<Watches,Integer>();
	
	
	
	public Order() {
		super();
	}

	public Order(int orderID, Customer customer, String orderNumber, Date orderDate, Map<Watches,Integer> basket) {
		super();
		this.orderID = orderID;
		this.customer = customer;
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.basket = basket;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basket == null) ? 0 : basket.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + orderID;
		result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (basket == null) {
			if (other.basket != null)
				return false;
		} else if (!basket.equals(other.basket))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderID != other.orderID)
			return false;
		if (orderNumber == null) {
			if (other.orderNumber != null)
				return false;
		} else if (!orderNumber.equals(other.orderNumber))
			return false;
		return true;
	}

}
