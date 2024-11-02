class Tir {
    private Remorca[] remorci = new Remorca[5];
    private int nr_Remorci;

    public boolean adaugaRemorca (int nr_Cutii, String nrInmatriculare){
        
        if (nr_Remorci + 1 < 5){
            remorci[nr_Remorci] = new Remorca(nr_Cutii, nrInmatriculare);
            nr_Remorci++;
            return true;
        }

        return false;
    }

    public boolean adaugaRemorca (Remorca remorca){
        if (nr_Remorci + 1 < 5) {
            remorci[nr_Remorci] = remorca;
            nr_Remorci++;
            return true;
        }
            return false;
    }

    public Remorca stergeRemorca (String nrInmatriculare) {
        for (int i = 0; i <= nr_Remorci; i++){
            if (remorci[i].getNrInmatriculare().equals(nrInmatriculare)){
                Remorca remorca_inlaturata = remorci[i];

                for (int j = i; j < nr_Remorci; j++)
                    remorci[j] = remorci[j + 1];

                remorci[nr_Remorci] = null;
                nr_Remorci--;
                return remorca_inlaturata;
            }
        }

        return null;
    }

    public int getNrRemorci () {
        return nr_Remorci;
    }
    public boolean equals (Object o){
        if (o instanceof Tir){
            Tir objToCompare = (Tir) o;
                return totalCutii() == objToCompare.totalCutii();
        }
        return false;
    }

    public int totalCutii () {
        int total = 0;
        for (int i = 0; i < nr_Remorci; i++){
            total += remorci[i].getNrCutii();
        }

        return total;
    }

    public String toString () {
        String string_To_Show = "T";

        for (int i = 0; i < nr_Remorci; i++) {
            string_To_Show += " -> " + remorci[i].toString();
        }
        string_To_Show += '\n';
        return string_To_Show;
    }
}

class Remorca {
    private int nr_Cutii;
    private String nr_Inmatriculare;
    private static int nr_remorciTotale;
    private static int ultimul_nrCutii;

    public Remorca(int nr_Cutii, String nr_Inmatriculare) {
        this.nr_Cutii = nr_Cutii;
        this.nr_Inmatriculare = nr_Inmatriculare;
        nr_remorciTotale++;
        ultimul_nrCutii = this.nr_Cutii;
    }

    public Remorca(String nr_Inmatriculare) {
        if (nr_remorciTotale == 0){
            this.nr_Cutii = 10;
            ultimul_nrCutii = this.nr_Cutii;
        }
        else {
            this.nr_Cutii = ultimul_nrCutii + 1;
            ultimul_nrCutii = this.nr_Cutii;
        }
        this.nr_Inmatriculare = nr_Inmatriculare;
        nr_remorciTotale++;
    }


    public String getNrInmatriculare() {
        return nr_Inmatriculare;
    }

    public int getNrCutii (){
        return nr_Cutii;
    }

    public String toString() {
        return "R(" + getNrInmatriculare() + ", " + getNrCutii() + ")";
    }
}

class Main {
    public static void main(String[] args) {

        Tir tir1 = new Tir();
        Tir tir2 = new Tir();

        tir1.adaugaRemorca(15, "AB123CD");
        tir1.adaugaRemorca(new Remorca("XY456ZT"));

        tir2.adaugaRemorca(20, "EF789GH");
        tir2.adaugaRemorca(new Remorca("IJ012KL"));
        tir2.adaugaRemorca(10, "MN345OP");

        System.out.println("Tir 1: " + tir1);
        System.out.println("Tir 2: " + tir2);

        Remorca removedRemorca = tir1.stergeRemorca("AB123CD");
        System.out.println("Remorca stearsa din Tir 1: " + removedRemorca);

        System.out.println("Tir 1: " + tir1);

        System.out.println("Tir 1 si Tir 2 au aceeasi capacitate? " + tir1.equals(tir2));
    }
}