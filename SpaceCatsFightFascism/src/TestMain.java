public class TestMain {
    public static void main(String[] args) {
        Planet p1 = new Planet(1,0);
        Planet p2 = new Planet(1,0);
        Planet p3 = new Planet(1,0);
        Planet p4 = new Planet(1,0);
        Planet p5 = new Planet(1,0);
        Planet p6 = new Planet(1,0);
        Planet p7 = new Planet(1,0);
        Planet p8 = new Planet(1,0);
        Planet p9 = new Planet(1,0);
        Planet p10 = new Planet(1,0);
        Planet p11 = new Planet(1,0);
        Planet p12 = new Planet(1,0);
        Planet[][] planets = {{p1,p2,p3,p4},{p5,p6,p7,p8},{p9,p10,p11,p12}};


        DisplayUpdater disp = new DisplayUpdater();
        disp.setPlanetLayout(planets);
        disp.initDisplay(args);
    }
}
