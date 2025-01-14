import java.util.Random;
public class Depot {
    private Garage garage; 
    private RoadMap city;
    private DeliveryQueue<Package> packageQueue;
    
    public Depot(RoadMap graph) {
        garage = new Garage(); 
        packageQueue = new DeliveryQueue<>();
        city = graph;
        
    }
    // method to set the location of the depot.
    public void setCityBase(Location l){
        setcityBase(l);
    }
    private void setcityBase(Location l){
        city.setBase(l);
    }

    public Garage getGarage() {
        return garage;
    }   
    // method to get packages (supposedly from a third-party contractor)
    public void fillPackages(int number){
        Random random = new Random();
        for(int i=0;i<number;i++){
            packageQueue.enqueue(new Package(i, random.nextBoolean(), (random.nextInt(80)),city.getRandomLocation() , -1));
        }
    }
    // method to add a truck to the garage, based on its id and the district it belongs to
    public void addTruckToGarage(int truckid){
        addtruckToGarage(truckid);
    }
    private void addtruckToGarage(int truckid){
        Random random= new Random();
        garage.addTruck(truckid, (random.nextInt(1000)+2500), (random.nextInt(1000)+10000),city );
    }

    public DeliveryQueue<Package> getPackageQueue() {

        return packageQueue;

    }
    // method to fill the truck with packages, based on the district it is in, until the packages run out or the truck is full weight.
    public void fillTruckWithPackages(int truckID, District d) {
        filltruckWithPackages(truckID, d);
    }

    private void filltruckWithPackages(int truckID, District d) {
            Truck t =garage.getTruck(truckID);
            DeliveryQueue<Package> newQueue = new DeliveryQueue<>();
            for(int j=0;j<packageQueue.size();j++){
                Package p = null;
                try{
                p = packageQueue.dequeue();
                }catch(DeliveryQueueEmptyException e){
                    System.out.println(" Del Queue is empty.");
                }
                if(d.partOfDistrict(p.getDeliveryLocation()) ){
                    try{
                        t.addPackage(p);
                    }catch(TruckOverWeightException e){
                        System.out.println("Filled the truck"); 
                        break; 
                    }
                }
                else{
                    newQueue.enqueue(p);
                }
            }packageQueue= newQueue;
        
        
    }
    // method to set the truck off to start delivering.
    public void setTruckOff(int truckID) {
        settruckOff(truckID);
    }
    private void settruckOff(int truckID) {
        Truck t = garage.getTruck(truckID);
        t.setTruckCurrentLocation(city.getBase());
        t.setOff();

    }
}     