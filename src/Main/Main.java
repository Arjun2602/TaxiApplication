package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		List<Taxi> taxiList = createTaxi(4);
		int id = 1;
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("1)Call taxi booking..");
			System.out.println("2) Display the Taxi details..");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				//booking
				int customerId = id;
				
				System.out.println("Pickup point : ");
				char PickupPoint = sc.next().charAt(0);
				
				System.out.println("Drop point : ");
				char DropPoint = sc.next().charAt(0);
				
				System.out.println("Pick up Time : ");
				int PickUptime = sc.nextInt();
				
				List<Taxi> freetaxiList = getFreeTaxis(taxiList, PickupPoint, DropPoint, PickUptime);
				if(freetaxiList.size() == 0) {
					System.out.println("No taxi Available..");
					return;
				}
				
				Collections.sort(freetaxiList,(a,b)->a.totalEarning - b.totalEarning); 
				bookTaxi(freetaxiList, customerId, PickupPoint, DropPoint, PickUptime);
				id++;
				break;
			case 2:
				// print details
				for(Taxi t : taxiList) {
					t.printDetails();
				}
				break;
			default:
				flag = false;
				break;
			}
		}

	}
	
	// taxicount = taxicount + 1;
    // id = taxicount;
	// currentspot = 'A'
	// this.freeTime = 6;
	// this.totalEarning = 0;
	// this.taxiTrips = new ArrayList<>();
	
	/*Taxi No:    Total Earnings:
	BookingID    CustomerID    From    To    PickupTime    DropTime    Amount
	 * */
	
	
	// book taxi
	private static void bookTaxi(List<Taxi> freetaxiList, int customerId, char pickupPoint, char dropPoint, int pickUptime) {
		int min = 999;
		int distaBtwpickUppointAndcurrentSpot = 0;
		Taxi taxi = null;
		
		for(Taxi t : freetaxiList) {
			distaBtwpickUppointAndcurrentSpot = Math.abs((t.currentSpot - '0') - (pickupPoint - '0')) * 15;
			if(distaBtwpickUppointAndcurrentSpot < min) {
				min = distaBtwpickUppointAndcurrentSpot;
				taxi = t;
			}
		}
		char nextSpot = dropPoint;
		int distaBtwpicuppointAndDropuppoint = Math.abs((pickupPoint - '0') - (dropPoint - '0')) * 15;
		int dropTime = pickUptime + distaBtwpicuppointAndDropuppoint/15;
		int Amount = (distaBtwpicuppointAndDropuppoint - 5)*10 + 100;
		//
		String tripDetails = customerId + "               " + customerId + "          " + pickupPoint +  "       " + dropPoint + "          " + pickUptime + "          " +dropTime + "          " + Amount;
		taxi.setDetails(nextSpot, dropTime, taxi.totalEarning + Amount, tripDetails);
		System.out.println("Taxi "+taxi.id+" Booked successfully");
	}


	// get free taxi list 
	private static List<Taxi> getFreeTaxis(List<Taxi> taxiList, char pickupPoint, char dropPoint, int pickUptime) {
		List<Taxi> freeTaxiList = new ArrayList<>();
		for(Taxi taxi : taxiList) {
			if(taxi.freeTime <= pickUptime && Math.abs(taxi.currentSpot - pickupPoint) <= pickUptime - taxi.freeTime) {
				freeTaxiList.add(taxi);
			}
		}
		return freeTaxiList;
	}


	// create no of taxis
	private static List<Taxi> createTaxi(int n) {
		List<Taxi> taxiList = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			Taxi taxi = new Taxi();
			taxiList.add(taxi);
		}
		return taxiList;
	}

}
