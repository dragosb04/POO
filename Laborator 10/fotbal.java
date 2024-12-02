import java.util.Random;
import java.util.Date;

class CoordinateGenerator {
    private Random randomGenerator;

    public CoordinateGenerator() {
        Date now = new Date();
        long sec = now.getTime();
        randomGenerator = new Random(sec);
    }

    public int generateX() {
        int x = randomGenerator.nextInt(101);
        if (x < 5) {
            x = 0;
        } else if (x > 95) {
            x = 100;
        } else {
            x = randomGenerator.nextInt(99) + 1;
        }
        return x;
    }

    public int generateY() {
        int y = randomGenerator.nextInt(101);
        if (y < 5) {
            y = 0;
        } else if (y > 95) {
            y = 50;
        } else {
            y = randomGenerator.nextInt(49) + 1;
        }
        return y;
    }
}

class Gol extends Exception {
    public Gol(String mesaj) {
        super(mesaj);
    }
}

class Out extends Exception {
    public Out(String mesaj) {
        super(mesaj);
    }
}

class Corner extends Exception {
    public Corner(String mesaj) {
        super(mesaj);
    }
}

class Minge {
    private int x, y;

    public Minge(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void suteaza() throws Gol, Out, Corner{
        CoordinateGenerator coordonate = new CoordinateGenerator();
        x = coordonate.generateX();
        y = coordonate.generateY();

        if (y == 0 || y == 50)
            throw new Out("OUT!");
        
        if ((x == 0 || x == 100) && (y >= 20 && y <= 30))
            throw new Gol("GOL!");

        if (x == 0 || x == 100 && !((0 < y && y < 20) ||(30 < y && y < 50)))
            throw new Corner("CORNER!");
    }

}

class Joc {
    private String echipa1, echipa2;
    private int goluriEchipa1 = 0, goluriEchipa2 = 0;
    private int out = 0, corner = 0;

    public Joc (String echipa1, String echipa2){
        this.echipa1 = echipa1;
        this.echipa2 = echipa2;
    }

    public void simuleaza() {
        Minge minge = new Minge(20, 50);
        for (int i = 0; i < 1000; i++){
            try {
                minge.suteaza();
            }
            catch (Gol g) {
                System.out.println(g.getMessage());
                if (minge.getX() == 0) {
                    goluriEchipa2++;
                } else {
                    goluriEchipa1++;
                }
                minge = new Minge(20, 50);
            }
            catch (Corner c){
                System.out.println(c.getMessage());
                corner++;
                    if (minge.getX() <= 0)
                        minge = new Minge(0, minge.getY());
                    else
                        minge = new Minge(100, minge.getY());
                    
                    if (minge.getY() <= 0)
                        minge = new Minge(minge.getX(), 0);
                    else
                        minge = new Minge(minge.getX(), 50);
            }
            catch (Out o){
                System.out.println(o.getMessage());
                out++;
            }
            System.out.println(echipa1 + " - " + echipa2 + " Minge la (" + minge.getX() + ", " + minge.getY() + ").");
        } 
        
    }
    
    public String toString() {
        return "\nScor: " + echipa1 + " " + goluriEchipa1 + " - " + echipa2 + " " + goluriEchipa2 + "\nOut-uri: " + out + "\nCornere: " + corner;
    }
}

class Main {
    public static void main(String[] args) {
        Joc joc1 = new Joc("A", "B");
        Joc joc2 = new Joc("C", "D");

        System.out.println("Simulare 1:");
        joc1.simuleaza();
        System.out.println(joc1);

        System.out.println("\nSimulare 2:");
        joc2.simuleaza();
        System.out.println(joc2);
    }
}