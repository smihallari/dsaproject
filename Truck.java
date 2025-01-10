import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Truck {
    private int truckID;

    public boolean onDeliveryjob;
    // the list of packages the truck currently has, which it needs to deliver
    private List<Package> CargoList;
    // current truck weight, taking into account its own(driver,fluids, miscelleanous included) and the total weight of the packages
    private float truckWeight;
    // maximum weight the truck can have
    private float maxWeight;
    // where the truck is in the city
    private Location truckCurrentLocation;

    // the current route of the truck, containing the locations of where it is going in succession.
    private LinkedList<Location> truckRoute;
    // the district the truck belongs to
    private District district;
    // the city the truck belongs to
    private RoadMap citymap;

    Truck(int ID, float weight, float max, RoadMap city) {
        truckID = ID;
        onDeliveryjob = false;
        truckWeight = weight;
        CargoList = new ArrayList<Package>();
        maxWeight = max;
        truckRoute = new LinkedList<Location>();
        citymap = city;
        truckCurrentLocation = citymap.getBase();


    }
    // Getter and Setter for truckID
    public int getTruckID() {
        return truckID;
    }

    public void setTruckID(int truckID) {
        this.truckID = truckID;
    }


    // Getter and Setter for CargoList
    public List<Package> getCargoList() {
        return CargoList;
    }

    public void setCargoList(List<Package> CargoList) {
        this.CargoList = CargoList;
    }

    // Getter and Setter for truckWeight
    public float getTruckWeight() {
        return truckWeight;
    }

    public void setTruckWeight(float truckWeight) {
        this.truckWeight = truckWeight;
    }

    // Getter and Setter for maxWeight
    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    // Getter and Setter for truckCurrentLocation
    public Location getTruckCurrentLocation() {
        return truckCurrentLocation;
    }

    public void setTruckCurrentLocation(Location truckCurrentLocation) {
        this.truckCurrentLocation = truckCurrentLocation;
    }

  
    // Getter and Setter for truckRoute
    public LinkedList<Location> getTruckRoute() {
        return truckRoute;
    }

    public void setTruckRoute(LinkedList<Location> truckRoute) {
        this.truckRoute = truckRoute;
    }


    // Getter and Setter for district
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    // method to add a package to the truck, checking that the weight of that package doesnt make the truck overweight.
        public void addPackage(Package p) {
            addpackage(p);
        }
        private void addpackage(Package p) throws TruckOverWeightException {
            if (truckWeight + p.getWeight() >= maxWeight) {
                throw new TruckOverWeightException("Package can not be added to the truck, it can not store such a heavy load.");
            } else {
                truckWeight += p.getWeight();
                CargoList.add(p);
            }
        }

        // method to deliver(or not) packages at the locations the truck is in.
        public void deliverPackages(){
            deliverpackages();
        }
        private void deliverpackages() {
            Iterator<Package> iterator = CargoList.iterator();
            while (iterator.hasNext()) {
                Package p = iterator.next();
                if (p.getDeliveryLocation() == getTruckCurrentLocation()) {
                    System.out.println("Successfully delivered package with ID: " + p.getPackageID() + " to location: " + getTruckCurrentLocation());
                    iterator.remove();
                    setTruckWeight(getTruckWeight() - p.getWeight());
                }
            }
        }
        

 

    //method to move the truck to a certain location in the map
    
    private  void goToLocation(Location l)   {
        int intWeight=Algorithms.getDistance(truckCurrentLocation, l, citymap.getCityRoads());
        if(intWeight>100) {
            intWeight = 100;
        }
        try {
            // simulate real world moving
            Thread.sleep(intWeight*1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTruckCurrentLocation(l);
        System.out.println("Truck with id: "+truckID+" is at location "+truckCurrentLocation);
        deliverPackages();
    }
    // method to start deliveries, calculating route and then coming back to the depot
    public void setOff(){
        startDelivering();
        setoff();
    }
    private void setoff() {
        long startTime=System.nanoTime();
        int packagesDelivered=CargoList.size();
        // deliver all the packages, meaning until the cargo list empties
            while (!CargoList.isEmpty()) {
                deliverPackages();
                goToLocation(getNextStop());
            }
            // then, the truck route, which will have been calculated by the solvetsp, will already be there calculated, ready to return to depot.
            System.out.println("Truck with id: "+truckID+" is returning to depot");
            while(!truckRoute.isEmpty()){
                finishedDelivering();
                goToLocation(getNextStop());
                if(truckCurrentLocation==citymap.getBase()){
                    System.out.println("Truck with id: "+truckID+" returned to depot");
                    break;
                }
            }
            double elapsedTime = (System.nanoTime() - startTime) / 1_000_000_000.0;
            try (FileWriter writer = new FileWriter("operationData.txt",true)){
                writer.write("________________________________________________\n");
                writer.write("Elapsed time for truck to set off and return: "+elapsedTime+" seconds\n");
                writer.write("Num of packages delivered: "+packagesDelivered+"\n");
                writer.write("________________________________________________\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        
    }
    // method to get the next stop of the truck, if the truck route is empty, calculate the route.
    public Location getNextStop() {
        return getnextStop();
    }
    private Location getnextStop() {
            if (!truckRoute.isEmpty()) {
                return truckRoute.pollFirst();
            } else {
                calculateRoute();
                return truckRoute.pollFirst();
            }
    }

    public boolean isonDelivery() {
        return onDeliveryjob;
    }

    public void startDelivering() {
        onDeliveryjob = true;
    }

    public void finishedDelivering() {
        onDeliveryjob = false;
    }
    // method to calculate the route of the truck, firstly we see which locations we need to go to
    // then, using dijkstra's if the number of locations> 10, we find the closest location and go there. 
    // if num of locations <10, we use TSP to find the absolute least-costly path.
    private void calculateRoute() {
        List<Location> locationsToVisit = new ArrayList<>();
        for (int i = 0; i < CargoList.size(); i++) {
            Location l = CargoList.get(i).getDeliveryLocation();
            if (!locationsToVisit.contains(l)) {
                locationsToVisit.add(l);
            }
        }
        // if locations to visit are bigger than 10, use dijkstra to find the shortest and just go there.
        if (locationsToVisit.size() > 10) {
            Map<Location, List<Location>> paths = Algorithms.dijkstra(getTruckCurrentLocation(), locationsToVisit, citymap.getCityLocations(), citymap.getCityRoads());
            reCalculateDeliveryWeightFactor(paths);
            Package chosen = nextDelivery();
            List<Location> chosenPath = paths.get(chosen.getDeliveryLocation());
            chosenPath.remove(0);
            for (Location l : chosenPath) {
                truckRoute.add(l);
            }
            // else if they are 10 or lower, use TSP to find absolute shortest path
        } else {
            List<Location> fullRoute = Algorithms.solveTSP(getTruckCurrentLocation(), locationsToVisit, citymap.getCityRoads(), citymap.getCityLocations());
            for(int i=1;i<fullRoute.size()-1;i++){
                truckRoute.add(fullRoute.get(i));
            }truckRoute.add(citymap.getBase());
        }
    }


    // calculate the delivery weight factor for each package.
    private void reCalculateDeliveryWeightFactor(Map<Location, List<Location>> paths) {
        int proximityDelta = 1;
        int priorityDelta = 2;
        for (Map.Entry<Location, List<Location>> entry : paths.entrySet()) {
            Location target = entry.getKey();
            List<Location> path = entry.getValue();
            int totalDistanceWeight = 0;
            // calculate the total weight to deliver each package
            for (int i = 0; i < path.size() - 1; i++) {
                Location u = path.get(i);
                Location v = path.get(i + 1);
                totalDistanceWeight += Algorithms.getDistance(u, v, citymap.getCityRoads());
            }
            //
            for (int i = 0; i < CargoList.size(); i++) {
                Package p = CargoList.get(i);
                if (p.getDeliveryLocation() == target) {
                    if (p.isPriority()) {
                        priorityDelta = 0;
                    } else {
                        priorityDelta = 2;
                    }
                    p.setDelWF(proximityDelta * totalDistanceWeight + priorityDelta);
                    
                }
            }
        }
    }
    // method to find the package with the lowest delivery weight factor and select that as the one to be delivered first
    private  Package nextDelivery() {
            Package p = CargoList.get(0);
            for (int i = 1; i < CargoList.size(); i++) {
                if (CargoList.get(i).getDelWF() < p.getDelWF()) {
                    p = CargoList.get(i);
                }
            }
            return p;
    }
}
