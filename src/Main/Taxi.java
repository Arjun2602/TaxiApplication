package Main;

import java.util.ArrayList;
import java.util.List;

public class Taxi {
	
	static int taxicount = 0;
	int id;
	char currentSpot;
	int freeTime;
	int totalEarning;
	List<String> taxiTrips;
	
	public Taxi() {
		Taxi.taxicount = taxicount+1;
		this.id = taxicount;
		this.currentSpot = 'A';
		this.freeTime = 6;
		this.totalEarning = 0;
		this.taxiTrips = new ArrayList<>();
	}
	/*Taxi No:    Total Earnings:
	BookingID    CustomerID    From    To    PickupTime    DropTime    Amount
	 * */
	
	public void printDetails() {
		System.out.println("Taxi No:"+this.id+"    Total Earnings:"+this.totalEarning);
		System.out.println("BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
		for(String trips : this.taxiTrips) {
			System.out.println(trips);
		}
		System.out.println("---------------------------------------------------------------------------");
	}

	public void setDetails(char nextSpot, int dropTime, int totalEarnings, String tripDetails) {
		this.currentSpot = nextSpot;
		this.freeTime = dropTime;
		this.totalEarning = totalEarnings;
		this.taxiTrips.add(tripDetails);
	}
}

