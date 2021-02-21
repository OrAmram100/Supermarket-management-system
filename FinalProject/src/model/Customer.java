package model;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import obsrever.Observable;
import obsrever.Observer;

public class Customer extends Thread implements Observer {
	private String customerName;
	private String customerId;
	private String phoneNumber;
	private boolean wantUpdates;
	private final static int MAX_CUST_NAME = 20;
	private final static int MAX_ID = 9;
	private final static int Phone_Num = 10;
	public static final int CUSTOMER_SIZE = (MAX_CUST_NAME*2) + (MAX_ID*2)+(Phone_Num*2) + 1;


	public Customer(String customerName, String customerId, String phoneNumber, boolean wantUpdates) {
		this.customerName = customerName;
		this.customerId = customerId;
		this.phoneNumber = phoneNumber;
		this.wantUpdates = wantUpdates;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerId() {
		return customerId;
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
		return "\nCustomer:\ncustomer Name: " + customerName + "\ncustomer ID: " + customerId + "\nphone Number: " + phoneNumber
				+ "\nwant Updates: " + wantUpdates;
	}

	@Override
	public String update(Observable obs, Product product) 
	{
		StringBuffer sf = new StringBuffer();
		if(isWantUpdates())
		{
//			sf.append( this.getPhoneNumber() + " New Message Has Arrived!\n");
//			sf.append(this.customerName + " Hi!\nNew sale to the product: \n" +product + " from:" +((Store) obs).getNameStore()+"\n");	
			sf.append(sendMSG());
		}
		return sf.toString();
	}

	public String sendMSG() {
//		StringBuffer sf = new StringBuffer();
//		sf.append( "customer sent message back\n");
//		sf.append("from: " +this.getCustomerName()+ "\n");
		String replay = this.getCustomerName();
		return replay;

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
		binFile.writeStringToFile(this.customerId, MAX_ID, dOut);
		binFile.writeStringToFile(this.phoneNumber, Phone_Num, dOut);		
		dOut.writeBoolean(this.wantUpdates);
	}
}

