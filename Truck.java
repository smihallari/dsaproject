import java.util.*;

class Truck {
    private int truckID;
    public boolean onDeliveryjob;
    private List<Package> CargoList;
    private float truckWeight;
    private float maxWeight;
    private Location truckCurrentLocation;
    private float truckFuel;
    private LinkedList<Location> truckRoute;
    private boolean onItsWayToRefuel;
    private District district;

    Truck(int ID, float weight, float max, District d) {
        truckID = ID;
        onDeliveryjob = false;
        truckWeight = weight;
        truckFuel = 100;
        CargoList = new ArrayList<Package>();
        maxWeight = max;
        truckRoute = new LinkedList<Location>();
        onItsWayToRefuel = false;
        district = d;
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

    
        public void addPackage(Package p) throws TruckOverWeightException {
            if (truckWeight + p.getWeight() >= maxWeight) {
                throw new TruckOverWeightException("Package can not be added to the truck, it can not store such a heavy load.");
            } else {
                truckWeight += p.getWeight();
                CargoList.add(p);
            }
        }
    
        public  void deliverPackages()   {
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

    private boolean truckNeedstoRefuel() {
        return getTruckFuel() < 30;
    }

    public void goToReFuel(List<Location> gasStations) {
        List<Location> path = Algorithms.dijkstraOnlyShortest(truckCurrentLocation, gasStations, district.getLocationsInD(), district.getRoadsInD());
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

    public  void goToLocation(Location l) throws NonExistentLocationException {
        System.out.println(l.label);
        System.out.println(getTruckCurrentLocation().label);
        int w = Algorithms.getDistance(getTruckCurrentLocation(), l, district.getRoadsInD());
        System.out.println(w);
        setTruckFuel(getTruckFuel()-w/10);
        System.out.println(getTruckFuel());
        try {
            
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTruckCurrentLocation(l);
        System.out.println("Truck with id: "+truckID+" is at location "+truckCurrentLocation);
        deliverPackages();
        
    }

    public void setOff() {
        calculateRoute();
        try {
            while (!CargoList.isEmpty()) {
                goToLocation(getNextStop());
            }
            System.out.println("Truck with id: "+truckID+"returning to depot");
            while(!truckRoute.isEmpty()){
                goToLocation(truckRoute.pollFirst());
            }
            System.out.println("Truck with id: "+truckID+" returned to depot");
        } catch (NonExistentLocationException e) {
            e.printStackTrace();
        }
    }

    public Location getNextStop() {
        if (!truckNeedstoRefuel()) {
            if (!truckRoute.isEmpty()) {
                return truckRoute.pollFirst();
            } else {
                calculateRoute();
                return truckRoute.pollFirst();
            }
        } else {
            if (onItsWayToRefuel) return truckRoute.pollFirst();
            else {
                if(truckRoute.isEmpty()){
                    calculateRoute();
                    return truckRoute.pollFirst();
                }
                // goToReFuel(new CustomList<FuelStation> = {});
                return truckRoute.pollFirst();
            }
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

    public  void calculateRoute() {
        List<Location> locationsToVisit = new ArrayList<>();
        for (int i = 0; i < CargoList.size(); i++) {
            Location l = CargoList.get(i).getDeliveryLocation();
            if (!locationsToVisit.contains(l)) {
                locationsToVisit.add(l);
            }
        }
        
        if (locationsToVisit.size() > 10) {
            Map<Location, List<Location>> paths = Algorithms.dijkstra(getTruckCurrentLocation(), locationsToVisit, district.getLocationsInD(), district.getRoadsInD());
            reCalculateDeliveryWeightFactor(paths);
            Package chosen = nextDelivery();
            truckRoute= new LinkedList<>();
            List<Location> chosenPath = paths.get(chosen.getDeliveryLocation());
            chosenPath.remove(0);
            for (Location l : chosenPath) {
                truckRoute.addFirst(l);
            }
        } else {
            List<Location> fullRoute = Algorithms.solveTSP(getTruckCurrentLocation(), locationsToVisit, district.getRoadsInD(), district.getLocationsInD());
            for (Location l : fullRoute) {
                truckRoute.addFirst(l);
            }
        }
    }

    public  void reCalculateDeliveryWeightFactor(Map<Location, List<Location>> paths) {
        int proximityDelta = 1;
        int priorityDelta = 2;
        for (Map.Entry<Location, List<Location>> entry : paths.entrySet()) {
            Location target = entry.getKey();
            List<Location> path = entry.getValue();
            int totalDistanceWeight = 0;
            for (int i = 0; i < path.size() - 1; i++) {
                Location u = path.get(i);
                Location v = path.get(i + 1);
                totalDistanceWeight += Algorithms.getDistance(u, v, district.getRoadsInD());
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

    public  void removeStop() {
        if (truckCurrentLocation == truckRoute.getFirst()) {
            truckRoute.removeFirst();
        }
    }

    public  Package nextDelivery() {
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
