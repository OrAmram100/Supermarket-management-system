package model;

import obsrever.Observable;
import obsrever.Observer;

public class Customer  implements Observer
{	
	private String customerName;
	private String id;	
	private	boolean wantUpdates;
	@Override
	public void update(Observable obs, Product product)
	{
		
	}
	
}
