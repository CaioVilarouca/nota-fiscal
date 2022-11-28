package entitites;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShoppingCart {
	private String dayOfPagamento;
	private List<Purchase> listPurchase = new ArrayList<Purchase>();
	
	public List<Purchase> getListPurchase() {
		return listPurchase;
	}

	public void setListPurchase(List<Purchase> listPurchase) {
		this.listPurchase = listPurchase;
	}

	public void addPurchase(Purchase purchase) {
		listPurchase.add(purchase);
	}
	
	public String getDate() {
		return dayOfPagamento;
	}

	public void setDate(String dayOfPagamento) {
		this.dayOfPagamento = dayOfPagamento;
	}
	
	public String datePerchase() {
		Date date = new Date();
		SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
		dayOfPagamento = formatData.format(date);
		return dayOfPagamento;
	}
	
	public Double getValueTotalPurchase() {
		double valueTotal = 0;
		for (Purchase purchase : listPurchase) {
			valueTotal+= purchase.getTotalValueOfProduct();
		}
		return valueTotal;
	}
}
