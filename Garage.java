public class Garage {
    private CustomList<Truck> trucks; 

    public Garage() {
        trucks = new CustomList<Truck>();
    }

    public int size(){
        return trucks.size();
    }

    public void addTruck(int ID,int truckWeight, int maxCarryWeight){
        trucks.add(new Truck(ID,truckWeight,maxCarryWeight));
    }
    public Truck getTruck(int truckID) throws NoSuchTruckException {
        for(int i=0;i<trucks.size();i++){
            if(trucks.get(i).getTruckId()==truckID){
                return trucks.get(i);
            }
        }
        throw new NoSuchTruckException("There is no truck with such an id");
    }

    public void removeTruck(int truckId) {
        for (int i = 0; i < trucks.size(); i++) {
            if (trucks.get(i).getTruckId() == truckId) {
                trucks.remove(i);
            }
        }
    }


    public void displayAvailableGarage() {
        for (int i=0;i<trucks.size();i++) {
            if (!trucks.get(i).isonDelivery()) {
                System.out.println("Truck with ID"+trucks.get(i).getTruckId()+" is available.");
            } else {
                System.out.println("Truck with ID"+trucks.get(i).getTruckId()+" is on a delivery.");
            }
        }
    }
}