package obsrever;

import java.util.ArrayList;
import model.Customer;
import model.Store;

public class updateObservers extends Thread {

	public static class StoreUpdates extends Thread{
	
		private ArrayList<Observer> allCustomers;


		private StoreUpdates() {
			allCustomers = new ArrayList<Observer>();
		}

		public void attach(Observer observer) {
			allCustomers.add(observer);
		}

		public String[] notifyAllObservers() {
			String[] observerlist = new String[allCustomers.size()];
			int counter =0;
			for (Observer observer : allCustomers) {
				observerlist[counter++] = ((Customer) observer).sendMSG();
			}
			return observerlist; 
		}
		public void showCustomerNames(String[] names, Store store)
		{
			replay MSG = new replay(names,store);
				MSG.start();
		}

		public class replay extends Thread
		{
			String[] names;
			private Store store;
			replay (String[] names, Store store)
			{
				this.names=names;
				this.store= store;
			}
			@Override
			public void run() {
				for(int i= 0 ; i<allCustomers.size();i++)
				{
					System.out.println(names[i]);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			@Override
			public void interrupt() {
				super.interrupt();
			}
			
		}
	}
}

