import java.util.*;

class Truck{
    public int truckID;
    public boolean onDeliveryjob;
    private static CustomList<Package> CargoList;
    public static float truckWeight;
    public static float maxWeight;
    public static Location truckCurrentLocation;
    public static float truckFuel;
    public static LinkedList<Location> truckRoute;
    public static boolean onItsWayToRefuel;


    Truck(int ID,float weight,float max){
        truckID = ID;
        onDeliveryjob = false;
        truckWeight = weight;
        CargoList = new CustomList<Package>();
        maxWeight = max;
        truckRoute = new LinkedList<Location>();
        onItsWayToRefuel = false;
    }

    public int getTruckId(){
        return truckID;
    }
    public  void addPackage ( Package p ) throws TruckOverWeightException
    {
        if( truckWeight + p.getWeight() >= maxWeight  ){
            throw new TruckOverWeightException(" Package can not be added to the truck, it can not store such a heavy load.");
        }
        else{
            truckWeight += p.getWeight();
            CargoList.add(p);
        }
    }

    public static void deliverPackages( ) throws noPackageToDeliverException
    {
        if ( CargoList.isEmpty() ) throw new noPackageToDeliverException("Cargo is empty, there is no package to deliver!");
        else {  
            for(int i=0;i<CargoList.size();i++)
            {   
                Package p= CargoList.get(i);
                if(p.getDeliveryLocation() == truckCurrentLocation){
                    System.out.println("Successfuly delivered package with ID: "+p.getPackageID()+" to location: "+truckCurrentLocation);
                    CargoList.remove(p);   
                    truckWeight -= p.getWeight();
                }
            }
        }
    }
    public static boolean truckNeedstoRefuel() {
        if( truckFuel < 30 ){
            return true;
        }else return false;
    }

    public static void goToReFuel (List<Location> gasStations) {

        List<Location> path  = dijkstraOnlyShortest(truckCurrentLocation, gasStations,graphVertices,graphEdges);
        
        truckRoute = new LinkedList<Location>();
        for(Location l : path){
            truckRoute.add( l );
        }
        onItsWayToRefuel = true;
    }

    public static void reFuel(){
        System.out.println("Paid 100$ to refuel! Fuel tank is now at "+truckFuel+"%!");
        truckFuel = 100;
        double moneypaid= (Math.random()*25 +50);
        System.out.println("Actually paid "+ moneypaid +"$ and scammed my boss for the rest ("+(100-moneypaid) +"$).");
        onItsWayToRefuel = false;
        getNextStop();
    }



    public static void goToLocation(Location l) throws NonExistentLocationException
    {
        int weight= getDistance(truckCurrentLocation,l,graphEdges);
        truckFuel-= weight/10;
        truckCurrentLocation = l;
    }

    public static Location getNextStop()  
    {
        if(!truckNeedstoRefuel()){
            if(!truckRoute.isEmpty()){
                return truckRoute.pollFirst();
            }
            else {
                calculateRoute();
                return truckRoute.pollFirst();
            }
        }
        else{
            if(onItsWayToRefuel) return  truckRoute.pollFirst();
            else{
            goToReFuel(fuelStations);
            return truckRoute.pollFirst();
            }
        }
    }
    public boolean isonDelivery(){
        return onDeliveryjob;
    }
    public void startedDelivering(){
        onDeliveryjob = true;
    }
    public void finishedDelivering(){
        onDeliveryjob = false;
    }
    public static void calculateRoute() {
        List<Location> locationsToVisit= new ArrayList<>();
        for(int i=0;i<CargoList.size();i++){
            Location l =  CargoList.get(i).getDeliveryLocation();
            if(!locationsToVisit.contains(l)){
                locationsToVisit.add(l);
            }
        }
        if(locationsToVisit.size()>10){
            Map < Location, List < Location > > paths  = dijkstra(truckCurrentLocation,locationsToVisit,graphVertices,graphEdges);
            reCalculateDeliveryWeightFactor(paths);
            int smallestWeight = 1000;
            Package chosen = nextDelivery();
            for (Location v: paths.get(  chosen.getDeliveryLocation())) {
                truckRoute.add((Location) v);
            }
        }
        else{
            List<Location> fullRoute = solveTSP(truckCurrentLocation,locationsToVisit,graphEdges,graphVertices);
            for( Location v : fullRoute){
                truckRoute.add( (Location) v);
            }
        }
    }
    public static void reCalculateDeliveryWeightFactor(Map < Location, List < Location > > paths){
        int proximityDelta = 1;
        int priorityDelta = 2;
        for (Map.Entry<Location, List<Location>> entry : paths.entrySet()) {
            Location target = entry.getKey();
            List<Location> path = entry.getValue();
            int totalDistanceWeight = 0;
            for (int i = 0; i < path.size() - 1; i++) {
                Location u = path.get(i);
                Location v = path.get(i + 1);
                totalDistanceWeight += getDistance(u, v, graphEdges);
            }
            for(int i=0;i<CargoList.size();i++){
                Package p= CargoList.get(i);
                if(p.getDeliveryLocation() ==(Location) target){
                    if(p.isPriority()) {
                        priorityDelta = 0;
                    }
                    else {
                        priorityDelta = 2;
                    }
                    p.setDelWF(proximityDelta * totalDistanceWeight + priorityDelta);
                }
            }
        
        }
    }
    public static void removeStop()
    {
        if(truckCurrentLocation == truckRoute.getFirst()){
        truckRoute.removeFirst();
        }

    }

    public static Package nextDelivery(){
        if( !CargoList.isEmpty() ){
            Package p = CargoList.get(0);
            for( int i =1; i<CargoList.size(); i++ ){
                if( CargoList.get(i).getDelWF() < p.getDelWF() ){
                    p=CargoList.get(i);
                }
            } 
            return p;
        }
    else return null;
    }

}