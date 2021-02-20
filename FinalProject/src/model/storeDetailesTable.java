package model;

public class storeDetailesTable {
	String serial;
	String productName;
	String priceToStore;
	String sellPrice;
	String customerName;
	String id;
	String customerPhone;
	String wantsUpdate;
	int profit;


	public storeDetailesTable(String serial, String productName,String PriceToStore,String sellPrice,String customerName,String id,
			String customerPhone, String wantsUpdate,int profit)
	{
		this.serial = serial;
		this.productName = productName;
		this.priceToStore = PriceToStore;
		this.sellPrice = sellPrice;
		this.customerName = customerName;
		this.id = id;
		this.customerPhone = customerPhone;
		this.wantsUpdate = wantsUpdate;
		this.profit=profit;
	}

	public int getProfit() {
		return profit;
	}
	
	public String getId() {
		return id;
	}

	public String getSerial() {
		return serial;
	}

	public String getProductName() {
		return productName;
	}

	public String getPriceToStore() {
		return priceToStore;
	}

	public String getSellPrice() {
		return sellPrice;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public String getwantsUpdate() {
		return wantsUpdate;
	}


}

