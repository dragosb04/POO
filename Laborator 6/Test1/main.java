class Angajat {
    private String numeAngajat;
    private int salariuAngajat;

    public Angajat (String nume, int salariu){
        numeAngajat = nume;
        salariuAngajat = salariu;
    }
    
    public Angajat (int salariu, String nume){
        numeAngajat = nume;
        salariuAngajat = salariu;
    }

    public int getSalariu(){
        return salariuAngajat;
    }

    public String getNumeAngajat(){
        return numeAngajat;
    }

    public boolean equals (Object o){
        if (o instanceof Angajat){
            Angajat obj_to_compare = (Angajat) o;
            return obj_to_compare.getNumeAngajat().equals(numeAngajat) && obj_to_compare.getSalariu() == salariuAngajat;
        }
        return false;
    }
}

class Firma {
    private String numeFirma;
    private int bugetFirma;
    private Angajat[] angajati = new Angajat[30];
    private int indexAngajat = 0;

    public Firma (String nume, int buget){
        numeFirma = nume;
        bugetFirma = buget;
    }

    public Firma (int buget, String nume){
        numeFirma = nume;
        bugetFirma = buget;
    }

    public boolean adaugaAngajat (Angajat data){
        if (indexAngajat < 30){
            for (int i = 0; i < indexAngajat; i++)
                if (data.equals(angajati[i]))
                    return false;
            
            angajati[indexAngajat++] = data;
            return true;
        }
        return false;
    }

    public String toString (){
        if (indexAngajat == 0)
            return "Firma nu are angajati.";

        String result = numeFirma + "\n";
        for (int i = 0; i < indexAngajat; i++)
            result += "Angajat [" + i + "]: \n    " + angajati[i].getNumeAngajat()+ ", " + angajati[i].getSalariu() + "\n";
        return result;
    }

    public void platesteSalarii(){
        int suma = 0;
        for (int i = 0; i < indexAngajat; i++){
            suma += angajati[i].getSalariu();
        }
        System.out.println("Suma ramasa: " + (bugetFirma - suma));

    }
}

class Main {
    public static void main(String[] args) {
        Angajat angajat1 = new Angajat(2000, "Candele Andrei");
        Angajat angajat2 = new Angajat(4000, "Obor Ioana");
        Angajat angajat3 = new Angajat("Mugurean Ion", 5000);

        Angajat duplicat = new Angajat(4000, "Obor Ioana");

        Firma firma = new Firma(15000, "Tech Solution");

        firma.adaugaAngajat(angajat1);
        firma.adaugaAngajat(angajat2);
        firma.adaugaAngajat(angajat1);
        firma.adaugaAngajat(angajat3);
        firma.adaugaAngajat(angajat1);
        firma.adaugaAngajat(duplicat);

        System.out.println(firma);

        firma.platesteSalarii();
    }
}
