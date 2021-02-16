package model;

import obsrever.Observable;
import obsrever.Observer;

public class Customer implements Observer {
	private String customerName;
	private String id;
	String phoneNumber;
	private boolean wantUpdates;

	public Customer(String customerName, String id, String phoneNumber, boolean wantUpdates) {
		this.customerName = customerName;
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.wantUpdates = wantUpdates;
	}

	//GETTRES:
	public String getCustomerName() {
		return customerName;
	}

	public String getId() {
		return id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public boolean isWantUpdates() {
		return wantUpdates;
	}

	// SETTERS:
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setWantUpdates(boolean wantUpdates) {
		this.wantUpdates = wantUpdates;
	}
	
	

	@Override
	public String toString() {
		return "Customer:\n customerName:" + customerName + ", id:" + id + ", phoneNumber:" + phoneNumber
				+ ", wantUpdates:" + wantUpdates;
	}

	@Override
	public void update(Observable obs, Product product) {
//		System.out.print("---------------- " + r.getSimNumber() + " ----------------\nNew Message Has Arrived\n");
	}
	
	
//	@Override
//	public void sendMSG(Receiver r, Message msg) {
//		System.out.print("---------------- " + r.getSimNumber() + " ----------------\nNew Message Has Arrived\n");
//		System.out.print("-----------------------------------------\n");
//		receiveMSG(this, msg);

}
