package ads_service;

import java.util.LinkedList;

import platform_service.Advertiser;

public final class AdsManagingService {
	private final LinkedList<Ad> fullAdsList = new LinkedList<Ad>();
	private final LinkedList<Advertiser> fullAdvertiserList = new LinkedList<Advertiser>();
			
	public static void validateAd(Advertiser advertiser, Ad ad) {
		if (advertiser.getBalance() < ad.getBudget())
			throw new IllegalArgumentException("Invalid value (advertiser's balance has to be bigger,"
					+ " than ad's budget).");
	}
	
	public void createAd(Advertiser advertiser, Ad ad) {
		validateAd(advertiser, ad);
		LinkedList<Ad> adsList = advertiser.getAdsList();
		adsList.addLast(ad);
		
		advertiser.setAdsList(adsList);
		advertiser.decreaseBalance(ad.getBudget());
		
		fullAdsList.addLast(ad);
		fullAdvertiserList.addLast(advertiser);
	}
	
	public void deleteAd(Advertiser advertiser, Ad ad) {
		LinkedList<Ad> adsList = advertiser.getAdsList();
		if (adsList.contains(ad))
			adsList.remove(ad);
			advertiser.setAdsList(adsList);
			
	    if 
	}
	
	public Ad pickAd() {
		double cpmMax = 0;
		int iMax = 0;
		
		for (int i=0; i < fullAdsList.size(); i++) {
			double cpmTemp = fullAdsList.get(i).getCpm();
			
			if (cpmTemp > cpmMax) {
				cpmMax = cpmTemp;
				iMax = i;
			}
		}
		
		return fullAdsList.get(iMax);
	}
	
	public void decreaseBudget(Advertiser advertiser, Ad ad) {
		double budget = ad.getBudget();
		double cpm = ad.getCpm();
		
		double decreaseAmount = cpm / 1000;
		
		if (budget < decreaseAmount) {
			this.deleteAd(advertiser, ad);
			throw new IllegalArgumentException("Too small budget. Ad was deleted.");
		}
		else {
			double newBudget = budget - decreaseAmount;
			ad.setBudget(newBudget);
		}
	}
}
