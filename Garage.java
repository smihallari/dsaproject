import java.util.ArrayList;

public class Garage {
    private ArrayList<Truck> trucks; 

    public Garage() {
        trucks = new ArrayList<Truck>();
    }

    public int size(){
        return trucks.size();
    }

    public void addTruck(int ID,int truckWeight, int maxCarryWeight,District d){
        trucks.add(new Truck(ID,truckWeight,maxCarryWeight,d));
    }
    public Truck getTruck(int truckID)  {
        for(int i=0;i<trucks.size();i++){
            if(trucks.get(i).getTruckID()==truckID){
                return trucks.get(i);
            }
        }return null;
        
    }

    public void removeTruck(int truckId) {
        for (int i = 0; i < trucks.size(); i++) {
            if (trucks.get(i).getTruckID() == truckId) {
                trucks.remove(i);
            }
        }
    }


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