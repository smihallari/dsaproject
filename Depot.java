import java.util.Random;
public class Depot {
    private Garage garage; 
    private CustomGraph city;
    private DeliveryQueue<Package> packageQueue;
    
    public Depot(CustomGraph graph) {
        this.garage = new Garage(); 
        this.packageQueue = new DeliveryQueue<>();
        this.city=graph;
    }

    public void fillPackages(int number){
        Random random = new Random();
        for(int i=0;i<number;i++){
            packageQueue.enqueue(new Package(i, random.nextBoolean(), (random.nextInt(80)+100),city.getCityLocations().get(random.nextInt(city.getCityLocations().size())) , -1));
        }
    }

    public void addTruckToGarage(int truckid,District d){
        Random random= new Random();
        garage.addTruck(truckid, (random.nextInt(1000)+2500), (random.nextInt(1000)+4000),d );
    }
    public void fillTruckWithPackages(int truckID,District district) throws NoSuchTruckException{
            Truck t =garage.getTruck(truckID);
            for(int j=0;j<packageQueue.size();j++){
                if(district.partOfDistrict(packageQueue.peek().getDeliveryLocation())){
                    t.addPackage(packageQueue.dequeue());
                }
            }
        
        
    }

    public void setTruckOff(int truckID) {
        Truck t = null;
        try {
            t = garage.getTruck(truckID);
        } catch (NoSuchTruckException e) {
            e.printStackTrace();
        }
        t.setOff();
        t.returnToDepot();

    }
}     