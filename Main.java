public class Main {

    public static void main(String[] args) {
       CustomGraph cg = new CustomGraph();
        Location v0 = new Location(0, "A");
        Location v1 = new Location(1, "B");
        Location v2 = new Location(2, "C");
        Location v3 = new Location(3, "D");
        Location v4 = new Location(4, "E");
        Location v5 = new Location(5, "F");
        Location v6 = new Location(6, "G");
        Location v7 = new Location(7, "H");
        Location v8 = new Location(8, "I");
        Location v9 = new Location(9, "J");
        Location v10 = new Location(10, "K");
        Location v11 = new Location(11, "L");
        Location v12 = new Location(12, "M");
        Location v13 = new Location(13, "N");
        Location v14 = new Location(14, "O");
        Location v15 = new Location(15, "P");
        Location v16 = new Location(16, "Q");
        Location v17 = new Location(17, "R");
        Location v18 = new Location(18, "S");
        Location v19 = new Location(19, "T");
        Location v20 = new Location(20, "U");
        Location v21 = new Location(21, "V");
        Location v22 = new Location(22, "W");
        Location v23 = new Location(23, "X");
        Location v24 = new Location(24, "Y");

        cg.addConnection(v0, v1, 10);
        cg.addConnection(v0, v2, 15);
        cg.addConnection(v0, v3, 20);
        cg.addConnection(v0, v4, 25);
        cg.addConnection(v1, v5, 30);
        cg.addConnection(v1, v6, 35);
        cg.addConnection(v2, v7, 40);
        cg.addConnection(v2, v8, 45);
        cg.addConnection(v3, v9, 50);
        cg.addConnection(v3, v10, 55);
        cg.addConnection(v4, v11, 60);
        cg.addConnection(v4, v12, 65);
        cg.addConnection(v5, v13, 70);
        cg.addConnection(v5, v14, 75);
        cg.addConnection(v6, v15, 80);
        cg.addConnection(v6, v16, 85);
        cg.addConnection(v7, v17, 90);
        cg.addConnection(v7, v18, 95);
        cg.addConnection(v8, v19, 100);
        cg.addConnection(v8, v20, 105);
        cg.addConnection(v9, v21, 110);
        cg.addConnection(v9, v22, 115);
        cg.addConnection(v10, v23, 120);
        cg.addConnection(v10, v24, 125);
        cg.addConnection(v11, v0, 130);
        cg.addConnection(v12, v1, 135);
        cg.addConnection(v13, v2, 140);
        cg.addConnection(v14, v3, 145);
        cg.addConnection(v15, v4, 150);
        cg.addConnection(v16, v5, 155);
        cg.addConnection(v17, v6, 160);
        cg.addConnection(v18, v7, 165);
        cg.addConnection(v19, v8, 170);
        cg.addConnection(v20, v9, 175);
        cg.addConnection(v21, v10, 180);
        cg.addConnection(v22, v11, 185);
        cg.addConnection(v23, v12, 190);
        cg.addConnection(v24, v13, 195);
        
        Depot depot = new Depot(cg);
        District eastside = new District("pew");
        eastside.includeLocations(v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15,v16,v17,v18,v19,v20,v22,v21,v23,v24);
        eastside.includeConnection(
    new Connection(v0, v1, 10), new Connection(v0, v2, 15), 
    new Connection(v0, v3, 20), new Connection(v0, v4, 25),
    new Connection(v1, v5, 30), new Connection(v1, v6, 35),
    new Connection(v2, v7, 40), new Connection(v2, v8, 45),
    new Connection(v3, v9, 50), new Connection(v3, v10, 55),
    new Connection(v4, v11, 60), new Connection(v4, v12, 65),
    new Connection(v5, v13, 70), new Connection(v5, v14, 75),
    new Connection(v6, v15, 80), new Connection(v6, v16, 85),
    new Connection(v7, v17, 90), new Connection(v7, v18, 95),
    new Connection(v8, v19, 100), new Connection(v8, v20, 105),
    new Connection(v9, v21, 110), new Connection(v9, v22, 115),
    new Connection(v10, v23, 120), new Connection(v10, v24, 125),
    new Connection(v11, v0, 130), new Connection(v12, v1, 135),
    new Connection(v13, v2, 140), new Connection(v14, v3, 145),
    new Connection(v15, v4, 150), new Connection(v16, v5, 155),
    new Connection(v17, v6, 160), new Connection(v18, v7, 165),
    new Connection(v19, v8, 170), new Connection(v20, v9, 175),
    new Connection(v21, v10, 180), new Connection(v22, v11, 185),
    new Connection(v23, v12, 190), new Connection(v24, v13, 195)
);

        depot.addTruckToGarage(1, eastside);
        depot.setTruckOff(1);
    }

    
}
