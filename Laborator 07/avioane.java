class Avion {
    private String planeID;
    private int totalEnginePower;

    public Avion(String planeID, int totalEnginePower){
        this.planeID = planeID;
        this.totalEnginePower = totalEnginePower;
    }

    public int getTotalEnginePower(){
        return totalEnginePower;
    }

    public String getPlaneID(){
        return planeID;
    }

    public void takeOff(){
        System.out.println(planeID + " - Initiating takeoff procedure- Starting engines- Accelerating down the runway- Taking off Retracting gear- Takeoff complete");
    }

    public void fly(){
        System.out.println(planeID + " - Flying");
    }

    public void land(){
        System.out.println(planeID + " - Initiating landing procedure- Enabling airbrakes Lowering gear- Contacting runway- Decelerating- Stopping engines- Landing complete");
    }

}

class Avion_Calatori extends Avion {
    private int maxPassengers;

    public Avion_Calatori (String planeID, int totalEnginePower, int maxPassengers){
        super(planeID, totalEnginePower);
        this.maxPassengers = maxPassengers;
    }

    public int getMaxPassengers(){
        return maxPassengers;
    }

}

class Boeing extends Avion_Calatori {
    public Boeing(String planeID, int totalEnginePower, int maxPassengers){
        super(planeID, totalEnginePower, maxPassengers);
    }
}

class Concorde extends Avion_Calatori {
    
    public Concorde(String planeID, int totalEnginePower, int maxPassengers){
        super(planeID, totalEnginePower, maxPassengers);
    }

    public void goSuperSonic(){
        System.out.println(getPlaneID() + " - Supersonic mode activated");
    }
    public void goSubSonic(){
        System.out.println(getPlaneID() + " - Supersonic mode deactivated");
    }
}

class Avion_Lupta extends Avion {

    public Avion_Lupta(String planeID, int totalEnginePower){
        super(planeID, totalEnginePower);
    }

    public void launchMissile(){
        System.out.println(getPlaneID() + " - Initiating missile launch procedure- Acquiring target- Launching missile- Breaking away- Missile launch complete");
    }
}


class Mig extends Avion_Lupta {

    public Mig(String planeID, int totalEnginePower){
        super(planeID, totalEnginePower);
    }

    public void highSpeedGeometry(){
        System.out.println(getPlaneID() + " - High speed geometry selected");
    }

    public void normalGeometry(){
        System.out.println(getPlaneID() + " - Normal geometry selected");
    }
}

class TomCat extends Avion_Lupta {

    public TomCat(String planeID, int totalEnginePower){
        super(planeID, totalEnginePower);
    }

    public void refuel(){
        System.out.println(getPlaneID() + "- Initiating refueling procedure- Locating refueller- Catching up - Refueling- Refueling complete");
    }
}

class Main {

    public static void main(String[] args) {
        // Creăm avioane
        Avion boeing = new Boeing("B737", 50000, 200);
        Avion concorde = new Concorde("C001", 150000, 100);
        Avion mig = new Mig("MIG29", 90000);
        Avion tomcat = new TomCat("F14", 120000);
    
        // Apelăm metodele comune
        boeing.takeOff();
        boeing.fly();
        boeing.land();
    
        concorde.takeOff();
        concorde.fly();
        ((Concorde) concorde).goSuperSonic();
        ((Concorde) concorde).goSubSonic();
        concorde.land();
    
        mig.takeOff();
        ((Mig) mig).highSpeedGeometry();
        ((Mig) mig).normalGeometry();
        mig.land();
    
        tomcat.takeOff();
        ((TomCat) tomcat).refuel();
        tomcat.land();
    }
    

}