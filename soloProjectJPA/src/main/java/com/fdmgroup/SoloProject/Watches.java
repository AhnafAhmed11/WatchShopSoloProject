package com.fdmgroup.SoloProject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Watches")
public class Watches {
	
	@Id
	@Column(name="watch_id", length=4)
	private int watchID;
	
	@Column(name="watch_Name", length=15)
	private String watchName;
	
	@Column(name="description", length=50)
	private String Description;
	
	@Column(name="pricePerUnit",length=8)
	private double price;
	
	
	public Watches() {
		super();
	}

	public Watches(int watchID, String watchName, String description, double price) {
		super();
		this.watchID = watchID;
		this.watchName = watchName;
		this.Description = description;
		this.price = price;
	}

	public int getWatchID() {
		return watchID;
	}

	public void setWatchID(int watchID) {
		this.watchID = watchID;
	}

	public String getWatchName() {
		return watchName;
	}

	public void setWatchName(String watchName) {
		this.watchName = watchName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Description == null) ? 0 : Description.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + watchID;
		result = prime * result + ((watchName == null) ? 0 : watchName.hashCode());
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
		Watches other = (Watches) obj;
		if (Description == null) {
			if (other.Description != null)
				return false;
		} else if (!Description.equals(other.Description))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (watchID != other.watchID)
			return false;
		if (watchName == null) {
			if (other.watchName != null)
				return false;
		} else if (!watchName.equals(other.watchName))
			return false;
		return true;
	}
	
}
