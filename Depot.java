import java.util.Random;
public class Depot {
    private Garage garage; 
    private CustomGraph city;
    private DeliveryQueue<Package> packageQueue;
    
    public Depot(CustomGraph graph) {
        garage = new Garage(); 
        packageQueue = new DeliveryQueue<>();
        city = graph;
        
    }

    public void setCityBase(Location l){
        city.setBase(l);
    }

    public void fillPackages(int number){
        Random random = new Random();
        for(int i=0;i<number;i++){
            packageQueue.enqueue(new Package(i, random.nextBoolean(), (random.nextInt(80)+100),city.getRandomLocation() , -1));
        }
    }

    public void addTruckToGarage(int truckid,District d){
        Random random= new Random();
        garage.addTruck(truckid, (random.nextInt(1000)+2500), (random.nextInt(1000)+4000),d );
    }
    public void fillTruckWithPackages(int truckID) {
            Truck t =garage.getTruck(truckID);
            for(int j=0;j<packageQueue.size();j++){
                if(t.getDistrict().partOfDistrict(packageQueue.peek().getDeliveryLocation()) ){
                    try{
                    t.addPackage(packageQueue.dequeue());
                    }catch(TruckOverWeightException e){
                        System.out.println("Filled the truck"); 
                        break; 
                    }
                    
                }
            }
        
        
    }

    public void setTruckOff(int truckID) {
        Truck t = garage.getTruck(truckID);
        t.setTruckCurrentLocation(city.getBase());
        t.setOff();

    }
}     