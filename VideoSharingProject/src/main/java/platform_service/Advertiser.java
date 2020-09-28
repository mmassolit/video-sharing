package platform_service;

import java.util.LinkedList;

import ads_service.Ad;

public final class Advertiser extends Account {
	private LinkedList<Ad> adList;
	
	public Advertiser(String name) {
		super(name);
		this.adList = new LinkedList<Ad>();
	}
	
	public LinkedList<Ad> getAdsList() {
		return this.adList;
	}
	
	public void setAdsList(LinkedList<Ad> adsList) {
		this.adList = adsList;
	}
	
	
	@Override
	public String toString() {
		return "Advertiser(name:" + name + "; balance: " + balance + "; ads: " + adList + ")";
	}
	
}
