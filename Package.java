public class Package{

    private int packageID;
    private boolean priority;
    private int weight;
    private float deliveryWeightFactor;
    private Location deliveryLocation;



    public Package(int ID, boolean p, int pweight, Location l, float delFactor ){
        deliveryLocation = l;
        packageID = ID;
        priority = p;
        weight = pweight;
        deliveryWeightFactor = delFactor;
    }

    public boolean isPriority(){
        return priority;
    }

    public int getWeight(){
        return weight;
    }
    public int getPackageID(){
        return packageID;
    }
    public Location getDeliveryLocation(){
        return deliveryLocation;
    }
    public float getDelWF(){
        return deliveryWeightFactor;
    }
    public void setDelWF(float newDELWF){
        deliveryWeightFactor = newDELWF;
    }
}