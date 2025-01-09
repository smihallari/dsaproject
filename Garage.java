import java.util.ArrayList;

public class Garage {
    private ArrayList<Truck> trucks; 

    public Garage() {
        trucks = new ArrayList<Truck>();
    }

    
    // method to add a truck to the garage list of trucks.
    public void addTruck(int ID,int truckWeight, int maxCarryWeight,RoadMap city){
        trucks.add(new Truck(ID,truckWeight,maxCarryWeight,city));
    }
    // get a specific truck based on its id.
    public Truck getTruck(int truckID)  {
        for(int i=0;i<trucks.size();i++){
            if(trucks.get(i).getTruckID()==truckID){
                return trucks.get(i);
            }
        }return null;
        
    }
    // remove a truck from the garage
    public void removeTruck(int truckId) {
        for (int i = 0; i < trucks.size(); i++) {
            if (trucks.get(i).getTruckID() == truckId) {
                trucks.remove(i);
            }
        }
    }

    // display which trucks are available.
    public void displayAvailableGarage() {
        for (int i=0;i<trucks.size();i++) {
            if (!trucks.get(i).isonDelivery()) {
                System.out.println("Truck with ID"+trucks.get(i).getTruckID()+" is available.");
            } else {
                System.out.println("Truck with ID"+trucks.get(i).getTruckID()+" is on a delivery.");
            }
        }
    }
}