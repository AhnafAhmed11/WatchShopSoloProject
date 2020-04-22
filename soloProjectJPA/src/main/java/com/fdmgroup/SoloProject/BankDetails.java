package com.fdmgroup.SoloProject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="BankDetails")
public class BankDetails {

	@Id
	@SequenceGenerator(name = "cardIdSeq", sequenceName = "CARD_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cardIdSeq")
	@Column(name="card_id",length=4)
	private int cardID;
	
	@Column(name="card_number",length=12)
	private long cardNum;
	
	@Column(name="balance",length=10)
	private double balance;
	
	@Column(name="sortcode",length=7)
	private int sortCode;
	
	
	public BankDetails() {
		super();
	}

	public BankDetails(int cardID, int cardNum, int balance, int sortCode) {
		super();
		this.cardID = cardID;
		this.cardNum = cardNum;
		this.balance = balance;
		this.sortCode = sortCode;
	}

	public int getCardID() {
		return cardID;
	}

	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	public long getCardNum() {
		return cardNum;
	}

	public void setCardNum(long l) {
		this.cardNum = l;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double d) {
		this.balance = d;
	}

	public int getSortCode() {
		return sortCode;
	}

	public void setSortCode(int sortCode) {
		this.sortCode = sortCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + cardID;
		result = prime * result + (int) (cardNum ^ (cardNum >>> 32));
		result = prime * result + sortCode;
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
		BankDetails other = (BankDetails) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (cardID != other.cardID)
			return false;
		if (cardNum != other.cardNum)
			return false;
		if (sortCode != other.sortCode)
			return false;
		return true;
	}	
	
}
