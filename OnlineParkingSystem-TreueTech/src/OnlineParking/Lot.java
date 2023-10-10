package OnlineParking;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;


public class Lot {
	private List<Spot> spots;
	 private Map<String, Users> users;
	 
	    public Lot(int volume) {
	    	
	        spots = new ArrayList<>();
	        
	        for (int i = 0; i < volume; i++) {
	            spots.add(new Spot(i + 1));
	        }
	        users=new HashMap<>();
	    }
	    
	    public void registerUser(String username, String password) {
	        users.put(username, new Users(username, password));
	    }

	    public Users getUser(String username) {
	        return users.get(username);
	    }

	    public boolean parkVehicle(Vehicle vehicle,Users user) {
	        for (Spot spot : spots) {
	            if (!spot.isOccupied() && !spot.isBooked()) {
	                spot.parkVehicle(vehicle);
	                spot.book();
	                System.out.println("Vehicle parked successfully in spot " + spot.getSpotNumber());
	                return true;
	            }
	        }
	        System.out.println("No available spots.");
	        return false;
	    }
	    
	    public void searchSpot(Users user) {
	        for (Spot spot : spots) {
	            if (!spot.isOccupied() && !spot.isBooked()) {
	                System.out.println("Spot " + spot.getSpotNumber() + " is available.");
	            }
	        }
	    }
	    
	
	    
	    public void confirmBooking(Users user, int spotNumber) {
	      Spot spot = spots.get(spotNumber - 1);
	        if (!spot.isOccupied() && !user.equals(spot)) {
	            spot.unbook();
	            System.out.println("Booking confirmed for spot " + spot.getSpotNumber());
	        } else {
	            System.out.println("Spot " + spot.getSpotNumber() + " is not available for confirmation.");
	        }
	    }
	

	    public void removeVehicle(Vehicle vehicle) {
	        for (Spot spot : spots) {
	            if (spot.isOccupied() && spot.getParkedVehicle().equals(vehicle)) {
	                spot.removeVehicle();
	                break;
	            }
	        }
	    }

	    public void displayParkingStatus() {
	        for (Spot spot : spots) {
	            if (spot.isOccupied()) {
	                System.out.println("Spot " + spot.getSpotNumber() + ": Occupied by " + spot.getParkedVehicle().getNumberPlate());
	            } else {
	                System.out.println("Spot " + spot.getSpotNumber() + ": Vacant");
	            }
	        }
	    }

}
