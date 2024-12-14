class Echipa {
    private Jucator[] titulari;
    private Jucator[] rezerve;

    private int indexTitulari;
    private int indexRezerve;

    public Echipa(Jucator[] titulari, Jucator[] rezerve) {
        this.titulari = titulari;
        this.rezerve = rezerve;
        indexTitulari = titulari.length;
        indexRezerve = rezerve.length;
    }

    public boolean efectueazaSchimbare(Jucator titular, Jucator rezerva) {
        for (int i = 0; i < indexTitulari; i++) {
            if ((titular).equals(titulari[i])) {
                for (int j = 0; j < indexRezerve; j++) {
                    if (rezerva.equals(rezerve[j])) {
                        Jucator temp = new Jucator(rezerve[j].getNrTricou(), rezerve[j].getNume());
                        rezerve[j] = titulari[i];
                        titulari[i] = temp;
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    public String toString() {
        String result = "Titulari:\n";

        for (int i = 0; i < indexTitulari; i++) {
            result += "(" + titulari[i].getNume() + ", " + titulari[i].getNrTricou() + "); ";
        }

        result += "\nRezerve:\n";

        for (int i = 0; i < indexRezerve; i++) {
            result += "(" + rezerve[i].getNume() + ", " + rezerve[i].getNrTricou() + "); ";
        }

        return result;
    }
}

class Jucator {
    private String nume;
    private int nrTricou;

    public Jucator(String nume, int nrTricou) {
        this.nume = nume;
        this.nrTricou = nrTricou;
    }

    public Jucator(int nrTricou, String nume) {
        this.nume = nume;
        this.nrTricou = nrTricou;
    }

    public int getNrTricou() {
        return nrTricou;
    }

    public String getNume() {
        return nume;
    }

    public boolean equals(Object o) {
        if (o instanceof Jucator) {
            Jucator data = (Jucator) o;
            return nume == data.nume && nrTricou == data.nrTricou;
        }
        return false;
    }
}

class Main {
    public static void main(String[] args) {
        Jucator[] rezerve = { new Jucator("Ion Popovici", 22), new Jucator("Andrei Stanciu", 11),
                new Jucator("David Popovici", 41) };
        Jucator[] titulari = { new Jucator("Dan Liviu", 2), new Jucator(14, "Horatiu Daniel"),
                new Jucator(12, "Catalin Ganea"), new Jucator(5, "Dragan Ionut") };

        Echipa echipa = new Echipa(titulari, rezerve);
        System.out.println(echipa + "\n\n");
        echipa.efectueazaSchimbare(titulari[0], rezerve[2]);
        System.out.println(echipa + "\n\n");
        echipa.efectueazaSchimbare(titulari[1], rezerve[1]);
        System.out.println(echipa + "\n\n");
        echipa.efectueazaSchimbare(titulari[0], rezerve[0]);
        System.out.println(echipa + "\n\n");
        echipa.efectueazaSchimbare(titulari[1], rezerve[0]);
        System.out.println(echipa + "\n\n");
    }
}