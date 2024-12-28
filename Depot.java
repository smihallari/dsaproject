import java.util.Random;
public class Depot {
    private Garage garage; 
    private DeliveryQueue<Package> packageQueue;
    
    public Depot() {
        this.garage = new Garage(); 
        this.packageQueue = new DeliveryQueue<>(); 
    }

    public void fillPackages(int number){
        Random random = new Random();
        for(int i=0;i<number;i++){
            packageQueue.enqueue(new Package(i, random.nextBoolean(), (random.nextInt(80)+100),graphVertices.get(random.nextInt(graphVertices.size())) , -1));
        }
    }

    public void setTrucks(int numOfTrucks){
        Random random= new Random();
        for(int i=0;i<numOfTrucks;i++){
            garage.addTruck(i, random.nextInt(1000)+2500, random.nextInt(1000)+4000);
        }
    }

    public void fillTruckWithPackages(int truckID,District district) throws NoSuchTruckException{
            Truck t =garage.getTruck(truckID);
            for(int j=0;j<packageQueue.size();j++){
                if(district.partOfDistrict(packageQueue.peek().getDeliveryLocation())){
                    t.addPackage(packageQueue.dequeue());
                }
            }
        
        
    }


}     