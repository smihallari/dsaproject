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

    private float truckFuel;
    // the current route of the truck, containing the locations of where it is going in succession.
    private LinkedList<Location> truckRoute;
    private boolean onItsWayToRefuel;
    // the district the truck belongs to
    private District district;
    // the city the truck belongs to
    private RoadMap citymap;

    Truck(int ID, float weight, float max, RoadMap city) {
        truckID = ID;
        onDeliveryjob = false;
        truckWeight = weight;
        truckFuel = 1000;
        CargoList = new ArrayList<Package>();
        maxWeight = max;
        truckRoute = new LinkedList<Location>();
        onItsWayToRefuel = false;
        citymap = city;

    }
    // Getter and Setter for truckID
    public int getTruckID() {
        return truckID;
    }

    public void setTruckID(int truckID) {
        this.truckID = truckID;
    }

    // Getter and Setter for onDeliveryjob
    public boolean isOnDeliveryJob() {
        return onDeliveryjob;
    }

    public void setOnDeliveryJob(boolean onDeliveryjob) {
        this.onDeliveryjob = onDeliveryjob;
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

    // Getter and Setter for truckFuel
    public float getTruckFuel() {
        return truckFuel;
    }

    public void setTruckFuel(float truckFuel) {
        this.truckFuel = truckFuel;
    }

    // Getter and Setter for truckRoute
    public LinkedList<Location> getTruckRoute() {
        return truckRoute;
    }

    public void setTruckRoute(LinkedList<Location> truckRoute) {
        this.truckRoute = truckRoute;
    }

    // Getter and Setter for onItsWayToRefuel
    public boolean isOnItsWayToRefuel() {
        return onItsWayToRefuel;
    }

    public void setOnItsWayToRefuel(boolean onItsWayToRefuel) {
        this.onItsWayToRefuel = onItsWayToRefuel;
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
        private  void deliverpackages()   {
            if (!getCargoList().isEmpty()) {
                for (int i = 0; i < getCargoList().size(); i++) {
                    Package p = getCargoList().get(i);
                    if (p.getDeliveryLocation() == getTruckCurrentLocation()) {
                        System.out.println("Successfully delivered package with ID: " + p.getPackageID() + " to location: " + getTruckCurrentLocation());
                        CargoList.remove(p);
                        i--;
                        setTruckWeight(getTruckWeight()-p.getWeight());
                    }
                }
            }
        }

    // private boolean truckNeedstoRefuel() {
    //     return getTruckFuel() < 30;
    // }
    
    public void goToReFuel(List<Location> gasStations) {

        List<Location> path = Algorithms.dijkstraOnlyShortest(truckCurrentLocation, gasStations, citymap.getCityLocations(), citymap.getCityRoads());
        truckRoute = new LinkedList<Location>();
        for (Location l : path) {
            truckRoute.addFirst(l);
        }
        onItsWayToRefuel = true;
    }

    public  void reFuel() {
        System.out.println("Paid 100$ to refuel! Fuel tank is now at " + getTruckFuel() + "%!");
        setTruckFuel(100); 
        double moneypaid = (Math.random() * 25 + 50);
        System.out.println("Actually paid " + moneypaid + "$ and scammed my boss for the rest (" + (100 - moneypaid) + "$).");
        onItsWayToRefuel = false;
        getNextStop();
    }
    //method to move the truck to a certain location in the map
    public  void goToLocation(Location l)  {
        gotoLocation(l);
    }
    private  void gotoLocation(Location l)  {
        if(l==truckCurrentLocation){System.out.println("at this location");}
        float w = (Algorithms.getDistance(getTruckCurrentLocation(), l, citymap.getCityRoads()))/100;
        System.out.println("weight"+w);
        truckFuel-=w;

        System.out.println(getTruckFuel());
        System.out.println(getTruckFuel());
        try {
            
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTruckCurrentLocation(l);
        System.out.println("Truck with id: "+truckID+" is at location "+truckCurrentLocation);
        deliverPackages();
        
    }
    // method to start deliveries, calculating route and then coming back to the depot
    public void setOff(){
        setoff();
    }
    private void setoff() {
        long startTime=System.nanoTime();
        int packagesDelivered=CargoList.size();
        calculateRoute();
        System.out.println(CargoList.size());
            System.out.println(CargoList.size());
            while (!CargoList.isEmpty()) {
                goToLocation(getNextStop());
            }
            System.out.println("Truck with id: "+truckID+" is returning to depot");
            while(!truckRoute.isEmpty()){
                goToLocation(truckRoute.pollFirst());
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

    private Location getNextStop() {
        // if (!truckNeedstoRefuel()) {
            if (!truckRoute.isEmpty()) {
                return truckRoute.pollFirst();
            } else {
                calculateRoute();
                return truckRoute.pollFirst();
            }
        // } else {
        //     if (onItsWayToRefuel) return truckRoute.pollFirst();
        //     else {
        //         if(truckRoute.isEmpty()){
        //             calculateRoute();
        //             return truckRoute.pollFirst();
        //         }
        //         goToReFuel(citymap.getGasStations());
        //         return truckRoute.pollFirst();
        //     }
        // }
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
            System.out.println(chosen.getPackageID());
            truckRoute = new LinkedList<>();
            List<Location> chosenPath = paths.get(chosen.getDeliveryLocation());
            for(Location l: chosenPath)
            {
                System.out.println("insdieforlipp"+l.id);
            }
            System.out.println(chosenPath.size());
            chosenPath.remove(0);
            for (Location l : chosenPath) {
                truckRoute.add(l);
            }
            // else if they are 10 or lower, use TSP to find absolute shortest path
        } else {
            List<Location> fullRoute = Algorithms.solveTSP(getTruckCurrentLocation(), locationsToVisit, citymap.getCityRoads(), citymap.getCityLocations());
            for (Location l : fullRoute) {
                truckRoute.addFirst(l);
            }
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
            for (int i = 0; i < path.size() - 1; i++) {
                Location u = path.get(i);
                Location v = path.get(i + 1);
                totalDistanceWeight += Algorithms.getDistance(u, v, citymap.getCityRoads());
            }
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
        if (!CargoList.isEmpty()) {
            Package p = CargoList.get(0);
            for (int i = 1; i < CargoList.size(); i++) {
                if (CargoList.get(i).getDelWF() < p.getDelWF()) {
                    p = CargoList.get(i);
                }
            }
            return p;
        }
        return null;
    }
}
