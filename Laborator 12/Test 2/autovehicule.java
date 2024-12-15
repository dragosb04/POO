import java.util.ArrayList;

abstract class Autovehicule {
    abstract public double calculeazaGreautateTotala();
}

class Autoturism extends Autovehicule {
    private String nrInmatriculare;
    private double greutateAuto, greutatePasageri;

    public Autoturism(String nrInmatriculare, double greutateAuto, double greutatePasageri) {
        this.nrInmatriculare = nrInmatriculare;
        this.greutatePasageri = greutatePasageri;
        this.greutateAuto = greutateAuto;
    }

    public double calculeazaGreautateTotala() {
        return greutateAuto + greutatePasageri;
    }

    public String toString() {
        return "Numar inmatriculare: " + nrInmatriculare + ", Greutate autovehicul: " + greutateAuto + ", Greutate pasageri: " + greutatePasageri;
    }
}

class Camion extends Autovehicule {
    private String nrInmatriculare;
    private double greutateAuto;
    ArrayList<Autoturism> autoturisme = new ArrayList<Autoturism>();

    public Camion(String nrInmatriculare, double greutateAuto) {
        this.nrInmatriculare = nrInmatriculare;
        this.greutateAuto = greutateAuto;
    }

    public void adaugaAutoturism(Autoturism data) {
        autoturisme.add(data);
    }

    public double calculeazaGreautateTotala() {
        double result = greutateAuto;

        for (Autoturism auto : autoturisme){
            result += auto.calculeazaGreautateTotala();
        }
    
        return result;
    }

    public String toString() {
        String result = "Numar inmatriculare: " + nrInmatriculare + ", Greutate autovehicul: " + greutateAuto + "\n";

        for (Autoturism auto : autoturisme) {
            result += "   " + auto + "\n";
        }

        return result;
    }

}

class Bac extends Autovehicule {
    private double greutateMax;
    private ArrayList<Autovehicule> auto = new ArrayList<Autovehicule>();
    public double greutateAct;

    public Bac(double greutateMax) {
        this.greutateMax = greutateMax;
        greutateAct = 0;
    }

    public boolean adaugaAutovehicul(Autovehicule data) {
        if (greutateAct + data.calculeazaGreautateTotala() < greutateMax){
            greutateAct += data.calculeazaGreautateTotala();
            auto.add(data);
            return true;
        }
        return false;
    }

    public double calculeazaGreautateTotala() {
        return greutateAct;
    }

    public String toString() {
        String result = "Greutate maxima: " + greutateMax + "\n";

        for (Autovehicule v : auto)
            result += "   " + v + "\n";
        
        return result;
    }
}

class Main {
    public static void main(String[] args) {

        Autoturism autoturism1 = new Autoturism("B123ABC", 4.5, 0.3); // Greutate auto: 4.5 t, greutate pasageri: 0.3 t
        Autoturism autoturism2 = new Autoturism("B456DEF", 2.5, 0.2); // Greutate auto: 2.5 t, greutate pasageri: 0.2 t
        Autoturism autoturism3 = new Autoturism("B789GHI", 3.0, 0.25); // Greutate auto: 3.0 t, greutate pasageri: 0.25 t

        System.out.println("Autoturisme create:");
        System.out.println(autoturism1);
        System.out.println(autoturism2);
        System.out.println(autoturism3);

        Camion camion = new Camion("CAM123", 7.0);
        camion.adaugaAutoturism(autoturism1);
        camion.adaugaAutoturism(autoturism2);
        camion.adaugaAutoturism(autoturism3);

        System.out.println("\nCamion cu autoturisme:");
        System.out.println(camion);

        Bac bac = new Bac(20.0);
        boolean adaugat = bac.adaugaAutovehicul(camion);

        System.out.println("\nIncarcarea camionului in bac:");
        if (adaugat) {
            System.out.println("Camionul a fost incarcat in bac.");
        } else {
            System.out.println("Camionul NU a putut fi incarcat in bac (greutatea maxima depasita).");
        }

        System.out.println("\nStarea finala a bacului:");
        System.out.println(bac);
    }
}
