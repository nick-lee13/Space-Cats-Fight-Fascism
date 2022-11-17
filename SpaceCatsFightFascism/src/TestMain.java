public class TestMain {
    public static void main(String[] args) {
        Planet p1 = new Planet(1,1);
        Planet p2 = new Planet(2,0);
        Planet p3 = new Planet(3,0);
        Planet p4 = new Planet(4,0);
        Planet p5 = new Planet(5,0);
        Planet p6 = new Planet(6,0);
        Planet p7 = new Planet(7,9);
        Planet p8 = new Planet(8,8);
        Planet p9 = new Planet(9,0);
        Planet p10 = new Planet(10,0);
        Planet p11 = new Planet(11,6);
        Planet p12 = new Planet(12,4);
        
        Planet[][] planets = {{p1,p2,p3,p4},{p5,p6,p7,p8},{p9,p10,p11,p12}};
        System.out.println(planets[2][3].getTokens());


        DisplayUpdater disp = new DisplayUpdater();
        disp.initDisplay(args);
    }
}
