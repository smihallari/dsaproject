
public class Main {
    public static void main(String[] args) {
       RoadMap citymap = new RoadMap();
       Location x01 = new Location(0, "North Ave.");
        Location x02 = new Location(1, "North Ave.");
        Location x03 = new Location(2, "North Ave.");
        Location x04 = new Location(3, "North Ave.");
        Location x05 = new Location(4, "North Ave.");
        Location x06 = new Location(5, "North Ave.");
        Location x07 = new Location(6, "North Ave.");
        Location x08 = new Location(7, "North Ave.");
        Location x09 = new Location(8, "North Ave.");
        Location x0A = new Location(9, "Main Street");
        Location x0B = new Location(10, "Main Street");
        Location x0C = new Location(11, "Main Street");
        Location x0D = new Location(12, "Main Street");
        Location x0E = new Location(13, "Main Street");
        Location x0F = new Location(14, "Main Street");
        Location x10 = new Location(15, "Main Street");
        Location x11 = new Location(16, "Main Street");
        Location x12 = new Location(17, "Main Street");
        Location x13 = new Location(18, "River Ave.");
        Location x14 = new Location(19, "River Ave.");
        Location x15 = new Location(20, "River Ave.");
        Location x16 = new Location(21, "River Ave.");
        Location x17 = new Location(22, "River Ave.");
        Location x18 = new Location(23, "River Ave.");
        Location x19 = new Location(24, "River Ave.");
        Location x1A = new Location(25, "River Ave.");
        Location x1B = new Location(26, "River Ave.");
        Location x1C = new Location(27, "River Ave.");
        Location x1D = new Location(28, "River Ave.");
        Location x1E = new Location(29, "River Ave.");
        Location x1F = new Location(30, "River Ave.");
        Location x20 = new Location(31, "South Rd.");
        Location x21 = new Location(32, "South Rd.");
        Location x22 = new Location(33, "South Rd.");
        Location x23 = new Location(34, "South Rd.");
        Location x24 = new Location(35, "South Rd.");
        Location x25 = new Location(36, "South Rd.");
        Location x26 = new Location(37, "South Rd.");
        Location x27 = new Location(38, "South Rd.");
        Location x28 = new Location(39, "South Rd.");
        Location x29 = new Location(40, "South Rd.");
        Location x2A = new Location(41, "West Str.");
        Location x2B = new Location(42, "West Str.");
        Location x2C = new Location(43, "West Str.");
        Location x2D = new Location(44, "West Str.");
        Location x2E = new Location(45, "West Str.");
        Location x2F = new Location(46, "Unix Rd.");
        Location x30 = new Location(47, "Unix Rd.");
        Location x31 = new Location(48, "Unix Rd.");
        Location x32 = new Location(49, "Unix Rd.");
        Location x33 = new Location(50, "Unix Rd.");
        Location x34 = new Location(51, "Von Neumann Rd.");
        Location x35 = new Location(52, "Von Neumann Rd.");
        Location x36 = new Location(53, "Von Neumann Rd.");
        Location x37 = new Location(54, "Von Neumann Rd.");
        Location x38 = new Location(55, "Von Neumann Rd.");
        Location x39 = new Location(56, "Stallman Rd.");
        Location x3A = new Location(57, "Stallman Rd.");
        Location x3B = new Location(58, "Stallman Rd.");
        Location x3C = new Location(59, "Stallman Rd.");
        Location x3D = new Location(60, "Stallman Rd.");
        Location x3E = new Location(61, "Stallman Rd.");
        Location x3F = new Location(62, "Torvalds Str.");
        Location x40 = new Location(63, "Torvalds Str.");
        Location x41 = new Location(64, "Torvalds Str.");
        Location x42 = new Location(65, "Torvalds Str.");
        Location x43 = new Location(66, "Torvalds Str.");
        Location x44 = new Location(67, "Torvalds Str.");
        Location x45 = new Location(68, "Torvalds Str.");
        Location x46 = new Location(69, "Gosling Str.");
        Location x47 = new Location(70, "Gosling Str.");
        Location x48 = new Location(71, "Gosling Str.");
        Location x49 = new Location(72, "Gosling Str.");
        Location x4A = new Location(73, "Gosling Str.");
        Location x4B = new Location(74, "Dijkstra Cres.");
        Location x4C = new Location(75, "Dijkstra Cres.");
        Location x4D = new Location(76, "Dijkstra Cres.");
        Location x4E = new Location(77, "East Str.");
        Location x4F = new Location(78, "East Str.");
        Location x50 = new Location(79, "East Str.");
        Location x51 = new Location(80, "East Str.");      
        Location x52 = new Location(81, "Berners-Lee Str.");
        Location x53 = new Location(82, "Berners-Lee Str.");
        Location x54 = new Location(83, "Turing Rd.");
        Location x55 = new Location(84, "Turing Rd.");
        Location x56 = new Location(85, "Turing Rd.");
        Location x57 = new Location(86, "Turing Rd.");
        Location x58 = new Location(87, "Hopper Rd.");
        Location x59 = new Location(88, "Gates Str.");
        Location x5A = new Location(89, "Gates Str.");
        Location x5B = new Location(90, "Tannenbaum Str.");
        Location x5C = new Location(91, "Tannenbaum Str.");
        Location x5D = new Location(92, "Tannenbaum Str.");
        Location x5E = new Location(93, "Tannenbaum Str.");
        Location x5F = new Location(94, "Tannenbaum Str.");
        Location x60 = new Location(95, "Apple Cres.");
        Location x61 = new Location(96, "Apple Cres.");
        Location x62 = new Location(97, "Newell Str.");
        Location x63 = new Location(98, "Newell Str.");
        Location x64 = new Location(99, "Newell Str.");
        Location x65 = new Location(100, "Google Dv.");
       District west = new District("Westside");
       west.includeLocations(
           x01, x02, x03, x04, x05, x0A, x0B, x0C, x0D, x0E, 
           x0F, x10, x11, x12, x2A, x2B, x2C, x2D, x20, x1F, 
           x2E, x58, x39, x59, x4A, x5B, x49, x48, x45, x1B, 
           x36, x47, x22, x46, x3E, x3D, x3C, x3B, x3A, x60, 
           x61, x62, x1E, x1D, x1C, x5A, x44, x37, x21, x38
       );
       
       District east = new District("Eastside");
       east.includeLocations(
           x06, x07, x08, x09, x13, x14, x15, x16, x17, x18, 
           x19, x1A, x23, x24, x25, x26, x27, x28, x29, x4B, 
           x4C, x4D, x3F, x40, x41, x42, x43, x2F, x30, x31, 
           x32, x33, x34, x35, x4E, x4F, x50, x51, x52, x53, 
           x54, x63, x64, x55, x56, x57, x5C, x5D, x5E, x5F, x65
       );
        citymap.addConnection(x01, x65);
        citymap.addConnection(x01, x02);
        citymap.addConnection(x02, x01);
        citymap.addConnection(x02, x03);
        citymap.addConnection(x03, x02);
        citymap.addConnection(x03, x04);
        citymap.addConnection(x04, x03);
        citymap.addConnection(x04, x05);
        citymap.addConnection(x05, x04);
        citymap.addConnection(x05, x0A);
        citymap.addConnection(x0A, x05);
        citymap.addConnection(x0A, x0B);
        citymap.addConnection(x0B, x0A);
        citymap.addConnection(x0B, x0C);
        citymap.addConnection(x0C, x0B);
        citymap.addConnection(x0C, x0D);
        citymap.addConnection(x0D, x0C);
        citymap.addConnection(x0D, x0E);
        citymap.addConnection(x0E, x0D);
        citymap.addConnection(x0E, x0F);
        citymap.addConnection(x0F, x0E);
        citymap.addConnection(x0F, x10);
        citymap.addConnection(x10, x0F);
        citymap.addConnection(x10, x11);
        citymap.addConnection(x11, x10);
        citymap.addConnection(x11, x12);
        citymap.addConnection(x12, x11);
        citymap.addConnection(x01, x2A);
        citymap.addConnection(x2A, x2B);
        citymap.addConnection(x2B, x2C);
        citymap.addConnection(x2C, x2D);
        citymap.addConnection(x2D, x20);
        citymap.addConnection(x20, x1F);
        citymap.addConnection(x1F, x2E);
        citymap.addConnection(x2E, x1F);
        citymap.addConnection(x2E, x46);
        citymap.addConnection(x46, x22);
        citymap.addConnection(x22, x47);
        citymap.addConnection(x47, x36);
        citymap.addConnection(x36, x1B);
        citymap.addConnection(x1B, x45);
        citymap.addConnection(x45, x48);
        citymap.addConnection(x48, x49);
        citymap.addConnection(x49, x4A);
        citymap.addConnection(x4A, x04);
        citymap.addConnection(x0F, x36);
        citymap.addConnection(x36, x37);
        citymap.addConnection(x37, x21);
        citymap.addConnection(x21, x38);
        citymap.addConnection(x38, x2E);
        citymap.addConnection(x1F, x1E);
        citymap.addConnection(x1E, x1F);
        citymap.addConnection(x1E, x1D);
        citymap.addConnection(x1D, x1E);
        citymap.addConnection(x1D, x1C);
        citymap.addConnection(x1C, x1D);
        citymap.addConnection(x1C, x1B);
        citymap.addConnection(x1B, x1C);
        citymap.addConnection(x1B, x0E);
        citymap.addConnection(x0E, x1B);
        citymap.addConnection(x20, x1E);
        citymap.addConnection(x1E, x21);
        citymap.addConnection(x21, x22);
        citymap.addConnection(x22, x11);
        citymap.addConnection(x11, x22);
        citymap.addConnection(x2D, x3E);
        citymap.addConnection(x3E, x1D);
        citymap.addConnection(x1D, x37);
        citymap.addConnection(x37, x47);
        citymap.addConnection(x47, x10);
        citymap.addConnection(x03, x39);
        citymap.addConnection(x39, x3A);
        citymap.addConnection(x3A, x3B);
        citymap.addConnection(x3B, x3C);
        citymap.addConnection(x3C, x3D);
        citymap.addConnection(x3D, x3E);
        citymap.addConnection(x3E, x20);
        citymap.addConnection(x2C, x61);
        citymap.addConnection(x61, x3B);
        citymap.addConnection(x3B, x48);
        citymap.addConnection(x48, x62);
        citymap.addConnection(x62, x0C);
        citymap.addConnection(x0D, x44);
        citymap.addConnection(x44, x45);
        citymap.addConnection(x45, x3C);
        citymap.addConnection(x3C, x2C);
        citymap.addConnection(x2A, x58);
        citymap.addConnection(x58, x39);
        citymap.addConnection(x3A, x60);
        citymap.addConnection(x60, x2B);
        citymap.addConnection(x02, x58);
        citymap.addConnection(x58, x02);
        citymap.addConnection(x58, x60);
        citymap.addConnection(x60, x58);
        citymap.addConnection(x60, x61);
        citymap.addConnection(x61, x60);
        citymap.addConnection(x4A, x5B);
        citymap.addConnection(x5B, x0A);
        citymap.addConnection(x5A, x49);
        citymap.addConnection(x5A, x0B);
        citymap.addConnection(x5B, x5A);
        citymap.addConnection(x49, x5A);
        citymap.addConnection(x62, x5A);
        citymap.addConnection(x49, x59);
        citymap.addConnection(x59, x49);
        citymap.addConnection(x59, x39);
        citymap.addConnection(x39, x59);
        citymap.addConnection(x59, x3A);
        citymap.addConnection(x3A, x59);
        citymap.addConnection(x59, x4A);
        citymap.addConnection(x4A, x59);
        //WEST node touching EAST node
        citymap.addConnection(x05, x06);
        citymap.addConnection(x06, x05);
        citymap.addConnection(x0A, x2F);
        citymap.addConnection(x0B, x30);
        citymap.addConnection(x30, x0B);
        citymap.addConnection(x0C, x63);
        citymap.addConnection(x43, x0D);
        citymap.addConnection(x0E, x1A);
        citymap.addConnection(x1A, x0E);
        citymap.addConnection(x35, x0F);
        citymap.addConnection(x10, x4B);
        citymap.addConnection(x11, x23);
        citymap.addConnection(x23, x11);
        //EAST side ONLY
        citymap.addConnection(x06, x07);
        citymap.addConnection(x07, x06);
        citymap.addConnection(x07, x08);
        citymap.addConnection(x08, x07);
        citymap.addConnection(x08, x09);
        citymap.addConnection(x09, x08);
        citymap.addConnection(x1A, x19);
        citymap.addConnection(x19, x1A);
        citymap.addConnection(x19, x18);
        citymap.addConnection(x18, x19);
        citymap.addConnection(x18, x17);
        citymap.addConnection(x17, x18);
        citymap.addConnection(x17, x16);
        citymap.addConnection(x16, x17);
        citymap.addConnection(x16, x15);
        citymap.addConnection(x15, x16);
        citymap.addConnection(x15, x14);
        citymap.addConnection(x14, x15);
        citymap.addConnection(x14, x13);
        citymap.addConnection(x13, x14);
        citymap.addConnection(x24, x25);
        citymap.addConnection(x25, x24);
        citymap.addConnection(x25, x26);
        citymap.addConnection(x26, x25);
        citymap.addConnection(x26, x27);
        citymap.addConnection(x27, x26);
        citymap.addConnection(x27, x28);
        citymap.addConnection(x28, x27);
        citymap.addConnection(x28, x29);
        citymap.addConnection(x29, x28);
        citymap.addConnection(x06, x2F);
        citymap.addConnection(x2F, x06);
        citymap.addConnection(x2F, x30);
        citymap.addConnection(x30, x2F);
        citymap.addConnection(x30, x31);
        citymap.addConnection(x31, x30);
        citymap.addConnection(x07, x52);
        citymap.addConnection(x52, x07);
        citymap.addConnection(x52, x53);
        citymap.addConnection(x53, x52);
        citymap.addConnection(x53, x56);
        citymap.addConnection(x56, x53);
        citymap.addConnection(x4B, x4C);
        citymap.addConnection(x4C, x4D);
        citymap.addConnection(x4D, x16);
        citymap.addConnection(x23, x24);
        citymap.addConnection(x24, x4C);
        citymap.addConnection(x4C, x54);
        citymap.addConnection(x54, x18);
        citymap.addConnection(x18, x42);
        citymap.addConnection(x42, x55);
        citymap.addConnection(x55, x56);
        citymap.addConnection(x56, x57);
        citymap.addConnection(x57, x4E);
        citymap.addConnection(x09, x4E);
        citymap.addConnection(x4E, x4F);
        citymap.addConnection(x4F, x50);
        citymap.addConnection(x50, x51);
        citymap.addConnection(x51, x40);
        citymap.addConnection(x40, x16);
        citymap.addConnection(x16, x27);
        citymap.addConnection(x56, x50);
        citymap.addConnection(x50, x13);
        citymap.addConnection(x2F, x5C);
        citymap.addConnection(x5C, x5D);
        citymap.addConnection(x5D, x5E);
        citymap.addConnection(x5E, x55);
        citymap.addConnection(x55, x5F);
        citymap.addConnection(x5F, x51);
        citymap.addConnection(x51, x14);
        citymap.addConnection(x08, x65);
        citymap.addConnection(x65, x57);
        citymap.addConnection(x14, x3F);
        citymap.addConnection(x3F, x28);
        citymap.addConnection(x63, x31);
        citymap.addConnection(x31, x5C);
        citymap.addConnection(x5C, x52);
        citymap.addConnection(x52, x08);
        citymap.addConnection(x31, x32);
        citymap.addConnection(x5D, x64);
        citymap.addConnection(x63, x32);
        citymap.addConnection(x32, x64);
        citymap.addConnection(x64, x55);
        citymap.addConnection(x63, x43);
        citymap.addConnection(x43, x1A);
        citymap.addConnection(x1A, x34);
        citymap.addConnection(x34, x54);
        citymap.addConnection(x54, x4D);
        citymap.addConnection(x4D, x26);
        citymap.addConnection(x3F, x15);
        citymap.addConnection(x15, x40);
        citymap.addConnection(x40, x41);
        citymap.addConnection(x41, x42);
        citymap.addConnection(x42, x33);
        citymap.addConnection(x33, x43);
        citymap.addConnection(x19, x34);
        citymap.addConnection(x34, x35);
        citymap.addConnection(x32, x33);
        citymap.addConnection(x33, x34);
        citymap.addConnection(x35, x4B);
        citymap.addConnection(x4B, x23);
        citymap.addGasStations(x3C,x21,x42,x56,x2F,x39,x15);
        citymap.randomizeTraffic();

        Depot depot = new Depot(citymap);
        depot.addTruckToGarage(1);
        depot.fillPackages(400);
        depot.fillTruckWithPackages(1,east);
        depot.setCityBase(x30);
        depot.addTruckToGarage(2);
        depot.fillTruckWithPackages(2,east);
        depot.setTruckOff(1);
        Thread thread1 = new Thread(() -> depot.setTruckOff(1));
        Thread thread2 = new Thread(() -> depot.setTruckOff(2));

        thread1.start();
        thread2.start();
    }

    
}
