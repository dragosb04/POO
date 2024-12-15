import java.util.ArrayList;

abstract class Whiskey {
    abstract public double getNrCalorii (double mililitri);
    abstract public double getConcentratieAlcool();
}

class ClassicWhiskey extends Whiskey {
    private String nume;
    private double concentratie;

    public ClassicWhiskey(String nume, double concentratie) {
        this.nume = nume;
        this.concentratie = concentratie;
    }

    public ClassicWhiskey(double concentratie, String nume) {
        this.nume = nume;
        this.concentratie = concentratie;
    }

    public double getNrCalorii(double mililitri) {
        return concentratie * mililitri * 5;
    }

    public double getConcentratieAlcool() {
        return concentratie;
    }

    public String toString() {
        return nume + ", Concentratie alcool: " + concentratie + "%, Calorii pe 100 ml: " + getNrCalorii(100);
    }
}

class JackAndHoney extends Whiskey {
    private String nume;
    private double concentratie;
    private int indulcitor;

    public JackAndHoney(String nume, double concentratie, int indulcitor) {
        this.nume = nume;
        this.concentratie = concentratie;
        this.indulcitor = indulcitor;
    }

    public JackAndHoney(double concentratie, String nume, int indulcitor) {
        this.nume = nume;
        this.concentratie = concentratie;
        this.indulcitor = indulcitor;        
    }

    public double getNrCalorii(double mililitri) {
        return concentratie * mililitri * 5 + indulcitor * mililitri * 15;
    }

    public double getConcentratieAlcool() {
        return concentratie;
    }

    public String toString() {
        return nume + ", Concentratie alcool: " + concentratie + "%, Calorii pe 100 ml: " + getNrCalorii(100) + " Cantitate indulcitor: " + indulcitor + " g";
    }
}

class BlendedWhiskey extends Whiskey {
    private String nume;
    private ArrayList<Whiskey> whiskey = new ArrayList<Whiskey>();
    private ArrayList<Double> cantitati = new ArrayList<>();

    public BlendedWhiskey (String nume){
        this.nume = nume;
    }

    public void adaugaBautura(Whiskey bautura, double cantitate) {
        whiskey.add(bautura);
        cantitati.add(cantitate);
    }

    public double getConcentratieAlcool() {
        double sumaConcentratie = 0.0;
        double sumaCantitati = 0.0;
        for (int i = 0; i < whiskey.size(); i++) {
            sumaConcentratie += whiskey.get(i).getConcentratieAlcool() * cantitati.get(i);
            sumaCantitati += cantitati.get(i);
        }
        return sumaConcentratie / sumaCantitati;
    }


    public double getNrCalorii(double mililitri) {
        double totalCalorii = 0.0;
        double sumaCantitati = 0.0;

        for (int i = 0; i < whiskey.size(); i++) {
            totalCalorii += whiskey.get(i).getNrCalorii(mililitri * cantitati.get(i));
            sumaCantitati += cantitati.get(i);
        }

        return totalCalorii / sumaCantitati;
    }

    public String toString() {
        String result = nume + ", Concentratie alcool: " + getConcentratieAlcool() 
                        + "%, Calorii pe 100 ml: " + getNrCalorii(100) + "\nCompozitie:";
        for (int i = 0; i < whiskey.size(); i++) {
            result += "\n   " + whiskey.get(i).toString() + " (Cantitate: " + cantitati.get(i) + " ml)";
        }
        return result;
    }
}

class Main {
    public static void main (String[] args){
        ClassicWhiskey classicWhiskey = new ClassicWhiskey("Classic Whiskey", 40.0);
        JackAndHoney jackAndHoney = new JackAndHoney("Jack and Honey", 35.0, 2);
        BlendedWhiskey blendedWhiskey = new BlendedWhiskey("Blended Whiskey");
        blendedWhiskey.adaugaBautura(classicWhiskey, 1.0);
        blendedWhiskey.adaugaBautura(jackAndHoney, 1.0);
    
        BlendedWhiskey finalBlended = new BlendedWhiskey("Final Blended Whiskey");
        finalBlended.adaugaBautura(blendedWhiskey, 2.0);
        finalBlended.adaugaBautura(classicWhiskey, 1.0);
        finalBlended.adaugaBautura(jackAndHoney, 1.0);
    
        System.out.println("Calorii pentru 150 ml din Final Blended Whiskey: " + finalBlended.getNrCalorii(150));
        System.out.println(finalBlended);
    }
}
