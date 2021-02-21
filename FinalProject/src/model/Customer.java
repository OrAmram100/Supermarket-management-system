package model;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import obsrever.Observable;
import obsrever.Observer;

public class Customer implements Observer {
	private String customerName;
	private String id;
	private String phoneNumber;
	private Boolean wantUpdates;
	private final static int MAX_CUST_NAME = 20;
	private final static int MAX_ID = 9;
	private final static int Phone_Num = 10;
	public static final int CUSTOMER_SIZE = (MAX_CUST_NAME*2) + (MAX_ID*2)+(Phone_Num*2) + 1;


	public Customer(String customerName, String id, String phoneNumber, boolean wantUpdates) {
		this.customerName = customerName;
		this.id = id;
		this.phoneNumber = phoneNumber;
		setWantUpdates(wantUpdates);
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getId() {
		return id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public boolean getWantUpdates() {
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
	public String update(Observable obs, Product product) 
	{
		StringBuffer sf = new StringBuffer();
		if(getWantUpdates())
		{
			sf.append( this.getPhoneNumber() + " New Message Has Arrived!\n");
			sf.append(this.customerName + " Hi!\nNew sale to the product: \n" +product + " from:" +((Store) obs).getName()+"\n");	
			sf.append(sendMSG(obs));
		}
		return sf.toString();
	}

	public String sendMSG(Observable r) {
		StringBuffer sf = new StringBuffer();
		sf.append( "customer sent message back\n");
		sf.append("from: " +this.getCustomerName()+ "\n");
		return sf.toString();

	}

	public static Customer readCustomerToFile(DataInput dataInput) throws IOException {
		String name = binFile.readStringFromFile(MAX_CUST_NAME, dataInput);
		String id = binFile.readStringFromFile(MAX_ID, dataInput);
		String phone = binFile.readStringFromFile(Phone_Num, dataInput);	
		Boolean isWantUpdate = dataInput.readBoolean(); 
		return new Customer(name ,id, phone , isWantUpdate);
	}
	public void writeCustomerToFile(DataOutput dOut) throws IOException
	{
		binFile.writeStringToFile(this.customerName, MAX_CUST_NAME, dOut);
		binFile.writeStringToFile(this.id, MAX_ID, dOut);
		binFile.writeStringToFile(this.phoneNumber, Phone_Num, dOut);		
		dOut.writeBoolean(this.wantUpdates);
	}
}

